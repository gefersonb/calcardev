package com.pack.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.pack.entity.Pessoa;
import com.pack.pojo.SpPessoaPojo;


public class SpPessoaConverter {

	public static List<SpPessoaPojo> toPojo(List<Pessoa> pessoas){
		List<SpPessoaPojo> retorno = new ArrayList<SpPessoaPojo>();
		for(Pessoa p : pessoas) {
			SpPessoaPojo novo = new SpPessoaPojo();
			novo.setCodigo(p.getCodigo());
			novo.setNome(p.getNome());
			novo.setEmail(p.getEmail());
			novo.setNacionalidade(p.getNacionalidade());
			novo.setNaturalidade(p.getNaturalidade());
			novo.setSexo(p.getSexo());
			novo.setCpf(p.getCpf());
			novo.setNasc(new SimpleDateFormat("dd/MM/yyyy").format(p.getNasc()));
			novo.setEcivil(p.getEcivil());
			novo.setUf(p.getUf());
			novo.setDependentes(p.getDependentes());
			novo.setRenda(p.getRenda());
			retorno.add(novo);
		}
		return retorno;
	}
}
