package com.pack.domain.service;

import com.pack.pojo.SpUsuarioPojo;
import com.pack.utils.exception.CustomValidationException;

public interface SpUsuarioService extends AbstractService<SpUsuarioPojo>{

	SpUsuarioPojo getUsuario(String usuario, String senha) throws CustomValidationException;

}
