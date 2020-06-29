package com.pack.repository;

import java.util.List;

import com.pack.utils.exception.CustomValidationException;;

public interface Repository<T> {

	T salvar(T obj) throws CustomValidationException;
	void excluir(Long codigo) throws CustomValidationException;
	List<T> listAll() throws CustomValidationException;
	T get(Long codigo) throws CustomValidationException;
}
