package vizsgaremek.massage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import vizsgaremek.massage.guests.*;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql(statements = "delete from guests")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GuestsControllerRestTemplateIT {


    @Autowired
    TestRestTemplate template;


    @Test
    void testGetGuests() {


        template.postForObject("/api/guests", new CreateGuestCommand(
                        "Frau Markgraf", "223344", MedicalCondition.MALFORMATION),
                GuestDto.class);


        template.postForObject("/api/guests", new CreateGuestCommand(
                        "Herr Zipfer", "123651", MedicalCondition.SPINAL_PROBLEM),
                GuestDto.class);

        List<GuestDto> guests = template.exchange("/api/guests",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GuestDto>>() {
                })
                .getBody();

        assertThat(guests)
                .extracting(GuestDto::getName)
                .containsExactly("Frau Markgraf", "Herr Zipfer");
    }

    @Test
    void testCreateGuest() {
        GuestDto result =
                template.postForObject("/api/guests",
                        new CreateGuestCommand(
                                "Frau Markgraf", "223344", MedicalCondition.MALFORMATION),
                        GuestDto.class);

        assertEquals("Frau Markgraf", result.getName());
        assertEquals("223344", result.getPhoneNumber());
        assertEquals(MedicalCondition.MALFORMATION, result.getMedicalCondition());
    }


    @Test
    void testUpdatePhoneNumber() {
        GuestDto guest = template.postForObject("/api/guests",
                new CreateGuestCommand(
                        "Frau Markgraf", "223344", MedicalCondition.MALFORMATION),
                GuestDto.class);

        template.put("/api/guests/"+ guest.getId()+"/phonenumber", new UpdatePhoneNumberCommand("153344"));

        GuestDto result = template.getForObject("/api/guests/" + guest.getId() + "/phonenumber", GuestDto.class);


        assertEquals("153344", result.getPhoneNumber());
    }





    @Test
    void testUpdateMedicalConditionById() {
        GuestDto guest = template.postForObject("/api/guests",
                new CreateGuestCommand(
                        "Frau Markgraf", "223344", MedicalCondition.MALFORMATION),
                GuestDto.class);

        template.put("/api/guests/"+ guest.getId() + "/medicalcondition", new UpdateMedicalConditionCommand(MedicalCondition.CASUALTY));

        GuestDto result = template.getForObject("/api/guests/" + guest.getId() + "/medicalcondition",GuestDto.class);

        assertEquals(MedicalCondition.CASUALTY,result.getMedicalCondition());

    }

    @Test
    void testDeleteGuest() {
        GuestDto guest =
                template.postForObject("/api/guests", new CreateGuestCommand(
                                "Herr Zipfer", "123651", MedicalCondition.SPINAL_PROBLEM),
                        GuestDto.class);
        template.delete("/api/guests/{id}", guest.getId());

        List<GuestDto> result = template.exchange("/api/guests",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GuestDto>>() {
                }).getBody();

        assertThat(result).
                hasSize(0);
    }

}



