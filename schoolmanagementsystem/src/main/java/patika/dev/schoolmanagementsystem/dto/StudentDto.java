package patika.dev.schoolmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import patika.dev.schoolmanagementsystem.entity.abstracts.Person;


import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto extends Person {

    @NotBlank(message = "The student birth date is mandatory!!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String gender;

    @JsonIgnore
    private Set<CourseDto> courses;

}
