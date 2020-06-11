package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
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

//se trata de um controller
@RestController
//informando a URI (caminho)
@RequestMapping("/postagens")
//aceita requisições de qualquer origem (frontEnd)
@CrossOrigin("*")
public class PostagemController {

	// @Autowired Serviço de Injeçao de Dependencias do Spring
	// faz o Spring se encarregar de instanciar essa interface (PostagemRepository)
	@Autowired
	private PostagemRepository repository;

	// Criando Metodos

	//FINDALL
	// sempre que vier uma requisiçao externa (alguem que consome essa API) atraves
	// da URI acima atraves do metodo GET ele ira disparar o Metodo Abaixo
	@GetMapping
	// ResponseEntity retornara uma lista do tipo postagem
	// GetAll - nome do metodo, nao recebe nada como parametro
	// Retorno do método - receberemos um objeto do tipo ResponseEntity, passando OK
	// (resposta http)
	// dentro da body faremos a requisição de todas as postagens, chamando o
	// repository e o metodo findAll dentro dele
	public ResponseEntity<List<Postagem>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	//FINDBYID
	// Definindo que sera um metrodo GET e que recebera um ID como parametro
	@GetMapping("/{id}")
	//Como nao utilizamos o List, ele trara apenas UM objeto
	//@PathVariable para capturar uma variavel (no caso o ID) do caminho da URI
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		return repository.findById(id)
				//map para receber algo populado
				.map(resp -> ResponseEntity.ok(resp))
				//caso o valor venha diferente do esperado (Nulo) ou caso ocorra erro na requisição
				.orElse(ResponseEntity.notFound().build());
				}

	//FINDBYTITULO
	//passaremos um subcaminho para o GetMapping nao se confundir com parametro
	//o PathVariable nao entendo o Nome apos a barra para ele é apenas um atributo
	@GetMapping("/titulo/{titulo}")
	//GetByTitulo é o nome que daremos para o metoro
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	//POST
	@PostMapping
	//@RequestBody - como o dado é grande e nao precisaremos mostrar para o cliente, iremos capturar pela Body
	//O objeto chega pela body(corpo) da requisição e sera "serealizado"
	public ResponseEntity<Postagem> post(@RequestBody Postagem postagem){
		//.status muda o status para 201 com o CREATED e passa um ENUM devolvendo o status
		//salvar na base de dados e  devolve isso na Body chamando o repository com o metodo save e a entidade postagem
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	
	
	//PUT
	@PutMapping
	public ResponseEntity<Postagem> put(@RequestBody Postagem postagem){
		//.status muda para 200 com o OK e passa um ENUM devolvendo o status
		//salvar na base de dados e  devolve isso na Body chamando o repository com o metodo save e a entidade postagem
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	
}
