package com.pack.domain.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.pack.domain.service.SpPessoaService;
import com.pack.pojo.SpPessoaPojo;
import com.pack.repository.SpPessoaRepository;
import com.pack.utils.exception.CustomValidationException;


@Stateless
public class SpPessoaServiceImpl implements SpPessoaService {
	
	@Inject
	private SpPessoaRepository repository;

	@Override
	public SpPessoaPojo salvar(SpPessoaPojo pessoa) throws CustomValidationException {
		return repository.salvar(pessoa);
	}

	@Override
	public List<SpPessoaPojo> listAll() throws CustomValidationException {
		return repository.listAll();
	}

	@Override
	public void excluir(Long codigo) throws CustomValidationException {
		repository.excluir(codigo);
	}

}
