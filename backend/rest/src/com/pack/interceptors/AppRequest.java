package com.pack.interceptors;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;
import com.pack.pojo.SpUsuarioPojo;

@Model
public class AppRequest {

    private String ip;
    private SpUsuarioPojo usuario;
    private Long codigo;
    private HttpServletRequest request;
    private boolean error;
    private boolean authenticated;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public SpUsuarioPojo getUsuario() {
        return usuario;
    }

    public void setUsuario(SpUsuarioPojo usuario) {
        this.usuario = usuario;
    }

    public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public boolean hasError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AppRequest [ip=");
        builder.append(ip);
        builder.append(", usuario=");
        builder.append(usuario);
        builder.append(", request=");
        builder.append(request);
        builder.append(", error=");
        builder.append(error);
        builder.append(", authenticated=");
        builder.append(authenticated);
        builder.append("]");
        return builder.toString();
    }

}