package ing.com.storemanagementapi.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> handleException(ApiException exception) {
        //TODO: Add logger
        return new ResponseEntity<>(exception, exception.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiException exception = new ApiException(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Malformed JSON request!");
        return handleException(exception);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiValidationException exception = new ApiValidationException(
                HttpStatus.BAD_REQUEST,
                "Invalid fields");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            Object value = ((FieldError) error).getRejectedValue();
            String message = error.getDefaultMessage();
            exception.addException(fieldName, message, value);
        });
        return handleException(exception);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiException exception = new ApiException(
                HttpStatus.BAD_REQUEST,
                String.format("Missing %s parameter!", ex.getParameter()));
        return handleException(exception);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        ApiException exception = new ApiException(
                HttpStatus.BAD_REQUEST,
                String.format("Invalid path: %s!", ex.getRequestURL()));
        return handleException(exception);
    }

    @ExceptionHandler(ApiEntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(ApiEntityNotFoundException ex) {
        ApiException exception = new ApiException(HttpStatus.NOT_FOUND, ex.getMessage());
        return handleException(exception);
    }

    @ExceptionHandler(ApiUsernameNotFoundException.class)
    protected ResponseEntity<Object> handleUsernameNotFound(ApiUsernameNotFoundException ex) {
        ApiException exception = new ApiException(HttpStatus.NOT_FOUND, ex.getMessage());
        return handleException(exception);
    }

    @ExceptionHandler(ApiInvalidCredentialsException.class)
    protected ResponseEntity<Object> handleInvalidCredentials(ApiInvalidCredentialsException ex) {
        ApiException exception = new ApiException(HttpStatus.NOT_FOUND, ex.getMessage());
        return handleException(exception);
    }
}
