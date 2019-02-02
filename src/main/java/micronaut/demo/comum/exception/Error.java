package micronaut.demo.comum.exception;

public class Error {

    String error;

    public Error(String message) {
        this.error = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
