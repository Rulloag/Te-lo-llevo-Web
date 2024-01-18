package cl.duoc.rulloa.telollevobackend.endpoint;

import cl.duoc.rulloa.telollevobackend.endpoint.response.BaseResponse;
import cl.duoc.rulloa.telollevobackend.endpoint.response.MessageResponse;
import cl.duoc.rulloa.telollevobackend.service.exception.FoundException;
import cl.duoc.rulloa.telollevobackend.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerHandle {
    public static final String ERROR_CODE_EXCEPTION = "E00";
    public static final String ERROR_CODE_REQUIRED_REQUEST = "E01";
    public static final String ERROR_CODE_REQUEST_METHOD_NOT_SUPPORTED = "E02";
    public static final String ERROR_CODE_METHOD_ARGUMENT_NOT_VALID = "E03";
    public static final String ERROR_CODE_EXISTS_ENTITY = "E04";
    public static final String ERROR_CODE_NOT_FOUND_ENTITY = "E05";
    private static final String COMMON_EXCEPTION_MESSAGE_LOG = "An exception has occurred: {}";
    private static final String COMMON_EXCEPTION_MESSAGE =
            "An error has occurred, please try again. If the problem persists please inform the email %s";

    private static BaseResponse createFailureResponse(String errorCode, String message) {
        return createFailureResponse(errorCode, message, null);
    }

    private static BaseResponse createFailureResponse(String errorCode, String message, Object data) {
        return BaseResponse.builder()
                .failure(new MessageResponse(errorCode, message))
                .data(data)
                .build();
    }

    private void addDebugDetailsIfIsEnabled(Exception initialCause, BaseResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("Trace exception: ", initialCause);
            var sbDebug = new StringBuilder().append(initialCause.getMessage());
            var sbThrowable = new StringBuilder().append(initialCause.getClass().getSimpleName());
            while (initialCause.getCause() instanceof Exception cause && !cause.equals(initialCause)) {
                sbDebug.append(" -> ").append(cause.getMessage());
                sbThrowable.append(" -> ").append(cause.getClass().getSimpleName());
                initialCause = cause;
            }
            response.setDebug(new MessageResponse(response.getFailure().code(), sbDebug.toString()));
            response.setThrowable(sbThrowable.toString());
        }
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<BaseResponse> exception(Exception exception) {
        exception.printStackTrace();
        log.error(COMMON_EXCEPTION_MESSAGE_LOG, exception.getMessage());
        var message = String.format(COMMON_EXCEPTION_MESSAGE, "support@telollevo.cl");
        var response = createFailureResponse(ERROR_CODE_EXCEPTION, message);
        addDebugDetailsIfIsEnabled(exception, response);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<BaseResponse> httpMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        log.error(COMMON_EXCEPTION_MESSAGE_LOG, exception.getMessage());
        var response =
                createFailureResponse(ERROR_CODE_REQUIRED_REQUEST, "Required request body is missing");
        addDebugDetailsIfIsEnabled(exception, response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<BaseResponse> httpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception) {
        log.error(COMMON_EXCEPTION_MESSAGE_LOG, exception.getMessage());
        var response =
                createFailureResponse(ERROR_CODE_REQUEST_METHOD_NOT_SUPPORTED, exception.getMessage());
        addDebugDetailsIfIsEnabled(exception, response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<BaseResponse> methodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        log.error(COMMON_EXCEPTION_MESSAGE_LOG, exception.getMessage());
        var data = new HashMap<>();
        exception
                .getFieldErrors()
                .forEach(error -> data.put(error.getField(), error.getDefaultMessage()));
        var response =
                createFailureResponse(
                        ERROR_CODE_METHOD_ARGUMENT_NOT_VALID, "Request validation error", data);
        addDebugDetailsIfIsEnabled(exception, response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({FoundException.class})
    public ResponseEntity<BaseResponse> foundException(FoundException exception) {
        log.error(COMMON_EXCEPTION_MESSAGE_LOG, exception.getMessage());
        var response = createFailureResponse(ERROR_CODE_EXISTS_ENTITY, exception.getMessage());
        addDebugDetailsIfIsEnabled(exception, response);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<BaseResponse> notFoundException(NotFoundException exception) {
        log.error(COMMON_EXCEPTION_MESSAGE_LOG, exception.getMessage());
        var response = createFailureResponse(ERROR_CODE_NOT_FOUND_ENTITY, exception.getMessage());
        addDebugDetailsIfIsEnabled(exception, response);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
