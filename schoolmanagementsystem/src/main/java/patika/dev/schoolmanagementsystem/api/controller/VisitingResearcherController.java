package patika.dev.schoolmanagementsystem.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import patika.dev.schoolmanagementsystem.dto.VisitingResearcherDto;
import patika.dev.schoolmanagementsystem.entity.VisitingResearcher;
import patika.dev.schoolmanagementsystem.service.VisitingResearcherService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/visiting-researcher-instructor")
public class VisitingResearcherController {

    private final VisitingResearcherService researcherService;

    @PostMapping("/add")
    public ResponseEntity<VisitingResearcher> saveInstructor(@RequestBody VisitingResearcherDto researcherDto){
        Optional<VisitingResearcher> resultOptional = researcherService.saveInstructor(researcherDto);
        if (resultOptional.isPresent()){
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}