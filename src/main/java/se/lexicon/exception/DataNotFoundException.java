package se.lexicon.exception;

public class DataNotFoundException extends RuntimeException {
    //...

    private final String requestId;


    public DataNotFoundException(String message, String requestId) {
        super(message);
        this.requestId = requestId;
    }

    public String info(){
        return " Message: " + getMessage() + " RequestId: " + requestId;
    }
}
