package org.tai.twitterchat.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CreateRoomServlet extends HttpServlet {
	private static final long serialVersionUID = -2457531721361959946L;
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        request.getRequestDispatcher("/jsp/createRoom.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String roomName = request.getParameter("roomName");  	
    	if (!roomName.equals("")) {
       		response.addHeader("roomName", roomName);
       		response.addHeader("roomCreated", "true");
       		request.getRequestDispatcher("/chat").forward(request, response);
   		} else {
   			// cannot create room with empty name
  			request.setAttribute("roomCreated", !roomName.equals(""));
   			request.getRequestDispatcher("/jsp/createRoom.jsp").forward(request, response);    		
   		}
    }
    
}

