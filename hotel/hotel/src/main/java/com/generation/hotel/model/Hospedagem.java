package com.generation.hotel.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table (name = "tb_hospedagem")

public class Hospedagem {
	
	@Column (name = "cd_hospedagem")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoHospedagem;
	
	//OneToOne - faz o Relacionamento de UM para UM com a tabela Quarto
	//CascadeType.ALL = se apagar um item que é linkado na tabela(nesse caso o quarto) Ele ira apagar todas as hospedagens que utilizaram aquele dado
	//CascadeType.PERSIST nao deixa apagar um item (QUARTO) que esta linkado a outro (HOSPEDAGEM)
	@OneToOne (cascade = CascadeType.PERSIST)
	//name = da um nome para o atributo abaixo 
	//referencedColumnName - pega o atributo abaixo dessa Tabela e associa com o Campo "cd_quarto" da tabela Quarto (tipo abaixo)
	@JoinColumn(name = "cd_quarto" , referencedColumnName = "cd_quarto")
	//Atributo do tipo quarto para fazer o relacionamento.
	private Quarto quarto;
		
	private Date dataCheckin = new java.sql.Date(System.currentTimeMillis());

	public int getCodigoHospedagem() {
		return codigoHospedagem;
	}

	public void setCodigoHospedagem(int codigoHospedagem) {
		this.codigoHospedagem = codigoHospedagem;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public Date getDataCheckin() {
		return dataCheckin;
	}

	public void setDataCheckin(Date dataCheckin) {
		this.dataCheckin = dataCheckin;
	}

	
	
}
