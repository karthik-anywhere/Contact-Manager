package com.example.contactmanager.contact;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.example.contactmanager.store.Contact;
import com.example.contactmanager.store.ContactStore;

@WebServlet("/add-contact")
public class AddContactServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONObject jsonData = new JSONObject();
		Contact c = new Gson().fromJson(request.getReader(), Contact.class);
		try {
			if (c.getFirstName().length() > 1 && c.getLastName().length() > 1) {
				ContactStore contact = new ContactStore();
				String resId = contact.storeContactInStore(c.getFirstName(), c.getLastName(), c.getEmail());
				jsonData.put("id", resId);
			} else {
				response.setStatus(400);
				jsonData.put("error code", 400);
				jsonData.put("error message", "invalid firstname / lastname");
			}
		} catch (JSONException e) {
			RequestDispatcher rd = request.getRequestDispatcher("ErrorHandlerServlet");
			rd.forward(request, response);
		}
		out.println(jsonData);
	}
}
