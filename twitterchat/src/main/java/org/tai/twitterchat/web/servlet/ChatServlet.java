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
import org.springframework.social.RateLimitExceededException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tai.twitterchat.domain.chat.ChatRoom;
import org.tai.twitterchat.domain.model.User;
import org.tai.twitterchat.domain.model.UserRole;

@Controller
@RequestMapping("/")
public class ChatServlet extends HttpServlet {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatServlet.class);
	private static ChatRoom chatRoom = null;
	private static final long serialVersionUID = 9049787261928772648L;
	private static final String FAKE_PASS = "pass";
	private User sender; 
	private User admin;
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = SecurityUtils.getSubject().getPrincipal().toString();
		String pass = FAKE_PASS;
		// Create room sender and room admin
		if (user != null && pass != null) {
			sender = new User(user, pass, UserRole.WRITER);   	
			admin = new User("admin", pass, UserRole.ADMIN);   	
		}
		
		// If chat is created
    	if (chatRoom != null) {
    		try {
    			chatRoom.synchronizeWithTwitter();    			
    		} catch (RateLimitExceededException e) {
				LOGGER.error("Twitter rate limit has exceeded!");
    			request.setAttribute("rateLimit", true);
    		}

	    	Subject currentUser = SecurityUtils.getSubject(); 
	    	// if user has role writer we show him send button, otherwise it is not visible
	    	if (currentUser.hasRole("writer")) {
	        	request.setAttribute("authorizedToSend", true);
	    	}
    		request.setAttribute("roomName", chatRoom.getName());
    		request.setAttribute("messages", chatRoom.getMessages());
    		request.setAttribute("user", sender);
    		request.setAttribute("userImageUrl", sender.getService().getSenderProfile().getProfileImageUrl());
	        request.getRequestDispatcher("/jsp/chat.jsp").forward(request, response);
    	} else {
    		request.getRequestDispatcher("/jsp/noChatRoom.jsp").forward(request, response);
    	}
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 	
    	// indicates whether or not this POST request is sent in order to create new chat room
    	String roomCreated = response.getHeader("roomCreated");
		String msg = request.getParameter("msg");
    	if (roomCreated != null) {
    		// Chat room creation 
    		String roomName = response.getHeader("roomName");  	
    		chatRoom = new ChatRoom(roomName, admin, sender);
    		response.sendRedirect(this.getServletConfig().getServletContext().getContextPath() + "/chat");     		
    	} else if (!msg.equals("")) { 
    		// Sending message - we need to set new sender in the room
    		String user = SecurityUtils.getSubject().getPrincipal().toString();
    		String pass = FAKE_PASS;
			sender = new User(user, pass, UserRole.WRITER);  
			try {
				chatRoom.addParticipant(sender.getLogin());
				chatRoom.sendMessage(sender, msg);
				chatRoom.synchronizeWithTwitter();				
			} catch (RateLimitExceededException e) {
				LOGGER.error("Twitter rate limit has exceeded!");
			}
    		response.sendRedirect(this.getServletConfig().getServletContext().getContextPath() + "/chat"); 
    	} else {//when roomCreated header is null so POST redirect is sent not in order to create room
    		request.getRequestDispatcher("/jsp/noChatRoom.jsp").forward(request, response);   		
    	}
    }
}
