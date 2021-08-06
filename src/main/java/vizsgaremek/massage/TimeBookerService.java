package vizsgaremek.massage;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TimeBookerService {


    private ModelMapper mapper;

    private TimeBookerRepository timeBookerRepository;


    public List<TimeBookerDto> getTimeBookers() {
        return timeBookerRepository.findAll().stream()
                .map(t -> mapper.map(t, TimeBookerDto.class))
                .toList();
    }


    public TimeBookerDto createTimeBooker(CreateTimeBookerCommand command) {
        TimeBooker timeBooker = new TimeBooker(command.getStartTime(), command.getEndTime(),
                command.getGuest());
        timeBookerRepository.save(timeBooker);
        return mapper.map(timeBooker, TimeBookerDto.class);
    }


    @Transactional
    public TimeBookerDto updateTimeBookerById(long id, UpdateTimeBookerCommand command) {
        TimeBooker timeBooker = timeBookerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot found time booker"));
        timeBooker.setStartTime(command.getStartTime());

        return mapper.map(timeBooker, TimeBookerDto.class);
    }


    public void deleteTimeBooker(long id) {
        timeBookerRepository.deleteById(id);
    }
}