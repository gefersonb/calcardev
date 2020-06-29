package com.pack.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditoPojo {
	@JsonProperty
	private Long codigo;
	@JsonProperty
	private String dtInc;
	@JsonProperty
	private Long idPessoa;
	@JsonProperty
	private String resultado;
	@JsonProperty
	private String motivo;
	@JsonProperty
	private String ecivil;
	@JsonProperty
	private String uf;
	@JsonProperty
	private Integer dependentes;
	@JsonProperty
	private Double renda;
	@JsonProperty
	private Integer idade;
	@JsonProperty
	private String nome;
	@JsonProperty
	private String sexo;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDtInc() {
		return dtInc;
	}
	public void setDtInc(String dtInc) {
		this.dtInc = dtInc;
	}
	public Long getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
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
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
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
}
