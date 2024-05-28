package Exception;

public class EmptyArrayListException extends RuntimeException {
    public EmptyArrayListException(){
        super("ArrayList is empty.");
    }
}
