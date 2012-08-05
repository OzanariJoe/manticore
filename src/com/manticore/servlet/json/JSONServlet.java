package com.manticore.servlet.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.util.JSON;

@WebServlet(urlPatterns = {})
public class JSONServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5038113101101238504L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("application/json; charset=UTF-8");
		String output = JSON.serialize(req.getParameterMap());

		try (PrintWriter writer = resp.getWriter()) {
			writer.write(output);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
