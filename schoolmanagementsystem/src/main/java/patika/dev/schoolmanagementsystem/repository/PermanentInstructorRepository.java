package patika.dev.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import patika.dev.schoolmanagementsystem.entity.PermanentInstructor;

@Repository
public interface PermanentInstructorRepository extends InstructorRepository {

    @Query("select CASE WHEN COUNT(p) >0 THEN TRUE ELSE FALSE END from PermanentInstructor p WHERE p.phoneNumber = ?1")
    boolean selectExistPhoneNumber(String phoneNumber);
}
