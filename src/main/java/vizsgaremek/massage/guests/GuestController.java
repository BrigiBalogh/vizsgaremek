package vizsgaremek.massage.guests;


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


    @GetMapping("/{id}")
    public GuestDto findGuestById(@PathVariable("id") long id) {
        return guestService.findGuestById(id);
    }


    @PostMapping
    @Operation(summary = "Creates a guest", description = " New guest has been created.")
    @ApiResponse(responseCode = "201", description = "Guest is  create")
    public GuestDto createGuest(@Valid @RequestBody CreateGuestCommand command) {
        return guestService.createGuest(command);
    }



    @PutMapping("/{id}")
    public GuestDto updateGuestById(@PathVariable("id") long id, @Valid @RequestBody UpdateGuestCommand command) {
        return guestService.updateGuestById(id, command);
    }


    @DeleteMapping("/{id}")
    public void deleteGuest(@PathVariable("id") long id) {
        guestService.deleteGuest(id);
    }
}
