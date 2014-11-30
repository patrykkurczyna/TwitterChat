package org.tai.twitterchat.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tai.twitterchat.domain.chat.ChatRoom;
import org.tai.twitterchat.domain.model.User;
import org.tai.twitterchat.domain.model.UserRole;

@Controller
@RequestMapping("/")
public class CreateRoomServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfilingFilter.class);
	private static final String FAKE_USER = "admin";
	private static final String FAKE_PASS = "fake pass";
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        request.getRequestDispatcher("/jsp/createRoom.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String roomName = request.getParameter("roomName");
    	    	
    	User roomAdmin = new User(FAKE_USER, FAKE_PASS, UserRole.ADMIN);
    	
    	ChatRoom chatRoom = new ChatRoom(roomName, roomAdmin);
    	
    	LOGGER.info("room created ! " + chatRoom.getName());
    	
    	request.setAttribute("username", roomAdmin.getLogin());
        request.getRequestDispatcher("/jsp/createRoom.jsp").forward(request, response);
    }
    
}

