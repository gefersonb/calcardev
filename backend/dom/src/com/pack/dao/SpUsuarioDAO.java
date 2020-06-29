package com.pack.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pack.converter.SpUsuarioConverter;
import com.pack.entity.Usuario;
import com.pack.pojo.SpUsuarioPojo;
import com.pack.repository.SpUsuarioRepository;
import com.pack.utils.exception.CustomValidationException;

@Stateless
public class SpUsuarioDAO implements SpUsuarioRepository {

	@PersistenceContext(unitName = "desafio-pu")
	private EntityManager em;
	
	@Override
	public SpUsuarioPojo salvar(SpUsuarioPojo obj) throws CustomValidationException {
		Usuario usuario = this.getUsuario(obj.getUsuario());
		if(null == usuario) {
			usuario = new Usuario();
			usuario.setUsuario(obj.getUsuario());
			usuario.setSenha(obj.getSenha());
			em.persist(usuario);
			obj.setCodigo(usuario.getCodigo());
			return obj;
		} else {
			List<com.pack.pojo.Erro> erros = new ArrayList<>();
			com.pack.pojo.Erro erro = new com.pack.pojo.Erro();
			erro.setCoRetorno("9990");
			erro.setDsValor(obj.getUsuario());
			erro.setDsRetorno("Já cadastrado");
			erro.setDsField("usuario");
			erros.add(erro);
			throw new CustomValidationException(erros);
		}
	}

	@Override
	public void excluir(Long codigo) throws CustomValidationException {
	}

	@Override
	public List<SpUsuarioPojo> listAll() throws CustomValidationException {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT u FROM Usuario u " );
		TypedQuery<Usuario> query = em.createQuery(jpql.toString(), Usuario.class);
		List<Usuario> lista = query.getResultList();
		return SpUsuarioConverter.toPojoList(lista);
	}

	@Override
	public SpUsuarioPojo get(Long codigo) throws CustomValidationException {
		return null;
	}
	
	public Usuario getUsuario(String usuario) throws CustomValidationException {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT u FROM Usuario u WHERE u.usuario = '" + usuario + "'" );
		TypedQuery<Usuario> query = em.createQuery(jpql.toString(), Usuario.class);
		List<Usuario> lista = query.getResultList();
		if(lista.size() > 0)
			return lista.get(0);
		return null;
	}

	@Override
	public SpUsuarioPojo getUsuario(String usuario, String senha) throws CustomValidationException {
		Usuario u = this.getUsuario(usuario);
		if(null == u || !senha.equals(u.getSenha()))
			u = null;
		return SpUsuarioConverter.toPojo(u);
	}

}
