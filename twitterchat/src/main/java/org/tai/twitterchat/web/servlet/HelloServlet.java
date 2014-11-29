package org.tai.twitterchat.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tai.twitterchat.service.TwitterConnectionService;

@Controller
@RequestMapping("/")
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 9049787261928772648L;
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
    	
    	TwitterConnectionService service = new TwitterConnectionService();
        request.setAttribute("twitterProfile", service.getProfile());
        CursoredList<TwitterProfile> friends = service.getFriends();
        request.setAttribute("friends", friends);
        request.setAttribute("message", "ehlo!");
        request.getRequestDispatcher("/jsp/hello.jsp").forward(request, response);
    }


}
