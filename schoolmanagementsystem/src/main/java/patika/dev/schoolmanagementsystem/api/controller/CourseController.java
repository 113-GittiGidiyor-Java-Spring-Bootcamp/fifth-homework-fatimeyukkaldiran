package patika.dev.schoolmanagementsystem.api.controller;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.dev.schoolmanagementsystem.dto.CourseDto;
import patika.dev.schoolmanagementsystem.entity.Course;
import patika.dev.schoolmanagementsystem.entity.ErrorExceptionLogger;
import patika.dev.schoolmanagementsystem.service.CourseService;
import patika.dev.schoolmanagementsystem.service.ErrorExceptionLoggerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

  private final CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<Course> saveCourse(@RequestBody CourseDto courseDto){
        Optional<Course> resultOptional = courseService.saveCourse(courseDto);
        if (resultOptional.isPresent()) {
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Course>> getCourses(@RequestParam int instructorId){

        Optional<List<Course>> courses = courseService.getCoursesByInstructor(instructorId);

        return new ResponseEntity<>(courses.get(), HttpStatus.OK);
    }





}
