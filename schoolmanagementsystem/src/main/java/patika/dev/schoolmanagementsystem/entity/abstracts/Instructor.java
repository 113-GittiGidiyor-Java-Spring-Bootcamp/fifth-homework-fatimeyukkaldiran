package patika.dev.schoolmanagementsystem.entity.abstracts;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import patika.dev.schoolmanagementsystem.entity.Course;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Instructor extends Person{

    private String phoneNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "instructor",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();
}
