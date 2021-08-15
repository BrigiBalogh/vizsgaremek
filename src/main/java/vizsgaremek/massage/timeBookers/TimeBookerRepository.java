package vizsgaremek.massage.timeBookers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vizsgaremek.massage.timeBookers.TimeBooker;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TimeBookerRepository extends JpaRepository<TimeBooker, Long> {

    @Query("SELECT t  from TimeBooker t " +
            "where t.startTime between :startTime and :endTime or  t.endTime between :startTime and :endTime")
    Optional<TimeBookerDto> FindTimeBookerBetweenStartTimeAndEndTime(
            @Param("start_time")LocalDateTime startTime,  @Param("end_time")LocalDateTime endTime);

}
