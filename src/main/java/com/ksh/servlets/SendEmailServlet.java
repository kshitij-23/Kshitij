package com.ksh.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ksh.util.EmailSender;

/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet 
{ 	
	private static final long serialVersionUID = 1L;
	public static String emailBody = "<html><body><table><tr><td><b>Name</b></td><td> : </td><td>${name}</td></tr><tr><td><b>Email</b></td><td> : </td><td>${email}</td></tr><tr><td><b>Message</b></td><td> : </td><td>${message}</td></tr></table></body></html>";
    public SendEmailServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			System.out.println(request.getParameter("demo-name"));
			System.out.println(request.getParameter("demo-email"));
			System.out.println(request.getParameter("demo-message"));
			emailBody = emailBody.replace("${name}", request.getParameter("demo-name"));
			emailBody = emailBody.replace("${email}", request.getParameter("demo-email"));
			emailBody = emailBody.replace("${message}", request.getParameter("demo-message"));
			EmailSender.sendFromGMail(emailBody);
			response.sendRedirect("index.html#three");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
