package com.citibank.projeto.controller.handle;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.citibank.projeto.model.exception.ErroPadrao;
import com.citibank.projeto.service.exception.PessoaException;
import com.citibank.projeto.service.exception.RecursoNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandle {

	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<ErroPadrao> entidadeNaoEncontrada(RecursoNaoEncontradoException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroPadrao erro = new ErroPadrao();
		erro.setTimeStamp(Instant.now());
		erro.setStatus(status.value());
		erro.setError("Recurso não encontrado");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(erro);
		
	}
	
	@ExceptionHandler(PessoaException.class)
	public ResponseEntity<ErroPadrao> pessoaConflito(PessoaException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.CONFLICT;
		ErroPadrao erro = new ErroPadrao();
		erro.setTimeStamp(Instant.now());
		erro.setStatus(status.value());
		erro.setError("Pessoa com conflito");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(erro);
	}
}
