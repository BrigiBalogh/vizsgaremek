package vizsgaremek.massage.guests;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vizsgaremek.massage.NotFoundException;
import vizsgaremek.massage.timeBookers.TimeBooker;
import vizsgaremek.massage.timeBookers.TimeBookerRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class GuestService {


    private ModelMapper mapper;

    private GuestRepository guestRepository;

    private TimeBookerRepository timeBookerRepository;

    public List<GuestDto> getGuests() {


        java.lang.reflect.Type targetListType = new TypeToken<List<GuestDto>>() {
        }.getType();
        return mapper.map(guestRepository.findAll(), targetListType);
    }


    public GuestDto findGuestById(long id) {
        Guest guest = findGuest(id);

        return mapper.map(guest, GuestDto.class);
    }

    public GuestDto createGuest(CreateGuestCommand command) {
        Guest guest = new Guest(command.getName(), command.getPhoneNumber(),
                command.getMedicalCondition());
        guestRepository.save(guest);
        return mapper.map(guest, GuestDto.class);
    }


    @Transactional
    public GuestDto updateGuestById(long id, UpdateGuestCommand command) {

        Guest guest = findGuest(id);

        guest.setPhoneNumber(command.getPhoneNumber());
        guest.setMedicalCondition(command.getMedicalCondition());

        return mapper.map(guest, GuestDto.class);

    }


    public void deleteGuest(long id) {
        guestRepository.deleteById(id);
    }


    private Guest findGuest(long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }


}