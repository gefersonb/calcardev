package com.pack.validation;

import java.util.List;

import javax.enterprise.inject.Model;

import com.pack.pojo.SpPessoaPojo;
import com.pack.utils.exception.CustomValidationException;
import com.pack.utils.exception.CustomValidator;

@Model
public class SpPessoaValidator extends CustomValidator<SpPessoaPojo> {

	@Override
	public void validate(SpPessoaPojo pessoa) throws CustomValidationException {

		List<com.pack.pojo.Erro> erros = super.beanValidate(pessoa);
		/*
		if (!validaEcivil(pessoa)) {
			com.pack.pojo.Erro erro = new com.pack.pojo.Erro();
			erro.setCoRetorno("9998");
			erro.setDsValor(pessoa.getCpf());
			erro.setDsRetorno("Inválido");
			erro.setDsField("cpf");
			erros.add(erro);
			
		}
*/
		if (erros.size() > 0) {
			throw new CustomValidationException(erros);
		}
		
	}

	private boolean validaEcivil(SpPessoaPojo p) {
		return ("S".equals(p.getEcivil()) ||
				"C".equals(p.getEcivil()) ||
				"D".equals(p.getEcivil()) ||
				"V".equals(p.getEcivil()));
	}

}
