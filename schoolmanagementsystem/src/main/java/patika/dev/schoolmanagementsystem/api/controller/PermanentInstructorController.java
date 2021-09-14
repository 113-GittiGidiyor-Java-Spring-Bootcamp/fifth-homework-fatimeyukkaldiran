package patika.dev.schoolmanagementsystem.api.controller;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.ClientRequestInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.dev.schoolmanagementsystem.dto.PermanentInstructorDto;
import patika.dev.schoolmanagementsystem.entity.InstructorServiceTransactionLogger;
import patika.dev.schoolmanagementsystem.entity.PermanentInstructor;
import patika.dev.schoolmanagementsystem.entity.enumeration.TransactionType;
import patika.dev.schoolmanagementsystem.service.PermanentInstructorService;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/permanent-instructor")
@Slf4j
public class PermanentInstructorController {

    private final PermanentInstructorService instructorService;


    @PostMapping("/add")
    public ResponseEntity<PermanentInstructor> saveInstructor(@RequestBody PermanentInstructorDto instructorDto){
        Optional<PermanentInstructor> resultOptional = instructorService.saveInstructor(instructorDto);
        if (resultOptional.isPresent()){
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/increase-salary/{instructorId}/{transactionType}/{percent}")
    public ResponseEntity<PermanentInstructor> getSalaryWithIncreasingUpdate(@PathVariable long instructorId,
                                                                             @PathVariable TransactionType transactionType, @PathVariable int percent){
        Optional<PermanentInstructor> resultOptional = Optional.ofNullable(instructorService.increaseSalary(instructorId,percent,transactionType));
        if (resultOptional.isPresent()){
            return new ResponseEntity<>(resultOptional.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/decrease-salary/{instructorId}/{transactionType}/{percent}")
    public ResponseEntity<PermanentInstructor> getSalaryWithDecreasingUpdate(@PathVariable long instructorId,
                                                                             @PathVariable TransactionType transactionType, @PathVariable int percent){
        Optional<PermanentInstructor> resultOptional = Optional.ofNullable(instructorService.decreaseSalary(instructorId,percent,transactionType));
        if (resultOptional.isPresent()){
            return new ResponseEntity<>(resultOptional.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-transactions-by-date")
    public ResponseEntity<Page<List<InstructorServiceTransactionLogger>>> getAllTransactionsWithDate(
            @ApiParam(value = "transaction query for wallet usage", example = "10/12/2019", required = true)
            @RequestParam String transactionDate,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @PageableDefault(page = 0, size = 10) Pageable pageable){
        return new ResponseEntity<>(this.instructorService.getAllTransactionsWithDate(transactionDate, pageNumber, pageSize, pageable), HttpStatus.OK);
    }

}
