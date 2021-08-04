package vizsgaremek.massage;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/guests")
@Tag(name = "Operations on guest")
public class GuestController {


    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }


    @GetMapping
    public List<GuestDto> getGuests() {
        return guestService.getGuests();
    }


    @PostMapping
    @Operation(summary = "Creates a guest", description = " New guest has been created.")
    @ApiResponse(responseCode = "201", description = "Location is  found")
    public GuestDto createGuest(@Valid @RequestBody CreateGuestCommand command) {
        return guestService.createGuest(command);
    }


    @PostMapping("/{id}")
    public GuestDto addTimeBookerToGuest(@PathVariable("id") long id,@Valid @RequestBody AddTimeBookerCommand command) {
        return guestService.addTimeBookerToGuest(id, command);
    }


    @PostMapping("/{id}")
    public GuestDto updatePhoneNumberById(@PathVariable("id") long id,@Valid @RequestBody UpdatePhoneNumberCommand command) {
        return guestService.updatePhoneNumberById(id, command);
    }

    @PostMapping("/{id}")
    public GuestDto updateMedicalConditionById(@PathVariable("id") long id,@Valid @RequestBody UpdateMedicalConditionCommand command) {
        return guestService.updateMedicalConditionById(id, command);
    }

    @DeleteMapping
    public void deleteGuest(@PathVariable("id") long id) {
        guestService.deleteGuest(id);
    }
}
