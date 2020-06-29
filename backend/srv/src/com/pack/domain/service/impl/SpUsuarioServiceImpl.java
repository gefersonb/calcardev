package com.pack.domain.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.pack.domain.service.SpUsuarioService;
import com.pack.pojo.SpUsuarioPojo;
import com.pack.repository.SpUsuarioRepository;
import com.pack.utils.exception.CustomValidationException;


@Stateless
public class SpUsuarioServiceImpl implements SpUsuarioService {
	
	@Inject
	private SpUsuarioRepository repository;

	@Override
	public SpUsuarioPojo salvar(SpUsuarioPojo usuario) throws CustomValidationException {
		return repository.salvar(usuario);
	}


	@Override
	public void excluir(Long codigo) throws CustomValidationException {
		
	}

	@Override
	public List<SpUsuarioPojo> listAll() throws CustomValidationException {
		return repository.listAll();
	}


	@Override
	public SpUsuarioPojo getUsuario(String usuario, String senha) throws CustomValidationException {
		return repository.getUsuario(usuario, senha);
	}


}
