package patika.dev.schoolmanagementsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import patika.dev.schoolmanagementsystem.entity.InstructorServiceTransactionLogger;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionLoggerRepository extends PagingAndSortingRepository<InstructorServiceTransactionLogger, Long> {
    @Query("SELECT i FROM InstructorServiceTransactionLogger i WHERE i.transactionDateTime = ?1")
    Page<List<InstructorServiceTransactionLogger>> findAllTransactionByTransactionDate(LocalDate transactionDate, Pageable pageable);
}

