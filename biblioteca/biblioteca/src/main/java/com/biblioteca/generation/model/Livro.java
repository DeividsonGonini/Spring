package com.biblioteca.generation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Livro")

public class Livro {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size (min = 5, max = 50 )
	private String isbn;
	
	@NotNull
	@Size (min = 2, max = 200 )
	private String titulo;
	
	@NotNull
	@Size (min = 5, max = 500 )
	private String descricao;
	
	@NotNull
	private Integer qtdLivro;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtdLivro() {
		return qtdLivro;
	}

	public void setQtdLivro(Integer qtdLivro) {
		this.qtdLivro = qtdLivro;
	}
	
	
	
	
}
