package com.pack.repository;

import com.pack.pojo.SpUsuarioPojo;
import com.pack.utils.exception.CustomValidationException;

public interface SpUsuarioRepository extends Repository<SpUsuarioPojo> {
	SpUsuarioPojo getUsuario(String usuario, String senha) throws CustomValidationException;
}
