package patika.dev.schoolmanagementsystem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import patika.dev.schoolmanagementsystem.dto.CourseDto;
import patika.dev.schoolmanagementsystem.entity.Course;
import patika.dev.schoolmanagementsystem.service.CourseService;

@Mapper(componentModel = "spring")
public abstract class CourseMapper {

    @Autowired
     CourseService courseService;


    @Mapping(target = "instructor",expression = "java(courseService.findByInstructorId(courseDto.getInstructorId()))")
    public abstract Course mapFromCourseDtoToCourse(CourseDto courseDto);
    public  abstract CourseDto mapFromCourseToCourseDto(Course course);
}
