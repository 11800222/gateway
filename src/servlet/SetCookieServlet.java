package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
 
import login.GetResponse; 
import login.NewLogin; 

public class SetCookieServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cookie = request.getParameter("cookie");
		System.out.println("ttt");
		String respon = null;  
		try {
			respon=NewLogin.newAndSetHttpClient(cookie); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().println(respon);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
