package patika.dev.schoolmanagementsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermanentInstructorDto extends InstructorDto {

    private double fixedSalary;


}
