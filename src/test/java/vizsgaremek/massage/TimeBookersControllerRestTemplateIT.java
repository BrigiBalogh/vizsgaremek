package vizsgaremek.massage;

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

@Sql(statements = "delete from time_bookers")
@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT )
public class TimeBookersControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;



    @Test
    void testGetTimeBookers() {

        TimeBookerDto timeBookerDto =
                template.postForObject("/api/time-bookers", new CreateTimeBookerCommand(
                        LocalDateTime.of(2021, 5,15, 10,30),
                        LocalDateTime.of(2021,5,15, 11,30)
                        ,new Guest ("Frau Markgraf", "223344", MedicalCondition.MALFORMATION)) , TimeBookerDto.class);

        assertEquals("Frau Markgraf", timeBookerDto.getGuest().getName());


    }







}
