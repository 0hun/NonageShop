package com.nonage.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.nonage.admin.controller.action.AdminIndexAction;
import com.nonage.admin.controller.action.AdminLoginAction;
import com.nonage.admin.controller.action.AdminLogoutAction;
import com.nonage.admin.controller.action.AdminMemberListAction;
import com.nonage.admin.controller.action.AdminOrderListAction;
import com.nonage.admin.controller.action.AdminOrderSaveAction;
import com.nonage.admin.controller.action.AdminOrderSearchListAction;
import com.nonage.admin.controller.action.AdminProductDetailAction;
import com.nonage.admin.controller.action.AdminProductListAction;
import com.nonage.admin.controller.action.AdminProductSearchAction;
import com.nonage.admin.controller.action.AdminProductUpdateAction;
import com.nonage.admin.controller.action.AdminProductUpdateFormAction;
import com.nonage.admin.controller.action.AdminProductWriteAction;
import com.nonage.admin.controller.action.AdminProductWriteFormAction;
import com.nonage.admin.controller.action.AdminQnaDetailAction;
import com.nonage.admin.controller.action.AdminQnaListAction;
import com.nonage.admin.controller.action.AdminQnaResaveAction;
import com.nonage.controller.action.Action;
import com.nonage.controller.action.CartDeleteAction;
import com.nonage.controller.action.CartInsertAction;
import com.nonage.controller.action.CartListAction;
import com.nonage.controller.action.ContractAction;
import com.nonage.controller.action.FindZipNumAction;
import com.nonage.controller.action.IdCheckFormAction;
import com.nonage.controller.action.IndexAction;
import com.nonage.controller.action.JoinAction;
import com.nonage.controller.action.JoinFormAction;
import com.nonage.controller.action.LoginAction;
import com.nonage.controller.action.LoginFormAction;
import com.nonage.controller.action.LogoutAction;
import com.nonage.controller.action.MyPageAction;
import com.nonage.controller.action.OrderAllAction;
import com.nonage.controller.action.OrderDetailAction;
import com.nonage.controller.action.OrderInsertAction;
import com.nonage.controller.action.OrderListAction;
import com.nonage.controller.action.ProductDetailAction;
import com.nonage.controller.action.ProductKindAction;
import com.nonage.controller.action.QnaListAction;
import com.nonage.controller.action.QnaViewAction;
import com.nonage.controller.action.QnaWriteAction;
import com.nonage.controller.action.QnaWriteFormAction;

public class ActionFactory {	
	private static ActionFactory instance = new ActionFactory();
	private ActionFactory() {		
		String path="com/nonage/properties/Command";
		this.loadProperties(path);
	}
	public static ActionFactory getInstance() {return instance;}

	// 명령어와 명령어 처리 클래스를 쌍으로 저장
	private Map<String,Action> commandMap = new HashMap<String,Action>(); 

	// properties 설정
	private void loadProperties(String path) {
		
		ResourceBundle rbHome = ResourceBundle.getBundle(path);// 누구를 실행할지를 rb에 저장.
		Enumeration<String> actionEnumHome = rbHome.getKeys();
		while (actionEnumHome.hasMoreElements())
		{
			String command = actionEnumHome.nextElement();
			String className = rbHome.getString(command);
			try {
				Class commandClass = Class.forName(className); // 해당 문자열을 클래스로 만든다
				Action commandInstance = (Action)commandClass.newInstance(); // 해당 클래스의 객체를 생성
				commandMap.put(command, commandInstance); // Map 객체인 commandMap에 객체 저장
			} catch (ClassNotFoundException e) {
				continue; // error
				// throw new ServletException(e);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}	
	public static Action getAction(String command){
		return instance.commandMap.get(command);
	}

}






