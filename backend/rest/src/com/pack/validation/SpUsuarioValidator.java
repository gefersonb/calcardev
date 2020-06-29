package com.pack.validation;

import java.util.List;

import javax.enterprise.inject.Model;

import com.pack.pojo.SpUsuarioPojo;
import com.pack.utils.exception.CustomValidationException;
import com.pack.utils.exception.CustomValidator;

@Model
public class SpUsuarioValidator extends CustomValidator<SpUsuarioPojo> {

	@Override
	public void validate(SpUsuarioPojo usuario) throws CustomValidationException {

		List<com.pack.pojo.Erro> erros = super.beanValidate(usuario);

		if (erros.size() > 0) {
			throw new CustomValidationException(erros);
		}
		
	}

}
