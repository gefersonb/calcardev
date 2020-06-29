package com.pack.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pack.converter.CreditoConverter;
import com.pack.converter.SpPessoaConverter;
import com.pack.entity.Credito;
import com.pack.entity.Pessoa;
import com.pack.pojo.CreditoPojo;
import com.pack.pojo.SpPessoaPojo;
import com.pack.repository.CreditoRepository;
import com.pack.repository.SpPessoaRepository;
import com.pack.utils.exception.CustomValidationException;

@Stateless
public class CreditoDAO implements CreditoRepository {
	
	final private Double RENDA_BASICA = 500.;
	final private String APROVADO = "A";
	final private String NEGADO = "N";
	final private String TXT_MOTIVO_RENDA = "Renda baixa";
	final private String TXT_MOTIVO_POLITICA = "Reprovado pela política de crédito";
	final private String TXT_MOTIVO_01 = "Entre 100 - 500";
	final private String TXT_MOTIVO_02 = "Entre 500 - 1000";
	final private String TXT_MOTIVO_03 = "Entre 1000 - 1500";
	final private String TXT_MOTIVO_04 = "Entre 1500 - 2000";
	final private String TXT_MOTIVO_05 = "Superior 2000";
	final private Double[] FAIXA_VALOR = {1000., 1500., 2000.}; 
	

	@PersistenceContext(unitName = "desafio-pu")
	private EntityManager em;

	public Pessoa getPessoa(Long codigo) throws CustomValidationException {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT p FROM Pessoa p WHERE p.codigo = " + codigo);
		TypedQuery<Pessoa> query = em.createQuery(jpql.toString(), Pessoa.class);
		List<Pessoa> lista = query.getResultList();
		if(lista.size() > 0)
			return lista.get(0);
		return null;
	}
	
	@Override
	public CreditoPojo salvar(CreditoPojo obj) throws CustomValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Long codigo) throws CustomValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CreditoPojo> listAll() throws CustomValidationException {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT c FROM Credito c JOIN c.pessoa p");
		TypedQuery<Credito> query = em.createQuery(jpql.toString(), Credito.class);
		List<Credito> lista = query.getResultList();
		return CreditoConverter.toPojo(lista);
	}

	@Override
	public CreditoPojo get(Long codigo) throws CustomValidationException {
		// TODO Auto-generated method stub
		return null;
	}
/*
	private ResultadoAnalise analisar(SpPessoaPojo o) {
		ResultadoAnalise r = new ResultadoAnalise();
		r.setLimiteInicial(0.);
		r.setLimiteFinal(0.);
		r.setResultado(NEGADO);
		Double calcRenda = o.getRenda() / (o.getDependentes() +1);
		
		if (o.getRenda() < RENDA_BASICA) 
			r.setMotivo(TXT_MOTIVO_RENDA);
		else if (calcRenda < 500) 
			r.setMotivo(TXT_MOTIVO_POLITICA);
		else if ("D".equals(o.getEcivil()) && calcRenda < 1000)
			r.setMotivo(TXT_MOTIVO_POLITICA);
		else {
			r.setResultado(APROVADO);
			if (o.getIdade() < 18 || ("S".equals(o.getEcivil()) && o.getDependentes() > 0)) {
				r.setLimiteInicial(100.);
				r.setLimiteInicial(500.);
				r.setMotivo("Entre 100 e 500");
			} else if ("S".equals(o.getEcivil()))  {
				r.setLimiteInicial(500.);
				r.setLimiteInicial(1000.);
				r.setMotivo("Entre 500 e 1000");
			} else if (("C".equals(o.getEcivil()) || "V".equals(o.getEcivil())) && (calcRenda > 2000))  {
				r.setLimiteInicial(2000.);
				r.setLimiteInicial(2500.);
				r.setMotivo("Superior a 2000");
			} else if (("C".equals(o.getEcivil()) || "V".equals(o.getEcivil())) && (calcRenda > 1500))  {
				r.setLimiteInicial(1500.);
				r.setLimiteInicial(2000.);
				r.setMotivo("Entre 1500 e 2000");
			} else if (("C".equals(o.getEcivil()) || "V".equals(o.getEcivil())) && (calcRenda > 1000))  {
				r.setLimiteInicial(1000.);
				r.setLimiteInicial(1500.);
				r.setMotivo("Entre 1000 e 1500");
			} else if (("C".equals(o.getEcivil()) || "V".equals(o.getEcivil())) && (calcRenda > 500))  {
				r.setLimiteInicial(1000.);
				r.setLimiteInicial(1500.);
				r.setMotivo("Entre 1000 e 1500");
			}
		}
		
		return r;
	}
	*/
	
	private ResultadoAnalise analisar(SpPessoaPojo o) {
		ResultadoAnalise r = new ResultadoAnalise();
		r.setLimiteInicial(0.);
		r.setLimiteFinal(0.);
		r.setResultado(NEGADO);
		Double calcRenda = o.getRenda() / (o.getDependentes() +1);
		
		if (o.getRenda() <= RENDA_BASICA) 
			r.setMotivo(TXT_MOTIVO_RENDA);
		else if (calcRenda <= RENDA_BASICA || "D".equals(o.getEcivil()))
			r.setMotivo(TXT_MOTIVO_POLITICA);
		else if (o.getIdade() < 18) {
			r.setResultado(APROVADO);
			r.setLimiteInicial(100.);
			r.setLimiteInicial(500.);
			r.setMotivo(TXT_MOTIVO_01);
		}else if ("S".equals(o.getEcivil()) && o.getDependentes() > 0) {
			r.setResultado(APROVADO);
			r.setLimiteInicial(100.);
			r.setLimiteInicial(500.);
			r.setMotivo(TXT_MOTIVO_01);
		}else if ("S".equals(o.getEcivil()) && o.getDependentes() == 0) {
			r.setResultado(APROVADO);
			r.setLimiteInicial(500.);
			r.setLimiteInicial(1000.);
			r.setMotivo(TXT_MOTIVO_02);
		}else if ("C".equals(o.getEcivil()) || "V".equals(o.getEcivil())) {
			if(calcRenda <= 1000.) {
				r.setResultado(APROVADO);
				r.setLimiteInicial(500.);
				r.setLimiteInicial(1000.);
				r.setMotivo(TXT_MOTIVO_02);
			}else if(calcRenda <= 1500.) {
				r.setResultado(APROVADO);
				r.setLimiteInicial(1000.);
				r.setLimiteInicial(1500.);
				r.setMotivo(TXT_MOTIVO_03);
			}else if(calcRenda <= 2000.) {
				r.setResultado(APROVADO);
				r.setLimiteInicial(1500.);
				r.setLimiteInicial(2000.);
				r.setMotivo(TXT_MOTIVO_04);
			}else {
				r.setResultado(APROVADO);
				r.setLimiteInicial(2000.);
				r.setLimiteInicial(calcRenda);
				r.setMotivo(TXT_MOTIVO_05);
			}
		}
		
		return r;
	}
	
	
	@Override
	public CreditoPojo analisarCredito(SpPessoaPojo o) throws CustomValidationException {
		// TODO Auto-generated method stub
		
		ResultadoAnalise r = analisar(o);
		
		Pessoa p = getPessoa(o.getCodigo());
		
		
		Credito cred = new Credito();
		cred.setDependentes(o.getDependentes());
		cred.setDtInc(new Date());
		cred.setEcivil(o.getEcivil());
		cred.setIdade(o.getIdade());
		cred.setPessoa(p);
		cred.setRenda(o.getRenda());
		cred.setResultado(r.getResultado());
		cred.setMotivo(r.getMotivo());
		cred.setLimiteInicial(r.getLimiteInicial());
		cred.setLimiteFinal(r.getLimiteFinal());
		cred.setUf(o.getUf());
		em.persist(cred);
		return null;
	}

	@Override
	public List<CreditoPojo> listByPessoa(SpPessoaPojo pessoa) throws CustomValidationException {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT c FROM Credito c WHERE c.pessoa.codigo = " + pessoa.getCodigo());
		TypedQuery<Credito> query = em.createQuery(jpql.toString(), Credito.class);
		List<Credito> lista = query.getResultList();
		return CreditoConverter.toPojo(lista);
	}

}


class ResultadoAnalise {
	private String resultado;
	private String motivo;
	private Double limiteInicial;
	private Double limiteFinal;
	
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