package com.pack.repository;

import java.util.List;

import com.pack.pojo.CreditoPojo;
import com.pack.pojo.SpPessoaPojo;
import com.pack.utils.exception.CustomValidationException;

public interface CreditoRepository extends Repository<CreditoPojo> {
	CreditoPojo analisarCredito(SpPessoaPojo object) throws CustomValidationException;
	List<CreditoPojo>  listByPessoa(SpPessoaPojo object) throws CustomValidationException;
}
