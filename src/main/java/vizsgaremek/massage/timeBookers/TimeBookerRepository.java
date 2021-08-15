package vizsgaremek.massage.timeBookers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vizsgaremek.massage.timeBookers.TimeBooker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TimeBookerRepository extends JpaRepository<TimeBooker, Long> {

    @Query("SELECT t  from TimeBooker t " +
            "where t.startTime between :startTime and :endTime  or  t.endTime between :startTime and :endTime")
    Optional<TimeBooker> findTimeBookerBetweenStartTimeAndEndTime(
            LocalDateTime startTime, LocalDateTime endTime);

    List<TimeBooker> findAllByGuest_Id(Long id);

    Optional<TimeBooker> findByIdAndGuest_Id(Long id, Long guestId);

}
