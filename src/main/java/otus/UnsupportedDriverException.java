package otus;

public class UnsupportedDriverException extends RuntimeException{
    public UnsupportedDriverException(WebDriverFactory.DriverType type) {
        super(type + " unsupported driver");
    }
}
