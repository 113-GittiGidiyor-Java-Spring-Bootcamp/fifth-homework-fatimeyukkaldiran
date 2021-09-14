package patika.dev.schoolmanagementsystem.entity;

import lombok.*;
import patika.dev.schoolmanagementsystem.entity.abstracts.Instructor;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PermanentInstructor extends Instructor {
    private double fixedSalary;
}
