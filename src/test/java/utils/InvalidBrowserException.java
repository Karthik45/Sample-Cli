package utils;

public class InvalidBrowserException extends Throwable {

    public InvalidBrowserException(String browser) {
        super(String.format("Requested browser %s is invalid, provide a valid browser name",browser));
    }
}
