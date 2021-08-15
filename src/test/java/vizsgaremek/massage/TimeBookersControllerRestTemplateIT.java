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
import vizsgaremek.massage.guests.CreateGuestCommand;
import vizsgaremek.massage.guests.GuestDto;
import vizsgaremek.massage.guests.MedicalCondition;
import vizsgaremek.massage.timeBookers.CreateTimeBookerCommand;
import vizsgaremek.massage.timeBookers.Status;
import vizsgaremek.massage.timeBookers.TimeBookerDto;
import vizsgaremek.massage.timeBookers.UpdateTimeBookerCommand;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql(statements = {"delete from time_bookers", "delete from guests"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TimeBookersControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    GuestDto guest;

    GuestDto guest2;

    static String URL_GUEST = "/api/guests";
    static String URL_TIMEBOOKER = "/api/guests/{id}/time-bookers";
    static String URL_TIMEBOOKER_ID = "/api/guests/{id}/time-bookers/{timebookId}";

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
    void testGetTimeBookers() {


        params.put("id", guest.getId().toString());
        TimeBookerDto timeBookerDto =
                template.postForObject(
                        URL_TIMEBOOKER,
                        new CreateTimeBookerCommand(
                                LocalDateTime.of(2021, 5, 15, 10, 30),
                                LocalDateTime.of(2021, 5, 15, 11, 30),
                                Status.PAID),
                        TimeBookerDto.class, params);

        assertEquals(LocalDateTime.of(2021, 5, 15, 10, 30), timeBookerDto.getStartTime());
        params.put("id", guest2.getId().toString());
        template.postForObject(URL_TIMEBOOKER, new CreateTimeBookerCommand(
                LocalDateTime.of(2021, 5, 17, 10, 30),
                LocalDateTime.of(2021, 5, 17, 11, 30),
                Status.NOT_PAID), TimeBookerDto.class, params);


        List<TimeBookerDto> timeBookers = template.exchange(URL_TIMEBOOKER,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TimeBookerDto>>() {
                }, params)
                .getBody();

        assertThat(timeBookers)
                .extracting(TimeBookerDto::getStartTime)
                .containsExactly(
                        LocalDateTime.of(2021, 5, 17, 10, 30));
    }


    @Test
    void testCreateTimeBooker() {


        params.put("id", guest2.getId().toString());
        TimeBookerDto result =
                template.postForObject(URL_TIMEBOOKER,
                        new CreateTimeBookerCommand(
                                LocalDateTime.of(2021, 5, 17, 10, 30),
                                LocalDateTime.of(2021, 5, 17, 11, 30),
                                Status.NOT_PAID), TimeBookerDto.class, params);


        assertEquals(LocalDateTime.of(2021, 5, 17, 10, 30), result.getStartTime());
        assertEquals(LocalDateTime.of(2021, 5, 17, 11, 30), result.getEndTime());

    }


    @Test
    void testUpdateStartTimeById() {


        params.put("id", guest2.getId().toString());
        TimeBookerDto timeBookerDto =
                template.postForObject(URL_TIMEBOOKER,
                        new CreateTimeBookerCommand(
                                LocalDateTime.of(2021, 5, 17, 10, 30),
                                LocalDateTime.of(2021, 5, 17, 11, 30),
                                Status.NOT_PAID), TimeBookerDto.class, params);

        params.put("timebookId", timeBookerDto.getId().toString());
        TimeBookerDto result = template.exchange(
                URL_TIMEBOOKER_ID,
                HttpMethod.PUT,
                new HttpEntity<>(new UpdateTimeBookerCommand(LocalDateTime.of(2021, 5, 17, 10, 30),
                        LocalDateTime.of(2021, 5, 17, 11, 30),
                        Status.PAID)),
                TimeBookerDto.class, params
        ).getBody();

        assertEquals(Status.PAID,
                result.getStatus());
    }


    @Test
    void testDeleteTimeBooker() {


        params.put("id", guest.getId().toString());
        TimeBookerDto timeBookerDto =
                template.postForObject(
                        URL_TIMEBOOKER,
                        new CreateTimeBookerCommand(
                                LocalDateTime.of(2021, 5, 15, 10, 30),
                                LocalDateTime.of(2021, 5, 15, 11, 30),
                                Status.PAID),
                        TimeBookerDto.class, params);

        params.put("timebookId", timeBookerDto.getId().toString());

        template.delete(URL_TIMEBOOKER_ID, params);


        List<TimeBookerDto> result = template.exchange(URL_TIMEBOOKER,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TimeBookerDto>>() {
                }, params)
                .getBody();

        assertThat(result).
                hasSize(0);
    }


    @Test
    void testCreateTimeBookerInvalidDate() {


        params.put("id", guest2.getId().toString());
        TimeBookerDto result =
                template.postForObject(URL_TIMEBOOKER,
                        new CreateTimeBookerCommand(
                                LocalDateTime.of(2021, 5, 17, 10, 30),
                                LocalDateTime.of(2021, 5, 17, 11, 30),
                                Status.NOT_PAID), TimeBookerDto.class, params);
        Problem problem =
                template.postForObject(URL_TIMEBOOKER,
                        new CreateTimeBookerCommand(
                                LocalDateTime.of(2021, 5, 17, 10, 0),
                                LocalDateTime.of(2021, 5, 17, 11, 0),
                                Status.NOT_PAID), Problem.class, params);

        assertEquals(Status.BAD_REQUEST, problem.getStatus());
    }
}
