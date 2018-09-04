package com.ksh.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = "/songsServlet")
public class SongsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		try
		{
			String folderName = request.getParameter("folderName").contains("7") ? request.getParameter("folderName").replaceAll("7", "/") : request.getParameter("folderName");
			String name = request.getServletContext().getRealPath("//"+folderName);
//			System.out.println("Name : "+name);
			File songsFolder = new File(name);
			File[] files = songsFolder.listFiles();
			System.out.println("File Size : "+files.length);
			Stream<File> stream = Arrays.stream(files);
			List<String> strings = new ArrayList<>();
			stream.forEach
			(
				e -> 
				{
					if(e.isDirectory())
						strings.add(folderName+"7"+e.getName());
					else
						strings.add(folderName+"/"+e.getName());
				}
			);
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(strings);
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(jsonString);
			out.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
