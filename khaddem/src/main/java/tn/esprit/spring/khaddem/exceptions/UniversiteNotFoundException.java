package tn.esprit.spring.khaddem.exceptions;

public class UniversiteNotFoundException extends RuntimeException {
    public UniversiteNotFoundException(String message) {
        super(message);
    }
}