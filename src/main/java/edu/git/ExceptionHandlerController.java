package edu.git;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

import javax.servlet.ServletException;
import java.lang.invoke.MethodHandles;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({RestClientException.class })
    @ResponseBody
    public ExceptionInformationResponse handleIncorrectRestRequestException(final Exception exception) {
        LOGGER.error(exception.getMessage());
        return new ExceptionInformationResponse(exception.getMessage(), "Current user repository was not found");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ServletException.class })
    @ResponseBody
    public ExceptionInformationResponse handleIncorrectRequestException(final Exception exception) {
        LOGGER.error(exception.getMessage());
        return new ExceptionInformationResponse(exception.getMessage(), "Incorrect request url");
    }


    private static class ExceptionInformationResponse {
        private final String message;
        private final String description;

        ExceptionInformationResponse(String message, String description){
            this.message = message;
            this.description = description;
        }

        public String getMessage() {
            return message;
        }

        public String getDescription() {
            return description;
        }
    }

}
