package com.generation.hotel.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.client.HttpStatusCodeException;

import com.generation.hotel.model.Usuario;
import com.generation.hotel.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@GetMapping
	public ResponseEntity<List<Usuario>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	
	//Codigo com Mensagem Personalizada para erro 404 (Não Encontrado)
	//http//localhost:8080/usuario/{codigo} -- substitua {codigo} pelo ID (chave primaria)
	@GetMapping("/{codigo}")
	public ResponseEntity<Object> GetById(@PathVariable long codigo){
		Optional<Usuario> usuario = repository.findById(codigo);
		if(usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		
		//return repository.findById(codigo).map(resp -> ResponseEntity.ok(resp))
			//	.orElse(ResponseEntity.notFound().build());
	}

	//http//localhost:8080/usuario/nome/{nome} -- substitua {nome} pelo nome do usuario
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>> GetByName(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	//http//localhost:8080/usuario/
	@PostMapping
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}
	
	//http//localhost:8080/usuario/{codigo} -- substitua {codigo} pelo ID (chave primaria)
	@PutMapping
	public ResponseEntity<Usuario> Put(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
	}

	//http//localhost:8080/usuario/{codigo} -- substitua {codigo} pelo ID (chave primaria)
	@DeleteMapping("/{codigo}")
	public void Delete(@PathVariable long codigo) {
		repository.deleteById(codigo);
	}
	
}
