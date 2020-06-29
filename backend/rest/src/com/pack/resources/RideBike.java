package com.pack.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import com.pack.pojo.RideBikePojo;

@Path("/integracao")
@Produces("application/json; charset=UTF-8")
@Consumes("application/json; charset=UTF-8")
public class RideBike {

	private static final Logger LOGGER = Logger.getLogger(RideBike.class);


	@Context
	private javax.servlet.http.HttpServletRequest request;

	@POST
	@Path("/ride_bike")
	public Response RideBikePost(RideBikePojo[] ride) throws Exception {
		//String texto = "";
		for (int i = 0; i < ride.length; i++) {
			String texto = "{hora: " + ride[i].getHora() 
					+ ", latitude: " + ride[i].getLatitude() 
					+ ", longitude: " + ride[i].getLongitude() 
					+ ", speed: " + ride[i].getSpeed()
					+ ", tempo_leitura: " + ride[i].getTempo_leitura()
					+ ", velocidade: " + ride[i].getVelocidade() + "}, ";
			
			System.out.println(texto);
			
			/*
			System.out.println(
					ride[i].getHora() + " : " + 
					ride[i].getLatitude() + " ; " +
					ride[i].getLongitude() + " = " +
					ride[i].getSpeed() + " km/h "
					);
			*/
		}

		
		
		List<RideBikePojo> retorno = new ArrayList<RideBikePojo>();
		return Response.ok().entity(retorno).build();

	}
	
}
