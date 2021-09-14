package patika.dev.schoolmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import patika.dev.schoolmanagementsystem.entity.ErrorExceptionLogger;
import patika.dev.schoolmanagementsystem.repository.ErrorExceptionLoggerRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ErrorExceptionLoggerService {

    private ErrorExceptionLoggerRepository loggerRepository;

    @Autowired
    public ErrorExceptionLoggerService(ErrorExceptionLoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }

    @Transactional
    public Optional<ErrorExceptionLogger> save(ErrorExceptionLogger errorExceptionLogger){
        return Optional.of(loggerRepository.save(errorExceptionLogger));
    }

    @Transactional(readOnly = true)
    public Optional<List<ErrorExceptionLogger>> findAll(){
        return Optional.of(loggerRepository.findAll());
    }

    @Transactional(readOnly = true)
    public Optional<List<ErrorExceptionLogger>> findAllByStatus(int status){
        return Optional.of(loggerRepository.getAllByStatus(status));
    }

    @Transactional(readOnly = true)
    public Optional<List<ErrorExceptionLogger>> getByTimestampBetween(Date date){
        return Optional.of(loggerRepository.findByExceptionDate(date));
    }
}
