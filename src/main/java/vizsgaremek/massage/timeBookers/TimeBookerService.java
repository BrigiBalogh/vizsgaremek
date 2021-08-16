package vizsgaremek.massage.timeBookers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vizsgaremek.massage.NotFoundException;
import vizsgaremek.massage.guests.Guest;
import vizsgaremek.massage.guests.GuestDto;
import vizsgaremek.massage.guests.GuestRepository;


import java.util.List;

@Service
@AllArgsConstructor
public class TimeBookerService {


    private ModelMapper mapper;

    private TimeBookerRepository timeBookerRepository;

    private GuestRepository guestRepository;


    public List<TimeBookerDto> getTimeBookers(Long id) {
        java.lang.reflect.Type targetListType = new TypeToken<List<TimeBookerDto>>() {
        }.getType();
        return mapper.map(timeBookerRepository.findAllByGuest_Id(id), targetListType);
    }


    public TimeBookerDto findTimeBookerById(long id, long timeBookerId) {
        TimeBooker timeBooker = findTimeBookerByIdAndGuestId(id, timeBookerId);
        return mapper.map(timeBooker, TimeBookerDto.class);
    }


    public TimeBookerDto createTimeBooker(long id, CreateTimeBookerCommand command) {

        Guest guest = guestRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        TimeBooker timeBooker = new TimeBooker(command.getStartTime(), command.getEndTime(),
                command.getStatus(), guest);
        timeBookerRepository.save(timeBooker);
        return mapper.map(timeBooker, TimeBookerDto.class);
    }


    @Transactional
    public TimeBookerDto updateTimeBookerById(long id, long timeBookerId, UpdateTimeBookerCommand command) {
        TimeBooker timeBooker = findTimeBookerByIdAndGuestId(id,timeBookerId);

        timeBooker.setStartTime(command.getStartTime());
        timeBooker.setEndTime(command.getEndTime());
        timeBooker.setStatus(command.getStatus());

        return mapper.map(timeBooker, TimeBookerDto.class);
    }


    public void deleteTimeBooker(long id, long timeBookerId) {
        timeBookerRepository.delete(findTimeBookerByIdAndGuestId(id, timeBookerId));
    }


    private TimeBooker findTimeBookerByIdAndGuestId(long id, long timeBookerId) {

        return timeBookerRepository.findByIdAndGuest_Id( timeBookerId, id)
                .orElseThrow(() -> new NotFoundGuestIdAndTimeBookerIdException(id, timeBookerId) );
    }
}