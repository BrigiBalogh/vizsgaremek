package vizsgaremek.massage;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class GuestService {


    private ModelMapper mapper;

    private GuestRepository guestRepository;

    public List<GuestDto> getGuests() {
        return guestRepository.findAll().stream()
                .map(g -> mapper.map(g, GuestDto.class))
                .toList();
    }

    public GuestDto createGuest(CreateGuestCommand command) {
        Guest guest = new Guest(command.getName(), command.getPhoneNumber(),
                command.getMedicalCondition());
        guestRepository.save(guest);
        return mapper.map(guest, GuestDto.class);
    }

    @Transactional
    public GuestDto addTimeBookerToGuest(long id, AddTimeBookerCommand command) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot found guest"));

        TimeBooker timeBooker = new TimeBooker(command.getStartTime(), command.getEndTime(),command.getGuest());

        guest.addTimeBooker(timeBooker);

        return mapper.map(guest, GuestDto.class);
    }

    @Transactional
    public GuestDto updatePhoneNumberById(long id, UpdatePhoneNumberCommand command) {

        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot found guest"));
        guest.setPhoneNumber(command.getPhoneNumber());

        return mapper.map(guest, GuestDto.class);
    }

    @Transactional
    public GuestDto updateMedicalConditionById(long id, UpdateMedicalConditionCommand command) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot found guest"));
        guest.setMedicalCondition(command.getMedicalCondition());

        return mapper.map(guest, GuestDto.class);
    }


    @Transactional
    public GuestDto updateStatusById(long id, UpdateStatusCommand command) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot found guest"));
        guest.setStatus(command.getStatus());

        return mapper.map(guest, GuestDto.class);
    }


    public void deleteGuest(long id) {
        guestRepository.deleteById(id);
    }


}
