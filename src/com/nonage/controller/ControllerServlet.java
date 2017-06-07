package com.nonage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.controller.action.Action;

public class ControllerServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response); // get방식과 post방식을 모두 requestPro로 처리
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, 
			IOException {
		requestPro(request, response);
	}

	// 사용자의 요청을 분석해서 해당 작업을 처리
	private void requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, 
			IOException {

		request.setCharacterEncoding("utf-8");		
		response.setContentType("text/html;charset=utf-8");
		
		String view = null;
		Action action = null;
		try {
			String command = request.getRequestURI();			
			if (command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			action =  ActionFactory.getAction(command);
			if (action == null) {
				System.out.println("not found : " + command);
				return;
			}

			view = action.execute(request, response);
			if (view == null) {
				return;
			}

		} catch (Throwable e) {
			throw new ServletException(e);

		}

		if (view == null)
			return;
		
		ViewResolver.view(view, request, response);
		
	}

}
