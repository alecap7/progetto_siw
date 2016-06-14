package it.uniroma3.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.uniroma3.controller.UtenteController;

public class AutenticazioneAmministrazioneFilter implements Filter {
	@SuppressWarnings("unused")
	private ServletContext application;

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		UtenteController bean = (UtenteController) ((HttpServletRequest)req).getSession().getAttribute("utenteController");
		
		if (bean == null || bean.getUtenteLoggato() == null || !bean.getUtenteLoggato().getRuolo().equals("amministratore")) {
			((HttpServletResponse) resp).sendRedirect("/progetto-siw-2016/login.xhtml");
		} else {
			chain.doFilter(req, resp);
		}
	}

	public void init(FilterConfig config) throws ServletException {
		this.application = config.getServletContext();
	}

	public void destroy() {
		application = null;
	}
}
