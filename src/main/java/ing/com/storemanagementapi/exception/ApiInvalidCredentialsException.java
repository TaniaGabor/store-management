package ing.com.storemanagementapi.exception;

public class ApiInvalidCredentialsException extends RuntimeException {

    @Override
    public String toString() {
        return "Invalid username or password!";
    }
}
