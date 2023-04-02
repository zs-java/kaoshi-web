package com.xzcoder.kaoshi.exception;

/**
 * ThrowsException
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class ThrowsException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String message;

    public ThrowsException() {
        super();
    }

    public ThrowsException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
