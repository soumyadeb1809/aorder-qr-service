package in.aorder.qr.exception;


import in.aorder.qr.dto.ErrorResponse;
import in.aorder.qr.model.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LogManager.getLogger(ApiExceptionHandler.class);

    /**
     * Default exception handler for any uncaught exceptions.
     *
     * @param e Exception
     * @param request WebRequest object
     * @return ResponseEntity with ErrorResponse
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleDefault(Exception e, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(details);
        errorResponse.setMessage(ResponseMessage.SERVER_ERROR);

        LOG.error("Unexpected error", e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception handler to handle ResourceNotFoundException.
     *
     * @param e Exception
     * @return ResponseEntity with ErrorResponse
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException e) {
        List<String> details = new ArrayList<>();
        details.add(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(details);
        errorResponse.setMessage(ResponseMessage.NOT_FOUND);

        LOG.error(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
