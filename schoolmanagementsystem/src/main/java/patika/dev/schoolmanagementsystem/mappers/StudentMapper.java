package patika.dev.schoolmanagementsystem.mappers;

import org.mapstruct.Mapper;
import patika.dev.schoolmanagementsystem.dto.StudentDto;
import patika.dev.schoolmanagementsystem.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student mapFromStudentDtoToStudent(StudentDto studentDto);
    StudentDto mapFromStudentToStudentDto(Student student);



}
