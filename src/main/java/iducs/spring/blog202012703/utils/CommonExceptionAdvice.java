package iducs.spring.blog202012703.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionAdvice {
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public String errorException(Model model, Exception e) {
		logger.info("@ControllerAdvice 방식 \n###exception: " + e.getMessage());
		model.addAttribute("exception", e);
		
		return "/main/error";
	}
}
