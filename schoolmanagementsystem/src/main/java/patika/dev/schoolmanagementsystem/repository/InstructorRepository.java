package patika.dev.schoolmanagementsystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import patika.dev.schoolmanagementsystem.entity.abstracts.Instructor;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {
}
