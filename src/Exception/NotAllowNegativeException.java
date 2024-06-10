package Exception;

public class NotAllowNegativeException extends RuntimeException {
    public NotAllowNegativeException(){
        super("Balance not allow negative.");
    }

}
