package com.nonage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolver {
	
	public static void view(String view,HttpServletRequest request,
			HttpServletResponse response) throws IOException,ServletException{
		
		if(view.indexOf("redirect:")==0){
			view=view.substring("redirect:".length());
			response.sendRedirect(view);
		}else{
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		
	}  
}
