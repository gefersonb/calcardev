package com.pack.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import com.pack.interceptors.AppRequest;

@Provider
public class TokenResponseFilter implements ContainerResponseFilter {

    private static final Logger LOG = Logger.getLogger(TokenResponseFilter.class);

    @Inject
    private AppRequest appRequest;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        LOG.debug("RefreshTokenResponseFilter: filtro da resposta invocado...");
        
        if (appRequest.hasError() || !appRequest.isAuthenticated()) {
            LOG.debug("TokenResponseFilter: autenticação JWT falhou ou não era esperada. Não será retornado um token");
            return;
        }
        List<Object> authHeader = new ArrayList<>();
        
        authHeader.add(SecurityToken.createToken(this.appRequest));
        
        responseContext.getHeaders().put(SecurityToken.AUTHORIZATION_PROPERTY, authHeader);
    }

}