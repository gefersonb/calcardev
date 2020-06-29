package com.pack.domain.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.pack.domain.service.CreditoService;
import com.pack.pojo.CreditoPojo;
import com.pack.pojo.SpPessoaPojo;
import com.pack.repository.CreditoRepository;
import com.pack.repository.SpPessoaRepository;
import com.pack.utils.exception.CustomValidationException;


@Stateless
public class CreditoServiceImpl implements CreditoService {
	
	@Inject
	private CreditoRepository repository;


	@Override
	public List<CreditoPojo> listAll() throws CustomValidationException {
		return repository.listAll();
	}

	@Override
	public CreditoPojo analisarCredito(SpPessoaPojo object) throws CustomValidationException {
		// TODO Auto-generated method stub
		return repository.analisarCredito(object);
	}

	@Override
	public CreditoPojo salvar(CreditoPojo object) throws CustomValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Long codigo) throws CustomValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CreditoPojo> listByPessoa(SpPessoaPojo pessoa) throws CustomValidationException {
		return repository.listByPessoa(pessoa);
	}

}
