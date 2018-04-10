package br.com.inventarioApi.exceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice //observa toda a aplicação
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	@Autowired
	private MessageSource messageSource;
	
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String msgUsuario = "Mensagem Inválida";
		String msgDesenvolvedor = ex.getCause() != null ?ex.getCause().toString():ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(msgDesenvolvedor, msgUsuario));

		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros = criarListaDeErros(ex.getBindingResult());

		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	private List<Erro> criarListaDeErros(BindingResult bindingResult){
		List<Erro> erros = new ArrayList<>();
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			String msgDesenvolvedor = fieldError.toString();
			String msgUsuario = "O campo " + fieldError.getField() + " - não pode ser null";
			
			erros.add(new Erro(msgDesenvolvedor,msgUsuario));
		}
		
		return erros;
	}
	
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> EmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		
		String msgUsuario = "Recurso não encontrado";
		String msgDesenvolvedor = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(msgDesenvolvedor, msgUsuario));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		
	}
	
	
	
	public static class Erro{
		
		private String mensagemDesenvolvedor;
		private String mensagemUsuario;
		
		public Erro(String mensagemDesenvolvedor, String mensagemUsuario) {
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
			this.mensagemUsuario = mensagemUsuario;
		}
		
		
		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
		public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}
		
		
		public String getMensagemUsuario() {
			return mensagemUsuario;
		}
		public void setMensagemUsuario(String mensagemUsuario) {
			this.mensagemUsuario = mensagemUsuario;
		}
		
	}

}










