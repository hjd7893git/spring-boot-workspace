package spring.springauthority.exce;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hjd on 2020-08-31 15:19
 * -->
 **/
@ControllerAdvice
public class Myexception {

    @ResponseBody
    @ExceptionHandler(value = AccessDeniedException.class)
    public String handle(AccessDeniedException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String handle(Exception e) {
        return "你没有权限哦！";
    }
}
