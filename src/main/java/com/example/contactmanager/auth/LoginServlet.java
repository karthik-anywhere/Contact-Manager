package com.example.contactmanager.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

@WebServlet({ "/LoginServlet", "/login" })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		Login loginData = new Gson().fromJson(request.getReader(), Login.class);
		PrintWriter out = response.getWriter();
		JSONObject jsonData = new JSONObject();
		HttpSession oldSession = request.getSession(false);
		try {
			if (oldSession != null) {
				response.setStatus(400);
				jsonData.put("Error Message", "You already logged In!");
				out.println(jsonData);
			} else {
				LoginInputValidation loginValidate = new LoginInputValidation();
				out.println(loginValidate.checkLoginCreadentials(loginData.getUserName(), loginData.getPassWord(),
						request, response));
			}
		} catch (JSONException e) {
			RequestDispatcher rd = request.getRequestDispatcher("ErrorHandlerServlet");
			rd.forward(request, response);
		}
	}

}
