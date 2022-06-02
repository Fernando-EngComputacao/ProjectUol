package code.font.project.handler;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.h2.security.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandlerError {

	@Autowired
	MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ExceptionResponse badRequest(MethodArgumentNotValidException exception) {

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		String message = "field["+fieldErrors.get(0).getField()+"]: "+ messageSource.getMessage(fieldErrors.get(0), LocaleContextHolder.getLocale());
		ExceptionResponse erro = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), message);
		return erro;
	}
	
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(AuthenticationException.class)
	public ExceptionResponse unauthorized() {
		ExceptionResponse erro = new ExceptionResponse(HttpStatus.FORBIDDEN.value(), "FORBIDDEN");
		return erro;
	}

	
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	@ExceptionHandler(AccessDeniedException.class)
	public ExceptionResponse forbidden(){
		ExceptionResponse erro = new ExceptionResponse(HttpStatus.FORBIDDEN.value(), "FORBIDDEN");
		return erro;
	} 
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND) 
	@ExceptionHandler(RuntimeException.class)
	public ExceptionResponse notFound(){
		ExceptionResponse erro = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "NOT FOUND");
		return erro;
	}
	
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ExceptionResponse notAllowed() {
		ExceptionResponse erro = new ExceptionResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), "NOT ALLOWED");
		return erro;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ExceptionResponse errorInternal(Exception exception){
		ExceptionResponse erro = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL SERVER ERROR");
		return erro;
	}
}
