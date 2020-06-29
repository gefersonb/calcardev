package com.pack.application;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Provider
public class FilterApplication implements ContainerResponseFilter {
	
	@Context javax.servlet.http.HttpServletRequest request;

	public String getIP() {
        
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
		}		
		
        String ipAddress  = request.getHeader("X-FORWARDED-FOR");  
        if(ipAddress == null)  
        {  
          ipAddress = request.getRemoteAddr();  
        }  
 
        return ipAddress;
        //return ((HttpServletRequest) facesContext.getExternalContext().getRequest()).getRemoteAddr();
 
    }
	
 
    @Override
    public void filter(ContainerRequestContext requestContext, 
      ContainerResponseContext responseContext) throws IOException {
    	
		//System.out.println(getIP());
    	
    	
          responseContext.getHeaders().add(
            "Access-Control-Allow-Origin", "*");
          responseContext.getHeaders().add(
            "Access-Control-Allow-Credentials", "true");
          responseContext.getHeaders().add(
           "Access-Control-Allow-Headers",
           "origin, content-type, accept, authorization");
          responseContext.getHeaders().add(
            "Access-Control-Allow-Methods", 
            "GET, POST, PUT, DELETE, OPTIONS, HEAD");
          responseContext.getHeaders().add(
        	"Access-Control-Expose-Headers", 
        	"Authorization");
    }
}