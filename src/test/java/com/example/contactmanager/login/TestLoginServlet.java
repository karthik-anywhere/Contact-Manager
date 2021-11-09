package com.example.contactmanager.login;

import com.example.contactmanager.auth.Login;
import com.example.contactmanager.auth.LoginServlet;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestLoginServlet extends Mockito {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher rd;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void testServlet() throws Exception {
//    Login l = new Login("karthik","password123");
//
//
//        when(new Gson().fromJson(request.getReader(), Login.class)).thenReturn(new Gson().fromJson(    {"userName" + ":" + "karthik" + "," + "passWord" + ":" + "password123" }));
//        when(request.getSession()).thenReturn(session);
//
//
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//
//        when(response.getWriter()).thenReturn(pw);
//
//        new LoginServlet().doPost(request, response);
//
//        // Verify the session attribute value
//        verify(session).setAttribute("userName", l.getPassWord().hashCode());
//        String result = sw.getBuffer().toString().trim();
//
//        System.out.println("Result: " + result);
//
//        assertEquals("{\n" +
//                "    \"message\": \"Successfully logged in!\"\n" +
//                "}", result);
//    }

}
