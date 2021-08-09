package vizsgaremek.massage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;


import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql(statements = "delete from guests")
@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT )
public class GuestsControllerRestTemplateIT {


    @Autowired
    TestRestTemplate template;


    @Test
    void testGetGuests() {


        template.postForObject("/api/guests", new CreateGuestCommand(
                              "Frau Markgraf", "223344", MedicalCondition.MALFORMATION),
                        GuestDto.class);



       template.postForObject("/api/guest", new CreateGuestCommand(
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

        assertEquals("Frau Markgraf",result.getName());
        assertEquals("223344", result.getPhoneNumber());
        assertEquals(MedicalCondition.MALFORMATION, result.getMedicalCondition());
    }


    @Test
    void testUpdatePhoneNumberById() {
        template.postForObject("/api/guests",
                new CreateGuestCommand(
                        "Frau Markgraf", "223344", MedicalCondition.MALFORMATION),
                GuestDto.class);

        template.put("/api/guests/1/phonenumber", new UpdatePhoneNumberCommand("153344"));


        List<GuestDto> result = template.exchange(
                "/api/guest/1/phonenumber",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GuestDto>>() {
                }).getBody();

        assertThat(result)
                .extracting(GuestDto::getPhoneNumber)
                .containsExactly("153344");


    }


    @Test
    void testUpdateMedicalConditionById() {
        template.postForObject("/api/guests",
                new CreateGuestCommand(
                        "Frau Markgraf", "223344", MedicalCondition.MALFORMATION),
                GuestDto.class);

        template.put("/api/guests/1/medicalcondition", new UpdateMedicalConditionCommand(MedicalCondition.CASUALTY));


        List<GuestDto> result = template.exchange(
                "/api/guest/1/medicalcondition",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GuestDto>>() {
                }).getBody();


        assertThat(result)
                .extracting(GuestDto::getMedicalCondition)
                .containsExactly(MedicalCondition.CASUALTY);

    }

    @Test
    void testDeleteGuest() {
        GuestDto guestDto =
                template.postForObject("/api/guest", new CreateGuestCommand(
                                "Herr Zipfer", "123651", MedicalCondition.SPINAL_PROBLEM),
                        GuestDto.class);
        template.delete("/api/guest/1 ");

        List<GuestDto> result = template.exchange("/api/guest/1 ",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GuestDto>>() {
                }).getBody();

        assertThat(result).
                hasSize(0);
    }
                                 
}



