package patika.dev.schoolmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patika.dev.schoolmanagementsystem.dto.CourseDto;
import patika.dev.schoolmanagementsystem.dto.StudentDto;
import patika.dev.schoolmanagementsystem.entity.Course;
import patika.dev.schoolmanagementsystem.entity.PermanentInstructor;
import patika.dev.schoolmanagementsystem.entity.Student;
import patika.dev.schoolmanagementsystem.exceptions.BadRequestException;
import patika.dev.schoolmanagementsystem.exceptions.InstructorNotFoundException;
import patika.dev.schoolmanagementsystem.exceptions.StudentAgeNotValidException;
import patika.dev.schoolmanagementsystem.exceptions.StudentNotFoundException;
import patika.dev.schoolmanagementsystem.mappers.CourseMapper;
import patika.dev.schoolmanagementsystem.mappers.StudentMapper;
import patika.dev.schoolmanagementsystem.repository.CourseRepository;
import patika.dev.schoolmanagementsystem.repository.StudentRepository;
import patika.dev.schoolmanagementsystem.utils.ErrorMessageConstants;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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
        boolean isExists = studentRepository.selectExistsStudent(studentDto.getId());

        if(isExists){
            throw new BadRequestException("Student with ID : " + studentDto.getId() + " is already exists!");
        }
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

    public Student findInstructorById(long studentId){
        Student foundStudent= (Student) studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(String.format("Student with ID: %d could not found!", studentId)));
        return foundStudent;
    }

    public List<?> genderGroups(){
        return studentRepository.getGenderWithGrouping();
    }


    @Transactional
    public StudentDto updateStudent(StudentDto studentDto, long studentId){

        Student foundStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(String.format(ErrorMessageConstants.STUDENT_NOT_FOUND, studentId)));
        int birthDateYear = studentDto.getBirthDate().getYear();
        int age = (LocalDate.now().getYear()) - birthDateYear;
        if(age < 18 || age > 40){
            throw new StudentAgeNotValidException(ErrorMessageConstants.STUDENT_AGE_WRONG);
        }
        foundStudent.setFullName(studentDto.getFullName());
        foundStudent.setGender(studentDto.getGender());
        foundStudent.setBirthDate(studentDto.getBirthDate());
        studentRepository.save(foundStudent);
        return studentMapper.mapFromStudentToStudentDto(foundStudent);

    }
}
