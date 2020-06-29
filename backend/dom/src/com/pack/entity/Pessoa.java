package com.pack.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.pack.jpa.BaseEntity;


@Entity
@Table(schema = "public", name = "pessoa")
public class Pessoa implements BaseEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1)
	@Column()
	private Long codigo;
	
	@Column(length = 150)
	private String nome;

	@Column(length = 1)
	private String sexo;
	
	@Column(length = 100)
	private String email;
	
	@Column()
	private Date nasc;
	
	@Column(length = 70)
	private String naturalidade;
	
	@Column(length = 40)
	private String nacionalidade;
	
	@Column(length = 14)
	private String cpf;
	
	@Column(length = 1)
	private String ecivil;
	
	@Column(length = 2)
	private String uf;

	@Column()
	private Integer dependentes;

	@Column()
	private Double renda;
	
	@Column()
	private Date dtInc;
	
	@Column()
	private Date dtAlt;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getNasc() {
		return nasc;
	}

	public void setNasc(Date nasc) {
		this.nasc = nasc;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEcivil() {
		return ecivil;
	}

	public void setEcivil(String ecivil) {
		this.ecivil = ecivil;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getDependentes() {
		return dependentes;
	}

	public void setDependentes(Integer dependentes) {
		this.dependentes = dependentes;
	}

	public Double getRenda() {
		return renda;
	}

	public void setRenda(Double renda) {
		this.renda = renda;
	}

	public Date getDtInc() {
		return dtInc;
	}

	public void setDtInc(Date dtInc) {
		this.dtInc = dtInc;
	}

	public Date getDtAlt() {
		return dtAlt;
	}

	public void setDtAlt(Date dtAlt) {
		this.dtAlt = dtAlt;
	}

	
}
