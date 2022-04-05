package com.citibank.projeto.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.citibank.projeto.model.Pessoa;
import com.citibank.projeto.service.PessoaService;

@RestController
@RequestMapping("api/v1/pessoas")
public class PessoaController {

	private final PessoaService pessoaService;
	
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@GetMapping
	public ResponseEntity<Page<Pessoa>> getAll(Pageable pageable){
		 Page<Pessoa> listaPessoa = pessoaService.findAll(pageable);		
		return ResponseEntity.ok().body(listaPessoa);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getById(@PathVariable Long id) {
		Pessoa pessoa = pessoaService.getById(id);
		return ResponseEntity.ok(pessoa);
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa){
		Pessoa p = pessoaService.create(pessoa);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(p.getId()).toUri();	
		
		return ResponseEntity.created(location).body(p);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa, @PathVariable Long id){
		pessoa = pessoaService.update(pessoa, id);
		//return ResponseEntity.noContent().build();
		return ResponseEntity.ok().body(pessoa);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pessoa> delete(@PathVariable Long id){
		pessoaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
