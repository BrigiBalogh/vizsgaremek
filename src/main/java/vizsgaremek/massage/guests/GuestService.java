package vizsgaremek.massage.guests;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vizsgaremek.massage.NotFoundException;
import vizsgaremek.massage.timeBookers.TimeBooker;
import vizsgaremek.massage.timeBookers.TimeBookerRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GuestService {


    private ModelMapper mapper;

    private GuestRepository guestRepository;

    private TimeBookerRepository timeBookerRepository;

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
                .orElseThrow(() -> new NotFoundException(id));
        ;

        TimeBooker timeBooker = timeBookerRepository.findById(command.getTimeBookerId())
                .orElseThrow(() -> new NotFoundException(command.getTimeBookerId()));

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


    public GuestDto findGuestById(long id) {

        Optional<Guest> guest = guestRepository.findById(id);
        if (guest.isEmpty())
            return null;
        else
            return mapper.map(guest.get(), GuestDto.class);
    }

    public GuestDto findGuestByIdWithPhoneNumber(long id) {

        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return mapper.map(guest, GuestDto.class);
    }



    public GuestDto findGuestByIdWithMedicalcondition(long id) {

        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return mapper.map(guest, GuestDto.class);
    }
}