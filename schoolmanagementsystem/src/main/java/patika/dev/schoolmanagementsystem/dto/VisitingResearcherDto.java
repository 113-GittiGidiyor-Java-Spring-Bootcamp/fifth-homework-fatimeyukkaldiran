package patika.dev.schoolmanagementsystem.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitingResearcherDto extends InstructorDto {
    private double hourlySalary;
    private int totalHour;

    @ApiModelProperty(hidden = true)
    private double totalSalary ;

    public double getTotalSalary() {
        return totalSalary ;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = hourlySalary*totalHour;
    }
}
