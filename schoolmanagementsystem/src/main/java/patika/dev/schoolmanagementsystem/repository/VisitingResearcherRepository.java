package patika.dev.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patika.dev.schoolmanagementsystem.entity.VisitingResearcher;

@Repository
public interface VisitingResearcherRepository extends InstructorRepository{
}
