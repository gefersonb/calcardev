package com.pack.servlets;

import java.io.Serializable;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class AppServlet implements ServletContextListener, Serializable {


	private static final long serialVersionUID = -5846884741266806995L;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("finalizou");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("iniciou......");
	}

}
