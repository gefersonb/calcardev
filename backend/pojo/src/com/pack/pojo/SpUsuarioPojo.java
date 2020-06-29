package com.pack.pojo;

import java.util.Date;
import java.util.InputMismatchException;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpUsuarioPojo {

	private Long codigo;
	
	@Length(max=150, message="10022#Nao pode ser maior que 150")
	@NotBlank(message="10021#Nao pode ser nulo.")
	@JsonProperty("usuario")
	private String usuario;

	@Length(max=15, message="10023#Nao pode ser maior que 15")
	@NotBlank(message="10024#Nao pode ser nulo.")
	@JsonProperty("senha")
	private String senha;

	@Length(max=15, message="10025#Nao pode ser maior que 15")
	@NotBlank(message="10026#Nao pode ser nulo.")
	@JsonProperty("confirmaSenha")
	private String confirmaSenha;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
}
