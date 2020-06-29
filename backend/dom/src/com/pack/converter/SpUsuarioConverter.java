package com.pack.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.pack.entity.Usuario;
import com.pack.pojo.SpUsuarioPojo;



public class SpUsuarioConverter {

	public static SpUsuarioPojo toPojo(Usuario u){
		SpUsuarioPojo novo = new SpUsuarioPojo();
		if(u != null) {
			novo.setCodigo(u.getCodigo());
			novo.setUsuario(u.getUsuario());
		}
		return novo;
	}
	
	public static List<SpUsuarioPojo> toPojoList(List<Usuario> users){
		List<SpUsuarioPojo> retorno = new ArrayList<SpUsuarioPojo>();
		for(Usuario u:users) {
			SpUsuarioPojo novo = new SpUsuarioPojo();
			if(u != null) {
				novo.setCodigo(u.getCodigo());
				novo.setUsuario(u.getUsuario());
				retorno.add(novo);
			}
		}
		return retorno;
	}
	
}
