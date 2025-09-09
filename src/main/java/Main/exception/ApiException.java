package Main.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final int httpCode;

    public ApiException(String message, int httpCode) {
        super(message);
        this.httpCode = httpCode;
    }

}
