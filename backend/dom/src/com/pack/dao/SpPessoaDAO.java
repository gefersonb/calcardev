package com.pack.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pack.converter.SpPessoaConverter;
import com.pack.entity.Pessoa;
import com.pack.pojo.SpPessoaPojo;
import com.pack.repository.SpPessoaRepository;
import com.pack.utils.exception.CustomException;
import com.pack.utils.exception.CustomValidationException;

@Stateless
public class SpPessoaDAO implements SpPessoaRepository {

	@PersistenceContext(unitName = "desafio-pu")
	private EntityManager em;

	@Override
	public SpPessoaPojo salvar(SpPessoaPojo obj) throws CustomValidationException {
		Pessoa pessoa = new Pessoa();
		
		if(obj.getCodigo() != null) {
			pessoa.setCodigo(obj.getCodigo());
			Pessoa pAux = this.getPessoa(pessoa.getCodigo());
			if(null != pAux)
				pessoa.setDtInc(pAux.getDtInc());
		}
		pessoa.setNome(obj.getNome().toUpperCase());
		pessoa.setSexo(obj.getSexo().toUpperCase());
		pessoa.setEmail(obj.getEmail());
		
		try {
			pessoa.setNasc(new SimpleDateFormat("dd/MM/yyyy").parse(obj.getNasc()));
		} catch (Exception e) {
			List<com.pack.pojo.Erro> erros = new ArrayList<>();
			com.pack.pojo.Erro erro = new com.pack.pojo.Erro();
			erro.setCoRetorno("9999");
			erro.setDsValor(obj.getNasc());
			erro.setDsRetorno("Formato invalido");
			erro.setDsField("nasc");
			erros.add(erro);
			throw new CustomValidationException(erros);
		}
		pessoa.setNaturalidade(obj.getNaturalidade().toUpperCase());
		pessoa.setNacionalidade(obj.getNacionalidade().toUpperCase());
		pessoa.setCpf(obj.getCpf());
		
		pessoa.setEcivil(obj.getEcivil().toUpperCase());
		pessoa.setUf(obj.getUf().toUpperCase());
		pessoa.setDependentes(obj.getDependentes());
		pessoa.setRenda(obj.getRenda());
		
		if(pessoa.getCodigo() == null) {
			pessoa.setDtInc(new Date());
			em.persist(pessoa);
		} else {
			pessoa.setDtAlt(new Date());
			em.merge(pessoa);
		}
				
		obj.setCodigo(pessoa.getCodigo());
		return obj;
	}

	@Override
	public List<SpPessoaPojo> listAll() throws CustomValidationException {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT p FROM Pessoa p");
		TypedQuery<Pessoa> query = em.createQuery(jpql.toString(), Pessoa.class);
		List<Pessoa> lista = query.getResultList();
		return SpPessoaConverter.toPojo(lista);
	}

	@Override
	public void excluir(Long codigo) throws CustomValidationException {
		Pessoa pessoa = this.getPessoa(codigo);
		if(null != pessoa)
			em.remove(pessoa);
	}

	@Override
	public SpPessoaPojo get(Long codigo) throws CustomValidationException {
		return null;
	}
	
	public Pessoa getPessoa(Long codigo) throws CustomValidationException {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT p FROM Pessoa p WHERE p.codigo = " + codigo);
		TypedQuery<Pessoa> query = em.createQuery(jpql.toString(), Pessoa.class);
		List<Pessoa> lista = query.getResultList();
		if(lista.size() > 0)
			return lista.get(0);
		return null;
	}
	

}
