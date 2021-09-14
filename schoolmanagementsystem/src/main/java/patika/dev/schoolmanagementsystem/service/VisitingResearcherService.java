package patika.dev.schoolmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.dev.schoolmanagementsystem.dto.VisitingResearcherDto;
import patika.dev.schoolmanagementsystem.entity.VisitingResearcher;
import patika.dev.schoolmanagementsystem.exceptions.InstructorNotFoundException;
import patika.dev.schoolmanagementsystem.mappers.VisitingResearcherMapper;
import patika.dev.schoolmanagementsystem.repository.VisitingResearcherRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitingResearcherService {
    private final VisitingResearcherRepository researcherRepository;
    private final VisitingResearcherMapper researcherMapper;

    public Optional<VisitingResearcher> saveInstructor(VisitingResearcherDto researcherDto){
        researcherDto.setTotalSalary(researcherDto.getTotalHour()*researcherDto.getHourlySalary()) ;
        VisitingResearcher researcher = researcherMapper.mapFromVisitingDtoToVisitingResearcher(researcherDto);
    return Optional.of(researcherRepository.save(researcher));

    }

    public VisitingResearcher findInstructorById(long instructorId){
        VisitingResearcher foundInstructor = (VisitingResearcher) researcherRepository.findById(instructorId)
                .orElseThrow(() -> new InstructorNotFoundException(String.format("Instructor with ID: %d could not found!", instructorId)));
        return foundInstructor;
    }
}
