package com.pack.resources;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import com.pack.domain.service.SpUsuarioService;
import com.pack.interceptors.AppRequest;
import com.pack.pojo.SpPessoaPojo;
import com.pack.pojo.SpUsuarioPojo;
import com.pack.utils.exception.CustomValidationException;
import com.pack.validation.SpUsuarioValidator;

@Path("/integracao")
@Produces("application/json; charset=UTF-8")
@Consumes("application/json; charset=UTF-8")
public class SpUsuario {

	private static final Logger LOGGER = Logger.getLogger(SpUsuario.class);

	@Inject
	private AppRequest appRequest;

	@Inject
	private SpUsuarioValidator validator;
	@Inject
	private SpUsuarioService service;
	@Context
	private javax.servlet.http.HttpServletRequest request;

	@POST
	@Path("/usuario")
	public Response cadastrarUsuario(SpUsuarioPojo usuario) throws Exception {
		try {
			validator.validate(usuario);
			
			service.salvar(usuario);

			List<SpUsuarioPojo> retorno = new ArrayList<SpUsuarioPojo>();
			return Response.ok().entity(retorno).build();

		} catch (CustomValidationException e) {
			return Response.status(403).entity(e.getMessages()).build();
			
		}
	}

	@POST
	@Path("/post_user_bike")
	public Response postUserBike(SpUsuarioPojo usuario) throws Exception {
		try {
			validator.validate(usuario);
			
			service.salvar(usuario);

			List<SpUsuarioPojo> retorno = new ArrayList<SpUsuarioPojo>();
			return Response.ok().entity(retorno).build();

		} catch (CustomValidationException e) {
			return Response.status(403).entity(e.getMessages()).build();
			
		}
	}
	
	@POST
	@Path("/usuario_teste")
	public Response usuarioTestePost(SpUsuarioPojo usuario) throws Exception {
		List<SpUsuarioPojo> retorno = service.listAll();
		return Response.ok().entity(retorno).build();
	}
	
	@GET
	@Path("/usuario_teste")
	public Response usuarioTesteGet() throws Exception {
		List<SpUsuarioPojo> retorno = service.listAll();
		return Response.ok().entity(retorno).build();
	}
	
	@GET
	@Path("/usuario/{usuario}/{senha}")
	public Response getUsuario(@PathParam("usuario") String usuario, @PathParam("senha") String senha) throws Exception {
		try {
			SpUsuarioPojo retorno = service.getUsuario(usuario, senha);
			return Response.ok().entity(retorno).build();

		} catch (CustomValidationException e) {
			return Response.status(403).entity(e.getMessages()).build();
		}

	}

	@POST
	@Path("/getUsuario")
	public Response getUsuario(SpUsuarioPojo user) throws Exception {
		String usuario = user.getUsuario();
		String senha = user.getSenha();
		
		try {
			SpUsuarioPojo retorno = service.getUsuario(usuario, senha);
			if(retorno != null) {
				this.appRequest.setAuthenticated(true);
				this.appRequest.setUsuario(retorno);
			}
			return Response.ok().entity(retorno).build();

		} catch (CustomValidationException e) {
			return Response.status(403).entity(e.getMessages()).build();
		}
	}
	
}
