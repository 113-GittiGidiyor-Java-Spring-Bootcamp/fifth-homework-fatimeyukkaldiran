package patika.dev.schoolmanagementsystem.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import patika.dev.schoolmanagementsystem.entity.ErrorExceptionLogger;
import patika.dev.schoolmanagementsystem.service.ErrorExceptionLoggerService;

import java.sql.Date;
import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {


    ErrorExceptionLoggerService errorExceptionLoggerService;

    @Autowired
    public GlobalExceptionHandler(ErrorExceptionLoggerService errorExceptionLoggerService) {
        this.errorExceptionLoggerService = errorExceptionLoggerService;
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorExceptionLogger> handleException(BadRequestException exc){
        ErrorExceptionLogger response = getErrorResponse(HttpStatus.BAD_REQUEST, exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({CourseIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorExceptionLogger> handleException(CourseIsAlreadyExistException exc){
        ErrorExceptionLogger response = getErrorResponse(HttpStatus.BAD_REQUEST, exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({StudentAgeNotValidException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ErrorExceptionLogger> handleException(StudentAgeNotValidException exc){
        ErrorExceptionLogger response = getErrorResponse(HttpStatus.NOT_ACCEPTABLE, exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
    }


    @ExceptionHandler({InstructorNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorExceptionLogger> handleException(InstructorNotFoundException exc){
        ErrorExceptionLogger response = getErrorResponse(HttpStatus.NOT_FOUND, exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InstructorIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorExceptionLogger> handleException(InstructorIsAlreadyExistException exc){
        ErrorExceptionLogger response = getErrorResponse(HttpStatus.BAD_REQUEST, exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StudentNumberForOneCourseExceededException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ErrorExceptionLogger> handleException(StudentNumberForOneCourseExceededException exc){
        ErrorExceptionLogger response = getErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    private ErrorExceptionLogger getErrorResponse(HttpStatus httpStatus,String message) {
        ErrorExceptionLogger response = new ErrorExceptionLogger();
        response.setStatus(httpStatus.value());
        response.setMessageError((message));
        response.setExceptionDate(Date.valueOf(LocalDate.now()));

        return errorExceptionLoggerService.save(response).get();
    }
}
