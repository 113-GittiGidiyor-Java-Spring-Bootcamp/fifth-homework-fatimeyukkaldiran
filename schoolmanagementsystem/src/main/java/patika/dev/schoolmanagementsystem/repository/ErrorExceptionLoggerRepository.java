package patika.dev.schoolmanagementsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import patika.dev.schoolmanagementsystem.entity.ErrorExceptionLogger;

import java.util.Date;
import java.util.List;

    @Repository
    public interface ErrorExceptionLoggerRepository extends JpaRepository<ErrorExceptionLogger, Long> {
        @Query(value = "SELECT l FROM ErrorExceptionLogger l where l.status = ?1")
        List<ErrorExceptionLogger> getAllByStatus(int status);

        List<ErrorExceptionLogger> findByExceptionDate(Date date);
    }

