package patika.dev.schoolmanagementsystem.entity;

import lombok.*;
import patika.dev.schoolmanagementsystem.entity.abstracts.Instructor;

import javax.persistence.Entity;
import javax.persistence.PostLoad;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VisitingResearcher extends Instructor {
    private double hourlySalary;
    private int totalHour;
    private double totalSalary ;

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }
/*  @PostLoad
    private void getF(){
     this.totalSalary = getHourlySalary()*getTotalHour();
 }*/
}
