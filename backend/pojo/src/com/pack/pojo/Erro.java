package com.pack.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Erro implements Serializable {
	private static final long serialVersionUID = 4806557731520877788L;

	@JsonProperty("co_erro")
	private String coErro;
	@JsonProperty("co_retorno")
	private String coRetorno;
	@JsonProperty("ds_retorno")
	private String dsRetorno;
	@JsonProperty("ds_valor")
	private String dsValor;
	@JsonProperty("ds_field")
	private String dsField;

	public Erro() {

	}

	public Erro(String coRetorno, String dsRetorno, String dsValor) {
		this.setCoRetorno(coRetorno);
		this.setDsRetorno(dsRetorno);
		this.setDsValor(dsValor);
	}

	public Erro(String coRetorno, String dsRetorno) {
		this.setCoRetorno(coRetorno);
		this.setDsRetorno(dsRetorno);
	}

	public String getCoRetorno() {
		return coRetorno;
	}

	public void setCoRetorno(String coRetorno) {
		this.coRetorno = coRetorno;
	}

	public String getDsRetorno() {
		return dsRetorno;
	}

	public void setDsRetorno(String dsRetorno) {
		this.dsRetorno = dsRetorno;
	}

	public String getDsValor() {
		return dsValor;
	}

	public void setDsValor(String dsValor) {
		this.dsValor = dsValor;
	}

	public String getCoErro() {
		return coErro;
	}

	public void setCoErro(String coErro) {
		this.coErro = coErro;
	}

	public String getDsField() {
		return dsField;
	}

	public void setDsField(String dsField) {
		this.dsField = dsField;
	}

}
