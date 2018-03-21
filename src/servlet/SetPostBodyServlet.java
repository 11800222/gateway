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

public class SetPostBodyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String sign = request.getParameter("sign");
		String time = request.getParameter("time");
		
		System.out.println(uid);
		System.out.println(sign);
		System.out.println(time);
		String respons=null;
		try {
			respons=NewLogin.SetPostBody(uid, sign, time);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		response.getWriter().println(respons);
		 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
