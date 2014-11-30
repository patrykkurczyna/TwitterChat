package org.tai.twitterchat.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		if (response.getStatus() == HttpStatus.UNAUTHORIZED.value()) {
//			request.getRequestDispatcher("/jsp/login.jsp").forward(request,
//					response);
//		} else {
			request.getRequestDispatcher("/jsp/home.jsp").forward(request,
					response);
//		}
	}

}
