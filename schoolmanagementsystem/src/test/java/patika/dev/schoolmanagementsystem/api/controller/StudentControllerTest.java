package patika.dev.schoolmanagementsystem.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import patika.dev.schoolmanagementsystem.dto.StudentDto;
import patika.dev.schoolmanagementsystem.entity.Student;
import patika.dev.schoolmanagementsystem.service.StudentService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    StudentService mockStudentService;

    @InjectMocks
    StudentController studentController;

    @Test
    void saveStudent() {
        //GIVEN
        Student student = new Student();
        student.getId();
        Optional<Student> expected = Optional.of(student);
        Mockito.when(mockStudentService.saveStudent(Mockito.any())).thenReturn(expected);

        //WHEN
        StudentDto dto = new StudentDto();
        Student actual = this.studentController.saveStudent(dto).getBody();


        //THEN
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(), actual),
                () -> assertEquals(student.getId(), actual.getId())
        );
    }


}