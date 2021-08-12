package vizsgaremek.massage;
/*
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import vizsgaremek.massage.guests.Guest;
import vizsgaremek.massage.guests.MedicalCondition;
import vizsgaremek.massage.timeBookers.CreateTimeBookerCommand;
import vizsgaremek.massage.timeBookers.TimeBookerDto;


import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql(statements = "delete from time_bookers")
@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT )
public class TimeBookersControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;



    @Test
    void testGetTimeBookers() {

        TimeBookerDto timeBookerDto =
                template.postForObject("/api/time-bookers", new CreateTimeBookerCommand(
                                LocalDateTime.of(2021, 5, 15, 10, 30),
                                LocalDateTime.of(2021, 5, 15, 11, 30)
                                , new Guest("Frau Markgraf", "223344", MedicalCondition.MALFORMATION)),
                        TimeBookerDto.class);

     //   assertEquals("Frau Markgraf", timeBookerDto.getGuest().getName());

        template.postForObject("/api/time-bookers", new CreateTimeBookerCommand(
                        LocalDateTime.of(2021, 5, 17, 10, 30),
                        LocalDateTime.of(2021, 5, 17, 11, 30)
                        , new Guest("Herr zipfer", "153344", MedicalCondition.SPINAL_PROBLEM)),
                TimeBookerDto.class);

        List<TimeBookerDto> timeBookers = template.exchange("/api/time-bookers",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TimeBookerDto>>() {
                })
                .getBody();

        assertThat(timeBookers)
                .extracting(TimeBookerDto::getStartTime)
                .containsExactly(LocalDateTime.of(2021, 5, 15, 10, 30),
                        LocalDateTime.of(2021, 5, 17, 10, 30));
    }



        @Test
        void testCreateTimeBooker() {
            TimeBookerDto result =
            template.postForObject("/api/time-bookers",
                    new CreateTimeBookerCommand(
                            LocalDateTime.of(2021, 5,17, 10,30),
                            LocalDateTime.of(2021,5,17, 11,30)
                            ,new Guest ("Herr zipfer", "153344", MedicalCondition.SPINAL_PROBLEM)),
                    TimeBookerDto.class);

            assertEquals( LocalDateTime.of(2021, 5,17, 10,30),result.getStartTime());
            assertEquals(LocalDateTime.of(2021,5,17, 11,30), result.getEndTime());
         //   assertEquals(new Guest ("Herr zipfer", "153344", MedicalCondition.SPINAL_PROBLEM), result.getGuest());
        }



        @Test
        void testUpdateStartTimeById() {
            TimeBookerDto timeBookerDto =
                    template.postForObject("/api/time-bookers",
                            new CreateTimeBookerCommand(
                                    LocalDateTime.of(2021, 5,17, 10,30),
                                    LocalDateTime.of(2021,5,17, 11,30)
                                    ,new Guest ("Herr zipfer", "153344", MedicalCondition.SPINAL_PROBLEM)),
                            TimeBookerDto.class);

            List<TimeBookerDto> result = template.exchange(
                    "/api/time-bookers",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<TimeBookerDto>>() {
                    }).getBody();

            assertEquals( LocalDateTime.of(2021, 5,17, 10,30),
                    result.get(0).getStartTime());
        }



    @Test
    void testDeleteTimeBooker() {
        TimeBookerDto timeBookerDto =
                template.postForObject("/api/time-bookers",
                        new CreateTimeBookerCommand(
                                LocalDateTime.of(2021, 5,17, 10,30),
                                LocalDateTime.of(2021,5,17, 11,30)
                                ,new Guest ("Herr zipfer", "153344", MedicalCondition.SPINAL_PROBLEM)),
                        TimeBookerDto.class);
        template.delete("/api/time-bookers/1 ");

        List<TimeBookerDto> result = template.exchange("/api/time-bookers/1 ",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TimeBookerDto>>() {
                }).getBody();

        assertThat(result).
                hasSize(0);
    }


}*/
