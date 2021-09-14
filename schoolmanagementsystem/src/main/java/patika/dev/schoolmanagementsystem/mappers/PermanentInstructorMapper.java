package patika.dev.schoolmanagementsystem.mappers;

import org.mapstruct.Mapper;
import patika.dev.schoolmanagementsystem.dto.PermanentInstructorDto;
import patika.dev.schoolmanagementsystem.entity.PermanentInstructor;

@Mapper
public interface PermanentInstructorMapper {
   PermanentInstructor mapFromPermanentDtoToPermanentInstructor(PermanentInstructorDto instructorDto);
   PermanentInstructorDto mapFromPermanentInstructorToPermanentDto(PermanentInstructor permanentInstructor);
}
