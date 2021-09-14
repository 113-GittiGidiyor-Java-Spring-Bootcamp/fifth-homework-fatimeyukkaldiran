package patika.dev.schoolmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import patika.dev.schoolmanagementsystem.dto.PermanentInstructorDto;
import patika.dev.schoolmanagementsystem.entity.InstructorServiceTransactionLogger;
import patika.dev.schoolmanagementsystem.entity.PermanentInstructor;
import patika.dev.schoolmanagementsystem.entity.enumeration.TransactionType;
import patika.dev.schoolmanagementsystem.exceptions.BadRequestException;
import patika.dev.schoolmanagementsystem.exceptions.InstructorNotFoundException;
import patika.dev.schoolmanagementsystem.mappers.PermanentInstructorMapper;
import patika.dev.schoolmanagementsystem.repository.PermanentInstructorRepository;
import patika.dev.schoolmanagementsystem.repository.TransactionLoggerRepository;
import patika.dev.schoolmanagementsystem.utils.ClientRequestInfo;
import patika.dev.schoolmanagementsystem.utils.InstructorValidatorUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermanentInstructorService {

    private final PermanentInstructorRepository instructorRepository;
    private final PermanentInstructorMapper instructorMapper;
    private final ClientRequestInfo clientRequestInfo;

    @Autowired
    private  TransactionLoggerRepository transactionLoggerRepository;


    public Optional<PermanentInstructor> saveInstructor(PermanentInstructorDto instructorDto){

       boolean isExists = instructorRepository.selectExistPhoneNumber(instructorDto.getPhoneNumber());
        if (isExists) {
            throw new BadRequestException("Permanent Instructor with phone umber:" + instructorDto.getPhoneNumber() + "is already exists!");
        }


        PermanentInstructor instructor = instructorMapper.mapFromPermanentDtoToPermanentInstructor(instructorDto);
        return Optional.of(instructorRepository.save(instructor));
    }

    @Transactional
    public PermanentInstructor decreaseSalary(long instructorId, int transactionPercent,TransactionType transactionType){
        PermanentInstructor instructor = this.findInstructorById(instructorId);
        double salaryBeforeUpdate = instructor.getFixedSalary();
        instructor.setFixedSalary(instructor.getFixedSalary() - (instructor.getFixedSalary()*transactionPercent/100));
        instructorRepository.save(instructor);
       this.saveTransactionToDatabase(instructor, transactionPercent,TransactionType.DECREASING,salaryBeforeUpdate);
        return instructor;
    }
    @Transactional
    public PermanentInstructor increaseSalary(long instructorId,int transactionPercent,TransactionType transactionType){
        PermanentInstructor instructor = this.findInstructorById(instructorId);
        double salaryBeforeUpdate = instructor.getFixedSalary();
        instructor.setFixedSalary(instructor.getFixedSalary() + (instructor.getFixedSalary()*transactionPercent/100));
        instructorRepository.save(instructor);
        this.saveTransactionToDatabase(instructor,transactionPercent,TransactionType.INCREASING, salaryBeforeUpdate);
        return instructor;
    }

    public PermanentInstructor findInstructorById(long instructorId){
        PermanentInstructor foundInstructor = (PermanentInstructor) instructorRepository.findById(instructorId)
                .orElseThrow(() -> new InstructorNotFoundException(String.format("Instructor with ID: %d could not found!", instructorId)));
        return foundInstructor;
    }

    private void saveTransactionToDatabase(PermanentInstructor instructor, int transactionPercent, TransactionType transactionType,double salaryBeforeUpdate) {
        InstructorServiceTransactionLogger transactionLogger = new InstructorServiceTransactionLogger();
        transactionLogger.setInstructorId(instructor.getId());

        transactionLogger.setSalaryBeforeUpdate(salaryBeforeUpdate);
        transactionLogger.setSalaryAfterUpdate(instructor.getFixedSalary());
        transactionLogger.setTransactionType(transactionType);
        transactionLogger.setTransactionPercent(transactionPercent);
        transactionLogger.setTransactionDateTime(LocalDate.now());
        transactionLogger.setClientURL(clientRequestInfo.getClientURL());
        transactionLogger.setClientIPAddress(clientRequestInfo.getClientIPAddress());
        transactionLogger.setSessionActivityID(clientRequestInfo.getSessionActivityID());
        this.transactionLoggerRepository.save(transactionLogger);
    }

    public Page<List<InstructorServiceTransactionLogger>> getAllTransactionsWithDate(String transactionDate, Integer page, Integer size, Pageable pageable) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        InstructorValidatorUtil.validateTransactionDate(transactionDate, formatter);
        LocalDate transactionDateResult = LocalDate.parse(transactionDate, formatter);
        if(page != null && size != null){
            pageable = PageRequest.of(page, size);
        }
        return this.transactionLoggerRepository.findAllTransactionByTransactionDate(transactionDateResult, pageable);
    }

}
