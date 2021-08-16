package vizsgaremek.massage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import vizsgaremek.massage.guests.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql(statements ={"delete from time_bookers", "delete from guests"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GuestsControllerRestTemplateIT {


    @Autowired
    TestRestTemplate template;

    GuestDto guest;

    GuestDto guest2;

    static String URL_GUEST = "/api/guests";
    static String URL_GUEST_ID = "/api/guests/{id}";

    static Map<String, String> params = new HashMap<>();

    @BeforeEach
    void init() {
        guest = template.postForObject(URL_GUEST, new CreateGuestCommand(
                        "Frau Markgraf", "223344", MedicalCondition.MALFORMATION),
                GuestDto.class);


        guest2 = template.postForObject(URL_GUEST, new CreateGuestCommand(
                        "Herr Zipfer", "123651", MedicalCondition.SPINAL_PROBLEM),
                GuestDto.class);
    }

    @Test
    void testGetGuests() {

        params.put("id", guest.getId().toString());
        List<GuestDto> guests = template.exchange(
                URL_GUEST,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GuestDto>>() {
                }, params)
                .getBody();

        assertThat(guests)
                .extracting(GuestDto::getName)
                .containsExactly("Frau Markgraf", "Herr Zipfer");
    }

    @Test
    void testCreateGuest() {


        assertEquals("Frau Markgraf", guest.getName());
        assertEquals("223344", guest.getPhoneNumber());
        assertEquals(MedicalCondition.MALFORMATION, guest.getMedicalCondition());
    }


    @Test
    void testUpdateGuest() {

        params.put("id", guest.getId().toString());
        GuestDto guest = template.exchange(
                URL_GUEST_ID,
                HttpMethod.PUT,
                new HttpEntity<>(new UpdateGuestCommand("153344", MedicalCondition.MALFORMATION)),
                GuestDto.class, params).getBody();

        assertEquals("153344", guest.getPhoneNumber());
    }


    @Test
    void testDeleteGuest() {
        params.put("id", guest2.getId().toString());

        template.delete(URL_GUEST_ID, params);

        List<GuestDto> result = template.exchange(URL_GUEST,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GuestDto>>() {
                }, params).getBody();

        assertThat(result).
                hasSize(1);
    }


    @Test
    void TestInvalidName() {

        params.put("id", guest2.getId().toString());
        Problem problem = template.postForObject(URL_GUEST, new CreateGuestCommand(
                        " ", "123651", MedicalCondition.SPINAL_PROBLEM),
                Problem.class, params);

        assertEquals(Status.BAD_REQUEST, problem.getStatus());
        assertEquals("Constraint Violation", problem.getTitle());
    }


    @Test
    void TestInvalidPhoneNumber() {

        params.put("id", guest2.getId().toString());
        Problem problem = template.postForObject(URL_GUEST, new CreateGuestCommand(
                        "Herr Zipfer", " ", MedicalCondition.SPINAL_PROBLEM),
                Problem.class, params);

        assertEquals(Status.BAD_REQUEST, problem.getStatus());
        assertEquals("Constraint Violation", problem.getTitle());
    }
}



