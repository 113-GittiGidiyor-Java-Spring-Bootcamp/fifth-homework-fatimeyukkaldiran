package patika.dev.schoolmanagementsystem.exceptions;

public class InstructorNotFoundException extends RuntimeException{
    public InstructorNotFoundException(String message){
        super(message);
    }
}
