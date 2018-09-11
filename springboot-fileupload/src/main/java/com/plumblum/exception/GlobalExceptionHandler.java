package com.plumblum.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @Auther: cpb
 * @Date: 2018/9/3 19:54
 * @Description:
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    // 上传错误的跳转提示
    @ExceptionHandler(MultipartException.class)
    public String handleError(MultipartException e, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message",e.getCause().getMessage());
        return "redirect:/results";
    }
}
