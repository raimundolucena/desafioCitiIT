package com.citibank.projeto.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citibank.projeto.model.Pessoa;
import com.citibank.projeto.repository.PessoaRepository;
import com.citibank.projeto.service.exception.PessoaException;
import com.citibank.projeto.service.exception.RecursoNaoEncontradoException;

@Service
public class PessoaService {

	private PessoaRepository pessoaRepository;	
	
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	//paginado
	public Page<Pessoa> findAll(Pageable pageable){
		Page<Pessoa> list = pessoaRepository.findAll(pageable);		
		return list;
	}	
	
	public Pessoa getById(Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		Pessoa p = pessoa.orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa não encontrada"));
		return p;
	}
	
	public Pessoa create (Pessoa pessoa) {
		try {
			Pessoa p = new Pessoa();
			p.setNameOwner(pessoa.getNameOwner());
			
			pessoaRepository.save(p);
			return p;
		}catch(DataIntegrityViolationException e) {
			throw new PessoaException("Pessoa: "+ pessoa.getNameOwner()+" já existe na base da dados");
		}		
	}
	
	@Transactional
	public Pessoa update(Pessoa pessoa, Long id) {		
		try {
			Optional<Pessoa> p = pessoaRepository.findById(id);
			p.get().setNameOwner(pessoa.getNameOwner());		
			pessoaRepository.save(p.get());			
			return p.get();			
		}catch(EntityNotFoundException e ) {
			throw new RecursoNaoEncontradoException("Id: "+id+" não foi encontrado");
		}catch(NoSuchElementException e ) {
			throw new RecursoNaoEncontradoException("Id: "+id+" não foi encontrado");
		}
	}
	
	@Transactional
	public void delete(Long id) {
		try {			 
			pessoaRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException("Id: "+id+" não foi encontrado");
		}catch(DataIntegrityViolationException e) {
			throw new PessoaException("Integridade violada!");
		}
	}
	
}
