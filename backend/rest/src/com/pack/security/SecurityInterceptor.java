package com.pack.security;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;
import org.jboss.resteasy.core.ResourceMethodInvoker;
/*
import org.json.JSONObject;

import br.gov.sc.epagri.core.interceptors.AppRequest;
import br.gov.sc.epagri.core.utils.JsonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
*/
import org.json.JSONObject;

import com.pack.interceptors.AppRequest;
import com.pack.pojo.SpUsuarioPojo;
import com.pack.utils.JsonUtils;

import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.ExpiredJwtException;

@Provider
public class SecurityInterceptor implements ContainerRequestFilter {

	private static final Logger LOG = Logger.getLogger(SecurityInterceptor.class);

	private static final String RESOURCE_INVOKER = "org.jboss.resteasy.core.ResourceMethodInvoker";

	@Context
	private HttpServletRequest httpRequest;

	@Inject
	private AppRequest appRequest;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		/*
		final String authHeaderLog = requestContext.getHeaderString(SecurityToken.AUTHORIZATION_PROPERTY);
		System.out.println(authHeaderLog);
		*/
		
		this.appRequest.setIp(httpRequest.getRemoteHost());
		this.appRequest.setRequest(httpRequest);
		
		//System.out.println(requestContext.getUriInfo().getPath());
		
		if (requestContext.getUriInfo().getPath().endsWith("/getUsuario/") ||
				requestContext.getUriInfo().getPath().endsWith("/usuario/") ||
				requestContext.getUriInfo().getPath().endsWith("/usuario_teste/") ||
				requestContext.getUriInfo().getPath().endsWith("/teste") ||
				requestContext.getUriInfo().getPath().endsWith("/post_user_bike/")) {
			return;
		}

		LOG.debug("SecurityInterceptor: Iniciando o filtro de autenticação/autorização baseada em token");
		final String authHeader = requestContext.getHeaderString(SecurityToken.AUTHORIZATION_PROPERTY);
		if (authHeader == null || !authHeader.startsWith(SecurityToken.AUTHENTICATION_SCHEME)) {
			LOG.error("SecurityInterceptor: Requisição abortada.\n >>>>>MOTIVO: Authorization = null<<<<<<");
			requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
			return;
		}

		
		Claims claims = null;
		try {
			final String token = authHeader.replace(SecurityToken.AUTHENTICATION_SCHEME, "");
			LOG.debug("SecurityInterceptor: Realizando parse do token " + token);
			claims = SecurityToken.verifyToken(token);
		} catch (ExpiredJwtException e) {
			appRequest.setError(true);
			LOG.error("SecurityInterceptor: Requisição abortada.\n >>>>>MOTIVO: Token inválido!<<<<<<", e);
			requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
			return;
		}

		// Recupera o usuário de dentro do corpo do token
		SpUsuarioPojo usuario = null;
		try {
			usuario = this.getUserFromToken(claims);
			/*
			LOG.debug("ID: " + claims.getId());
			LOG.debug("Subject: " + claims.getSubject());
			LOG.debug("Issuer: " + claims.getIssuer());
			LOG.debug("IssuedAt: " + claims.getIssuedAt());
			LOG.debug("Expiration: " + claims.getExpiration());
			System.out.println("Expiration: " + claims.getExpiration());
			*/
			
		} catch (IOException e) {
			appRequest.setError(true);
			LOG.error("SecurityInterceptor: Requisição abortada.\n >>>>>MOTIVO: Erro ao fazer parse do corpo do token!<<<<<<", e);
			requestContext.abortWith(Response.status(Status.INTERNAL_SERVER_ERROR).build());
			return;
		}

		// Define o objeto requisição utilizado em interceptadores
		appRequest.setIp(claims.getIssuer());
		appRequest.setUsuario(usuario);
		appRequest.setAuthenticated(true);

		// Valida autorização ao serviço invoca
		ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty(RESOURCE_INVOKER);
		Method method = methodInvoker.getMethod();
	}

	
	@SuppressWarnings("unchecked")
	private SpUsuarioPojo getUserFromToken(Claims claims) throws IOException {
		// Recupera o usuário de dentro do corpo do token
		Map<String, String> mapa = (LinkedHashMap<String, String>) claims.get(SecurityToken.USUARIO);
		JSONObject json = new JSONObject(mapa);
		return JsonUtils.parseToObject(json.toString(), SpUsuarioPojo.class);
	}

	/*
	private boolean isDenied(Method method) {
		return method.getDeclaringClass().isAnnotationPresent(DenyAll.class)
				|| method.isAnnotationPresent(DenyAll.class);
	}
	*/
	private boolean isValidAccess(Method method, List<String> userRoles) {
		boolean retorno = false;
		if (method.isAnnotationPresent(PermitAll.class)) {
			retorno = true;
		} else if (method.isAnnotationPresent(RolesAllowed.class)
				&& hasRole(method.getAnnotation(RolesAllowed.class).value(), userRoles)) {
			retorno = true;
		} else if (method.getDeclaringClass().isAnnotationPresent(PermitAll.class)) {
			retorno = true;
		} else if (method.getDeclaringClass().isAnnotationPresent(RolesAllowed.class)
				&& hasRole(method.getDeclaringClass().getAnnotation(RolesAllowed.class).value(), userRoles)) {
			retorno = true;
		}
		return retorno;
	}

	private boolean hasRole(String[] autorizedRoles, List<String> userRoles) {
		boolean hasProfile = false;
		for (String role : autorizedRoles) {
			if (userRoles.contains(role)) {
				hasProfile = true;
				break;
			}
		}
		return hasProfile;
	}
	

}