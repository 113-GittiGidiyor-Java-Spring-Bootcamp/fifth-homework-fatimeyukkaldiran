package patika.dev.schoolmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import patika.dev.schoolmanagementsystem.entity.abstracts.Person;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id") //serileştirme sırasında nesne kimliğinin kullanılacağını belirtmek için kullanılır
public class Student extends Person{

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String gender;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Course> courses = new HashSet<>();
}
