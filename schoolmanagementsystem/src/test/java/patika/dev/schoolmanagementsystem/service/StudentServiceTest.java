package patika.dev.schoolmanagementsystem.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import patika.dev.schoolmanagementsystem.dto.StudentDto;
import patika.dev.schoolmanagementsystem.entity.Student;
import patika.dev.schoolmanagementsystem.mappers.StudentMapper;
import patika.dev.schoolmanagementsystem.repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepository mockStudentRepository;

    @Mock
    StudentMapper mockStudentMapper;

    @InjectMocks
    StudentService studentService;


    @Test
    void saveStudent() {
        // given
        Student student = new Student();
        when(mockStudentRepository.selectExistsStudent(anyLong())).thenReturn(Boolean.FALSE);
        when(mockStudentMapper.mapFromStudentDtoToStudent(any())).thenReturn(student);
        when(mockStudentRepository.save(any())).thenReturn(student);

        // when
        StudentDto dto = new StudentDto();
        Student actual = this.studentService.saveStudent(dto).get();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(student, actual),
                () -> assertEquals(student.getId(), actual.getId())
        );
    }

}