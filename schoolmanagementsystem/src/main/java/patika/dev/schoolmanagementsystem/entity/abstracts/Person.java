package patika.dev.schoolmanagementsystem.entity.abstracts;

import lombok.Data;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;


@Data
@MappedSuperclass
public abstract class Person extends BaseEntity{

    @NotBlank(message = "The name is mandatory!!")
    private String fullName;
    private String address;
}
