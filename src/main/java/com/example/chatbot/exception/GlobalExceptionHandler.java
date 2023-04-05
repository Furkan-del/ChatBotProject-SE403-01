package com.example.chatbot.exception;

import com.example.chatbot.utils.ProblemDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {QuotaException.class})
    @ResponseStatus(code = HttpStatus.TOO_MANY_REQUESTS)
    public ModelAndView handleQuotaException(QuotaException quotaException) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", quotaException.getMessage());
      /*  modelAndView.setViewName("errorPage");
      */  return modelAndView;
    }
}
