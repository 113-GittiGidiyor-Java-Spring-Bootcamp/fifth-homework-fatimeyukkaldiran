package patika.dev.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import patika.dev.schoolmanagementsystem.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {
}
