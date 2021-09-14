package patika.dev.schoolmanagementsystem.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import patika.dev.schoolmanagementsystem.dto.StudentDto;
import patika.dev.schoolmanagementsystem.entity.Student;
import patika.dev.schoolmanagementsystem.service.StudentService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentDto studentDto){
        Optional<Student> resultOptional = studentService.saveStudent(studentDto);
        if (resultOptional.isPresent()){
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{studentId}/{courseId}")
    public Student addStudentCourse(@PathVariable Long studentId, @PathVariable Long courseId){
        return studentService.addStudentToCourse(studentId,courseId).get();
    }


}
