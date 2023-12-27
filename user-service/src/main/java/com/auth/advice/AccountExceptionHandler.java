/*
package com.auth.advice;


import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityDetails(Exception ex){

        ProblemDetail errorDetails=null;

        if(ex instanceof BadCredentialsException){
            errorDetails=ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401),ex.getMessage());
            errorDetails.setProperty("access_denied_reason","Authentication Failure");
          //  throw new BadCredentialsException("Bad credentials");
        }
       */
/* if(ex instanceof DisabledException){
            errorDetails=ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403),ex.getMessage());
            errorDetails.setProperty("access_denied_reason","Authentication Failure");
         //   throw new DisabledException("This account has been disable");
        }*//*



        return errorDetails;
    }
}
*/
