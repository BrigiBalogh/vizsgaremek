package vizsgaremek.massage.timeBookers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vizsgaremek.massage.NotFoundException;
import vizsgaremek.massage.guests.Guest;
import vizsgaremek.massage.guests.GuestRepository;


import java.util.List;

@Service
@AllArgsConstructor
public class TimeBookerService {


    private ModelMapper mapper;

    private TimeBookerRepository timeBookerRepository;

    private GuestRepository guestRepository;


    public List<TimeBookerDto> getTimeBookers() {
        return timeBookerRepository.findAll().stream()
                .map(t -> mapper.map(t, TimeBookerDto.class))
                .toList();
    }


    public TimeBookerDto findTimeBookerById(long id) {
        TimeBooker timeBooker = findTimeBookerBy(id);

        return mapper.map(timeBooker, TimeBookerDto.class);
    }


    public TimeBookerDto createTimeBooker(CreateTimeBookerCommand command) {
        TimeBooker timeBooker = new TimeBooker(command.getStartTime(), command.getEndTime(),
                command.getStatus());
        Guest guest = guestRepository.findById(command.getGuestId()).orElseThrow(()->new NotFoundException(command.getGuestId()));
        guest.addTimeBooker(timeBooker);
        timeBookerRepository.save(timeBooker);
        return mapper.map(timeBooker,  TimeBookerDto.class);
    }


    @Transactional
    public TimeBookerDto updateTimeBookerById(long id, UpdateTimeBookerCommand command) {
        TimeBooker timeBooker = findTimeBookerBy(id);

        timeBooker.setStartTime(command.getStartTime());
        timeBooker.setEndTime(command.getEndTime());
        timeBooker.setStatus(command.getStatus());

        return mapper.map(timeBooker, TimeBookerDto.class);
    }


    public void deleteTimeBooker(long id) {
        timeBookerRepository.deleteById(id);
    }


    private TimeBooker findTimeBookerBy(long id) {

        return timeBookerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }
}