package patika.dev.schoolmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import patika.dev.schoolmanagementsystem.entity.abstracts.BaseEntity;
import patika.dev.schoolmanagementsystem.entity.abstracts.Instructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Course extends BaseEntity {

    private String courseName;
    private String courseCode;
    private int creditScore;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(nullable = false, name = "instructor_id")
    private Instructor instructor;


    @ManyToMany(mappedBy = "courses",cascade = CascadeType.MERGE)
    private List<Student> students = new ArrayList<>();
}
