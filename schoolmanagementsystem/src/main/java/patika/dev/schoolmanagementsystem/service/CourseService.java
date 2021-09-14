package patika.dev.schoolmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import patika.dev.schoolmanagementsystem.dto.CourseDto;
import patika.dev.schoolmanagementsystem.entity.Course;
import patika.dev.schoolmanagementsystem.entity.abstracts.Instructor;
import patika.dev.schoolmanagementsystem.exceptions.InstructorNotFoundException;
import patika.dev.schoolmanagementsystem.mappers.CourseMapper;
import patika.dev.schoolmanagementsystem.repository.CourseRepository;
import patika.dev.schoolmanagementsystem.repository.InstructorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CourseService {
    @Autowired
   private CourseRepository courseRepository;

    @Autowired
   private  CourseMapper courseMapper;

    @Autowired
    private InstructorRepository instructorRepository;

    public Optional<Course> saveCourse(CourseDto courseDto){
        Course course = courseMapper.mapFromCourseDtoToCourse(courseDto);
        return Optional.of(courseRepository.save(course));
    }


    public Optional<List<Course>> getCoursesByInstructor(int instructorId) {
        Instructor instructor = this.findByInstructorId(instructorId);
        Optional<List<Course>> courses = Optional.of(instructor.getCourses());

        if (courses.get().isEmpty()) {
            List<Course> courseList = new ArrayList<>();

            Iterable<Course> courseIter = courseRepository.findAll();
            courseIter.iterator().forEachRemaining(courseList::add);
          return Optional.of(courseList);
        }

        return courses;
    }


    @Transactional
    public CourseDto deleteCourseById(long courseId){
        Course foundCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Course with ID: %d could not found!", courseId))) ;

        CourseDto result = courseMapper.mapFromCourseToCourseDto((foundCourse));
        courseRepository.delete(foundCourse);
        return result;
    }


   public Instructor findByInstructorId(long instructorId){
        Instructor foundInstructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new InstructorNotFoundException(String.format("Instructor with ID: %d could not foud!", instructorId)));
        return foundInstructor;
    }
}
