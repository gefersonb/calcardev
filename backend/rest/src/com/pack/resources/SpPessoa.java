package com.pack.resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
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

import com.pack.domain.service.SpPessoaService;
import com.pack.pojo.SpPessoaPojo;
import com.pack.utils.exception.CustomValidationException;
import com.pack.validation.SpPessoaValidator;

@Path("/integracao")
@Produces("application/json; charset=UTF-8")
@Consumes("application/json; charset=UTF-8")
public class SpPessoa {

	private static final Logger LOGGER = Logger.getLogger(SpPessoa.class);

	@Inject
	private SpPessoaValidator validator;
	@Inject
	private SpPessoaService service;
	@Context
	private javax.servlet.http.HttpServletRequest request;

	@POST
	@Path("/pessoa")
	public Response cadastrarPessoa(SpPessoaPojo pessoa) throws Exception {
		try {
			// curl --header "Content-Type: application/json" --request POST --data
			// {\"nome\":\"Geferson\"} http://localhost:8080/rest/rest/integracao/pessoa
			validator.validate(pessoa);
			
			if(!pessoa.isCpfValido()) {
				List<com.pack.pojo.Erro> erros = new ArrayList<>();
				com.pack.pojo.Erro erro = new com.pack.pojo.Erro();
				erro.setCoRetorno("10011");
				erro.setDsValor(pessoa.getCpf());
				erro.setDsRetorno("Inválido");
				erro.setDsField("cpf");
				erros.add(erro);
				throw new CustomValidationException(erros);
			}

			if(pessoa.getRenda() == null) {
				List<com.pack.pojo.Erro> erros = new ArrayList<>();
				com.pack.pojo.Erro erro = new com.pack.pojo.Erro();
				erro.setCoRetorno("10012");
				erro.setDsValor(null);
				erro.setDsRetorno("Inválido");
				erro.setDsField("renda");
				erros.add(erro);
				throw new CustomValidationException(erros);
			} else if(pessoa.getRenda() == 0) {
				List<com.pack.pojo.Erro> erros = new ArrayList<>();
				com.pack.pojo.Erro erro = new com.pack.pojo.Erro();
				erro.setCoRetorno("10012");
				erro.setDsValor(pessoa.getRenda().toString());
				erro.setDsRetorno("Inválido");
				erro.setDsField("renda");
				erros.add(erro);
				throw new CustomValidationException(erros);
			}
			
			service.salvar(pessoa);

			/*
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = (Date)formatter.parse(pessoa.getNasc());
			Integer age = getAge(date);
			*/
			
			List<SpPessoaPojo> retorno = new ArrayList<SpPessoaPojo>();
			
			return Response.ok().entity(retorno).build();

		} catch (CustomValidationException e) {
			return Response.status(403).entity(e.getMessages()).build();
			
		}
	}
	/*
	private Integer getAge(Date d) {
		Date h = new Date();
		LocalDate birthDate = LocalDate.of(d.getYear(), d.getMonth(), d.getDay());
		LocalDate currentDate = LocalDate.of(h.getYear(), h.getMonth(), h.getDay());
		return Period.between(birthDate, currentDate).getYears();
	}
*/
	@PUT
	@Path("/pessoa")
	public Response atualizarPessoa(SpPessoaPojo pessoa) throws Exception {
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
			
			service.salvar(pessoa);

			List<SpPessoaPojo> retorno = service.listAll();
			return Response.ok().entity(retorno).build();

		} catch (CustomValidationException e) {
			return Response.status(403).entity(e.getMessages()).build();
		}
	}

	@DELETE
	@Path("/pessoa/{codigo}")
	public Response excluirPessoa(@PathParam("codigo") Long codigo) throws Exception {

		try {
			service.excluir(codigo);

			List<SpPessoaPojo> retorno = service.listAll();
			return Response.ok().entity(retorno).build();

		} catch (CustomValidationException e) {
			return Response.ok().entity(e.getMessages()).build();
		} catch (Exception e) {
			List<com.pack.pojo.Erro> erros = new ArrayList<>();
			com.pack.pojo.Erro erro = new com.pack.pojo.Erro();
			erro.setCoRetorno("2000");
			erro.setDsValor("");
			erro.setDsRetorno("Ja existem analises para este cliente");
			erro.setDsField("");
			erros.add(erro);
			//throw new CustomValidationException(erros);
			
			//return Response.ok().entity(erros).build();
			return Response.status(403).entity(erros).build();
			//throw new CustomValidationException(erros);
		}

	}

	@GET
	@Path("/pessoa")
	public Response listar() throws Exception {
		try {
			List<SpPessoaPojo> retorno = service.listAll();
			return Response.ok().entity(retorno).build();

		} catch (CustomValidationException e) {
			return Response.ok().entity(e.getMessages()).build();
		}

	}

	@GET
	@Path("/teste")
	public Response teste() throws Exception {
		return Response.ok().entity("BACKEND 0.00.01").build();
	}
	
}
