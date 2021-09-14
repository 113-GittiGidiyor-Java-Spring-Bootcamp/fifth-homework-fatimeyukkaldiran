package patika.dev.schoolmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import patika.dev.schoolmanagementsystem.entity.abstracts.Person;

import javax.validation.constraints.NotBlank;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class InstructorDto extends Person {
    @NotBlank(message = "The phone number is mandatory!!")
    @ApiModelProperty(example = "5578978965")
    private String phoneNumber;
    @JsonIgnore
    private Set<CourseDto> courses ;
}
