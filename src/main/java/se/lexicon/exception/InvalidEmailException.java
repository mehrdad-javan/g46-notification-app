package se.lexicon.exception;

public class InvalidEmailException extends Exception {
    // to customise this class we can add more fields...

    public InvalidEmailException(String message) {
        super(message);
    }

}
