package com.pack.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.pack.jpa.BaseEntity;


@Entity
@Table(schema = "public", name = "credito")
public class Credito implements BaseEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_credito")
    @SequenceGenerator(name = "seq_credito", sequenceName = "seq_credito", allocationSize = 1)
	@Column()
	private Long codigo;

	@Column()
	private Date dtInc;

	//@Column(name = "idPessoa")
	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "codigo", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idpessoa", nullable = false)
	private Pessoa pessoa;
	
	@Column(length = 1)
	private String resultado;

	@Column(length = 1)
	private String ecivil;
	
	@Column(length = 2)
	private String uf;

	@Column()
	private Integer dependentes;

	@Column()
	private Double renda;
	
	@Column
	private Integer idade;
	
	@Column
	private String motivo;
	
	@Column
	private Double limiteInicial;
	
	@Column
	private Double limiteFinal;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getDtInc() {
		return dtInc;
	}

	public void setDtInc(Date dtInc) {
		this.dtInc = dtInc;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Double getLimiteInicial() {
		return limiteInicial;
	}

	public void setLimiteInicial(Double limiteInicial) {
		this.limiteInicial = limiteInicial;
	}

	public Double getLimiteFinal() {
		return limiteFinal;
	}

	public void setLimiteFinal(Double limiteFinal) {
		this.limiteFinal = limiteFinal;
	}
	
}
