package com.nonage.admin.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.controller.action.Action;

public class AdminIndexAction implements Action {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String url = "main.jsp";  
     
    return url;
  }
}
