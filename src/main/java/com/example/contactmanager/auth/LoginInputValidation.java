package com.example.contactmanager.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginInputValidation {

	public JSONObject checkLoginCreadentials(String firstName, String password, HttpServletRequest request,
			HttpServletResponse response) throws JSONException {
		JSONObject jsonData = new JSONObject();
		if (firstName.equals("karthik") && (password.equals("password123"))) {
			HttpSession newSession = request.getSession(true);
			newSession.setMaxInactiveInterval(5 * 60);
			newSession.setAttribute("userName", password.hashCode());
			jsonData.put("message", "Successfully logged in!");
			return jsonData;
		} else {
			response.setStatus(400);
			jsonData.put("message", "Incorrect UserName/Passord");
			return jsonData;
		}
	}
}
