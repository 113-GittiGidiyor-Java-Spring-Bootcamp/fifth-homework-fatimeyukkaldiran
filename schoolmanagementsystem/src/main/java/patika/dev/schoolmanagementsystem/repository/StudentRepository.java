package patika.dev.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import patika.dev.schoolmanagementsystem.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {
    @Query("SELECT " +
            "  CASE " +
            "   WHEN " +
            "       COUNT(s)>0 " +
            "   THEN " +
            "       TRUE " +
            "   ELSE " +
            "       FALSE " +
            "   END " +
            "FROM Student s " +
            "WHERE s.id = ?1")
    boolean selectExistsStudent(long id);

    @Query("SELECT COUNT(s) FROM Student s")
    int numberOfStudents();

    List<Student> findStudentByName(String studentName);

    @Query("select s.gender , count(s.gender) from Student s group by s.gender")
    List<?> getGenderWithGrouping();
}
