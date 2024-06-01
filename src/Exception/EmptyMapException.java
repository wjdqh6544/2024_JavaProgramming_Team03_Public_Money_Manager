package Exception;

public class EmptyMapException extends RuntimeException {
    public EmptyMapException(){
        super("Map is empty.");
    }
}
