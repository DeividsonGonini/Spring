package com.biblioteca.generation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.generation.model.Livro;
import com.biblioteca.generation.repository.LivroRepository;

@RestController
@RequestMapping("/livros")
@CrossOrigin("*")

public class LivroController {
	@Autowired
	private LivroRepository repository;

//Listando Todos
	@GetMapping
	public ResponseEntity<List<Livro>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

//Listando por ID
	@GetMapping("/{id}")
	public ResponseEntity<Object> GetById(@PathVariable long id) {
		Optional<Livro> livro = repository.findById(id);
		if (livro.isPresent()) {
			return ResponseEntity.ok(livro.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
	}

//Buscando pelo Titulo do Livro
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Livro>> GetByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}

//Inserindo Livro
	@PostMapping
	public ResponseEntity<Livro> post(@RequestBody Livro livro) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(livro));
	}

//Alterando Livro
	@PutMapping
	public ResponseEntity<Livro> put(@RequestBody Livro livro) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(livro));
	}

//Deletando Livro
	@DeleteMapping("/{id}")
	public ResponseEntity<Livro> delete(@PathVariable Long id) {
		Optional<Livro> livro = repository.findById(id);
		if (livro.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.badRequest().build();
	}

}
