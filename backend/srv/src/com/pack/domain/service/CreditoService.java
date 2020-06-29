package com.pack.domain.service;

import java.util.List;

import com.pack.pojo.CreditoPojo;
import com.pack.pojo.SpPessoaPojo;
import com.pack.utils.exception.CustomValidationException;

public interface CreditoService extends AbstractService<CreditoPojo>{
	CreditoPojo analisarCredito(SpPessoaPojo object) throws CustomValidationException;
	List<CreditoPojo>  listByPessoa(SpPessoaPojo object) throws CustomValidationException;
	

}
