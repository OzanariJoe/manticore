package com.manticore.servlet.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.util.JSON;

@WebServlet(urlPatterns = {})
public class JSONPServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6987824290551696489L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("application/json; charset=UTF-8");
		String output = getJSON(req);

		try (PrintWriter writer = resp.getWriter()) {
			writer.write(output);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	final String getJSON(HttpServletRequest req) {
		String callback = req.getParameter("callback");
		String className = req.getParameter("className");
		Object data = req.getParameterMap();
		try {
			Class<?> clazz = Class.forName(className);
			FormHandler handler = (FormHandler) clazz.newInstance();
			data = handler.handle(req);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			e.printStackTrace();
		}
		String output = JSON.serialize(data);
		return callback + "(" + output + ")";
	}

}
