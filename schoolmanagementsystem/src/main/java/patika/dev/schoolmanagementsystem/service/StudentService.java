package patika.dev.schoolmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patika.dev.schoolmanagementsystem.dto.CourseDto;
import patika.dev.schoolmanagementsystem.dto.StudentDto;
import patika.dev.schoolmanagementsystem.entity.Course;
import patika.dev.schoolmanagementsystem.entity.Student;
import patika.dev.schoolmanagementsystem.mappers.CourseMapper;
import patika.dev.schoolmanagementsystem.mappers.StudentMapper;
import patika.dev.schoolmanagementsystem.repository.CourseRepository;
import patika.dev.schoolmanagementsystem.repository.StudentRepository;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class StudentService {


    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final CourseRepository courseRepository;
    /**
     * We use this method to save  our studentDto datas on db
     *
     *
     */
    public Optional<Student> saveStudent(StudentDto studentDto){
        return Optional.of(studentRepository.save(studentMapper.mapFromStudentDtoToStudent(studentDto)));
    }

    public Optional<Student> addStudentToCourse(Long studentId,Long courseId){
       Optional<Course> course = courseRepository.findById(courseId);
        Optional<Student> student = studentRepository.findById(studentId);

        course.get().getStudents().add(student.get());
        student.get().getCourses().add(course.get());
        courseRepository.save(course.get());
        return Optional.of(studentRepository.save(student.get()));
    }
}
