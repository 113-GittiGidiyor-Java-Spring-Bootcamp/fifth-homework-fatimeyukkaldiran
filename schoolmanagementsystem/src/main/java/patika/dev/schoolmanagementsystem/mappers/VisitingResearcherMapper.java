package patika.dev.schoolmanagementsystem.mappers;

import org.mapstruct.Mapper;
import patika.dev.schoolmanagementsystem.dto.VisitingResearcherDto;
import patika.dev.schoolmanagementsystem.entity.VisitingResearcher;

@Mapper
public interface VisitingResearcherMapper {
    VisitingResearcher mapFromVisitingDtoToVisitingResearcher(VisitingResearcherDto researcherDto);
    VisitingResearcherDto mapFromVisitingResearcherToVisitingDto(VisitingResearcher researcher);
}
