package com.pack.security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;


@Provider
@PreMatching
@Priority(Priorities.AUTHORIZATION)
public class CorsResourceFilter implements Feature, ContainerRequestFilter, ContainerResponseFilter {

	private static final Logger LOG = Logger.getLogger(CorsResourceFilter.class);

	@Override
	public boolean configure(FeatureContext context) {
		/*
		if (!Application.CORS_ALLOWED_ORIGINS.isEmpty()) {
			String[] origins = Application.CORS_ALLOWED_ORIGINS.split(",");
			CorsFilter corsFilter = new CorsFilter();
			for (String origin : origins) {
				LOG.debug("CORS Allowed Origin: " + origin);
				corsFilter.getAllowedOrigins().add(origin.trim());
			}
			context.register(corsFilter);
		}
		*/
		return true;
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		LOG.debug("CorsResourceFilter: Validando cabeçalho da requisição");
		LOG.debug(requestContext.getHeaderString("User-Agent"));
		LOG.debug(requestContext.getHeaderString("Accept-Language"));
		LOG.debug(requestContext.getHeaderString("Accept"));
		LOG.debug(requestContext.getHeaderString("Accept-Encoding"));
		LOG.debug(requestContext.getHeaderString("Connection"));
		LOG.debug(requestContext.getHeaderString("Host"));
		LOG.debug(requestContext.getHeaderString("Authorization"));
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
	}

}