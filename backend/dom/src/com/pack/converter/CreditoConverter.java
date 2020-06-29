package com.pack.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.pack.entity.Credito;
import com.pack.pojo.CreditoPojo;



public class CreditoConverter {

	public static List<CreditoPojo> toPojo(List<Credito> analise){
		List<CreditoPojo> retorno = new ArrayList<CreditoPojo>();
		for(Credito a : analise) {
			CreditoPojo novo = new CreditoPojo();
			novo.setCodigo(a.getCodigo());
			novo.setDependentes(a.getDependentes());
			novo.setDtInc(new SimpleDateFormat("dd/MM/yyyy").format(a.getDtInc()));
			
			novo.setEcivil(getEstadoCivil(a.getEcivil()));
			novo.setIdade(a.getIdade());
			novo.setIdPessoa(a.getPessoa().getCodigo());
			novo.setRenda(a.getRenda());
			novo.setResultado(getResultado(a.getResultado()));
			novo.setMotivo(a.getMotivo());
			novo.setUf(a.getUf());
			novo.setNome(a.getPessoa().getNome());
			novo.setSexo(a.getPessoa().getSexo());
			retorno.add(novo);
		}
		return retorno;
	}

	private static String getResultado(String resultado) {
		if("A".equals(resultado))
			return "Aprovado";
		return "Negado";
	}

	private static String getEstadoCivil(String ecivil) {
		if("C".equals(ecivil))
			return "Casado";
		if("D".equals(ecivil))
			return "Divorciado";
		if("V".equals(ecivil))
			return "Viúvo";
		return "Solteiro";
	}
}
