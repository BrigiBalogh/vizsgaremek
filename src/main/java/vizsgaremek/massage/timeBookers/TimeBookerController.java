package vizsgaremek.massage.timeBookers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/time-bookers")
@Tag(name = "Operations on timeBooker")
public class TimeBookerController {

    private final TimeBookerService timeBookerService;


    public TimeBookerController(TimeBookerService timeBookerService) {
        this.timeBookerService = timeBookerService;
    }

    @GetMapping
    public List<TimeBookerDto> getTimeBookers() {
        return timeBookerService.getTimeBookers();
    }


    @PostMapping
    @Operation(summary = "Creates a timeBooker", description = " New timeBooker has been created.")
    @ApiResponse(responseCode = "201", description = "TimeBooker is  create")
    public TimeBookerDto createTimeBooker(@Valid @RequestBody CreateTimeBookerCommand command) {
        return timeBookerService.createTimeBooker(command);
    }


    @PutMapping("/{id}")
    public TimeBookerDto updateTimeBookerById(@PathVariable("id") long id, @Valid @RequestBody UpdateTimeBookerCommand command) {
        return timeBookerService.updateTimeBookerById(id, command);
    }


    @DeleteMapping("/{id}")
    public void deleteTimeBooker(@PathVariable("id") long id) {
        timeBookerService.deleteTimeBooker(id);
    }
}


