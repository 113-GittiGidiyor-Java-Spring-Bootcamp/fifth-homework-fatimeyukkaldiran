package patika.dev.schoolmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import patika.dev.schoolmanagementsystem.entity.abstracts.BaseEntity;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto extends BaseEntity {

    @NotBlank(message = "The course name is mandatory!!")
    private String courseName;
    private String courseCode;
    @NotBlank(message = "The credit score is mandatory!!")
    private int creditScore;

    @JsonIgnore
    private Set<StudentDto> students ;
    private long instructorId;

}
