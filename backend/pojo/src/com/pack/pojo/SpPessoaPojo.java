package com.pack.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.InputMismatchException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpPessoaPojo {

	private Long codigo;
	
	@Length(max=150, message="10002#Nao pode ser maior que 150")
	@NotBlank(message="10001#Nao pode ser nulo.")
	@JsonProperty("nome")
	private String nome;

	@Length(min=1, max=1, message="10003#Deve ser um caracter (M/F)")
	@Pattern(regexp = "^(M|F)$", message="10003#Deve ser um caracter (M/F)")
	@JsonProperty("sexo")
	private String sexo;
	
	//@Email(message="10004#Deve ser um endereço válido")
	@JsonProperty("email")
	private String email;

	@NotEmpty(message="10005#Deve ser uma data válida")
	@JsonProperty("nasc")
	private String nasc;
	
	@JsonProperty("naturalidade")
	private String naturalidade;

	@JsonProperty("nacionalidade")
	private String nacionalidade;

	@NotEmpty(message="10006#Nao pode ser nulo.")
	@JsonProperty("cpf")
	private String cpf;
	
	@JsonProperty("cpfValido")
	private String cpfValido;
	
	
	@JsonProperty("ecivil")
	@Length(min=1, max=1, message="10007#Deve ser um caracter (S/C/D/V)")
	@Pattern(regexp = "^(S|C|D|V)$", message="10007#Deve ser um caracter (S/C/D/V)")
	private String ecivil;
	
	@JsonProperty("uf")
	private String uf;
	
	
	@JsonProperty("dependentes")
	//@NotBlank(message="10008#Nao pode ser nulo.")
	//@NotEmpty(message="10008#Nao pode ser nulo.")
	//@Valid
	//@Min(0)
	private Integer dependentes;
	
	@JsonProperty("renda")
	private Double renda;
	
	@JsonProperty("idade")
	private Integer idade;
	

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

	public String getNasc() {
		return nasc;
	}

	public void setNasc(String nasc) {
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
		this.cpf = cpfFormat(cpf);
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
		/*
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date d = (Date)formatter.parse(this.getNasc());
			Date h = new Date();
			LocalDate birthDate = LocalDate.of(d.getYear(), d.getMonth(), d.getDay());
			LocalDate currentDate = LocalDate.of(h.getYear(), h.getMonth(), h.getDay());
			return Period.between(birthDate, currentDate).getYears();
		} catch (Exception e) {
			return 0;
		}
		*/
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date d = (Date)formatter.parse(this.getNasc());
			Date h = new Date();
			/*
			LocalDate birthDate = LocalDate.of(d.getYear(), d.getMonth(), d.getDay());
			LocalDate currentDate = LocalDate.of(h.getYear(), h.getMonth(), h.getDay());
			return Period.between(birthDate, currentDate).getYears();
			*/
			return Math.round(((h.getTime() - d.getTime())/31536000)/1000);
		} catch (Exception e) {
			return 0;
		}
	}

	public boolean isCpfValido() {
		String CPF = numbers(this.getCpf());
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48);

			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return false;
		}
	}

	private String cpfFormat(String x) {
		String cpfAux = numbers(x);
		
        if(cpfAux.length() == 11) {
            String result = "";
            int ct1 = 0;
            for(int i = 0; i < cpfAux.length(); i++) {
              result += cpfAux.charAt(i);
              ct1++;
              if(ct1 == 3){
                if(i > 7){
                  result += '-';
                }else{
                  result += '.';
                }
                ct1 = 0;
              }
            }
            return result;
          }
		
		return x;
	}
	
	private static boolean isDigit(char x) {
		return (
	            x == '0' ||
	            x == '1' ||
	            x == '2' ||
	            x == '3' ||
	            x == '4' ||
	            x == '5' ||
	            x == '6' ||
	            x == '7' ||
	            x == '8' ||
	            x == '9');
		}
	
	private static String numbers(String cpf) {
		String res = "";
		for(int i = 0; i < cpf.length(); i ++) {
			if(isDigit(cpf.charAt(i))) {
				res += cpf.charAt(i);
			}
		}
		return res;
	}

	public void setCpfValido(String cpfValido) {
		this.cpfValido = cpfValido;
	}
	
}
