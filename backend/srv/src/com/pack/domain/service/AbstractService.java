package com.pack.domain.service;

import java.util.List;

import com.pack.utils.exception.CustomValidationException;

public abstract interface AbstractService<T> {
	T salvar(T object) throws CustomValidationException;
	void excluir(Long codigo) throws CustomValidationException;
	List<T> listAll() throws CustomValidationException;
}
