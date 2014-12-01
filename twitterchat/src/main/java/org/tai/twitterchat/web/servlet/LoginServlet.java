package org.tai.twitterchat.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 6125902842922207880L;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProfilingFilter.class);

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Subject currentUser = SecurityUtils.getSubject();
    	if(currentUser.isAuthenticated()) {
    		request.getRequestDispatcher("/chat").forward(request, response);
    	} else { 	
    		request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    	}
    }

	/**
	 * try to authenticate user with posted credentials
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
    		request.getRequestDispatcher("/chat").forward(request, response);
    		return;
    	}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		LOGGER.info("username " + username + " password " + password);

		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		token.setRememberMe(true);

		try {
			currentUser.login(token);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Incorrect credentials");
			request.getRequestDispatcher("/jsp/login.jsp").forward(request,
					response);
		}
	}

}
