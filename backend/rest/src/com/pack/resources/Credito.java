package com.pack.resources;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import com.pack.domain.service.CreditoService;
import com.pack.pojo.CreditoPojo;
import com.pack.pojo.SpPessoaPojo;
import com.pack.utils.exception.CustomValidationException;
import com.pack.validation.SpPessoaValidator;

@Path("/credito")
@Produces("application/json; charset=UTF-8")
@Consumes("application/json; charset=UTF-8")
public class Credito {

	private static final Logger LOGGER = Logger.getLogger(SpPessoa.class);

	@Inject
	private SpPessoaValidator validator;
	@Inject
	private CreditoService service;
	@Context
	private javax.servlet.http.HttpServletRequest request;

	@POST
	@Path("/solicitar_avaliacao")
	public Response solicitarAvaliacao(SpPessoaPojo pessoa) throws Exception {
		
		try {
			validator.validate(pessoa);
			
			if(!pessoa.isCpfValido()) {
				List<com.pack.pojo.Erro> erros = new ArrayList<>();
				com.pack.pojo.Erro erro = new com.pack.pojo.Erro();
				erro.setCoRetorno("9998");
				erro.setDsValor(pessoa.getCpf());
				erro.setDsRetorno("Inválido");
				erro.setDsField("cpf");
				erros.add(erro);
				throw new CustomValidationException(erros);
			}

			service.analisarCredito(pessoa);

			List<SpPessoaPojo> retorno = new ArrayList<SpPessoaPojo>();
			List<com.pack.pojo.Erro> erros = new ArrayList<>();			
			com.pack.pojo.Erro erro = new com.pack.pojo.Erro();
			erro.setCoRetorno("0000");
			erro.setDsValor(pessoa.getNome());
			erro.setDsRetorno(" Solicitou avaliacao de crédito");
			erro.setDsField("nome");
			erros.add(erro);
			
			return Response.ok().entity(erros).build();

		} catch (CustomValidationException e) {
			return Response.status(403).entity(e.getMessages()).build();
			
		}
		
		
//		return Response.ok().entity(pessoa.getNome() + " solicitou avaliacao").build();
	}

	@GET
	@Path("/listar_todos")
	public Response listar() throws Exception {
		try {
			List<CreditoPojo> retorno = service.listAll();
			return Response.ok().entity(retorno).build();

		} catch (CustomValidationException e) {
			return Response.ok().entity(e.getMessages()).build();
		}

	}
	
	@POST
	@Path("/listar")
	public Response listar(SpPessoaPojo pessoa) throws Exception {
		
		try {
			List<CreditoPojo> retorno = service.listByPessoa(pessoa);
			return Response.ok().entity(retorno).build();

		} catch (CustomValidationException e) {
			return Response.ok().entity(e.getMessages()).build();
		}

	}
}
