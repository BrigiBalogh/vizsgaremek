package vizsgaremek.massage.timeBookers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/guests/{id}/time-bookers")
@Tag(name = "Operations on timeBooker")
public class TimeBookerController {

    private final TimeBookerService timeBookerService;


    public TimeBookerController(TimeBookerService timeBookerService) {
        this.timeBookerService = timeBookerService;
    }

    @GetMapping
    public List<TimeBookerDto> getTimeBookers(@PathVariable Long id) {
        return timeBookerService.getTimeBookers(id);
    }

    @GetMapping("/{timebookerId}")
    public TimeBookerDto getTimeBooker(
            @PathVariable Long id,
            @PathVariable("timebookerId") Long timeBookId
    ) {
        return timeBookerService.findTimeBookerById(id, timeBookId);
    }


    @PostMapping
    @Operation(summary = "Creates a timeBooker", description = " New timeBooker has been created.")
    @ApiResponse(responseCode = "201", description = "TimeBooker is  create")
    public TimeBookerDto createTimeBooker(@PathVariable Long id, @Valid @RequestBody CreateTimeBookerCommand command) {
        return timeBookerService.createTimeBooker(id, command);
    }


    @PutMapping("/{timebookerId}")
    public TimeBookerDto updateTimeBookerById(
            @PathVariable("id") long id,
            @PathVariable("timebookerId")Long timeBookId,
            @Valid @RequestBody UpdateTimeBookerCommand command) {
        return timeBookerService.updateTimeBookerById(id, timeBookId, command);
    }


    @DeleteMapping("/{timebookerId}")
    public void deleteTimeBooker(@PathVariable("id") long id, @PathVariable("timebookerId") Long timeBookId) {
        timeBookerService.deleteTimeBooker(id, timeBookId);
    }
}


