package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExampleServlet01
 */
@WebServlet("/hello.do")
public class ExampleServlet01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.service(req, resp);
		
		//응답방식 먼저 설정.
		resp.setContentType("text/html;charset=utf-8");
		
		//출력스트림을 생성
		PrintWriter out = resp.getWriter();
		
		String html = "<html>"
				+ "<body>"
				+ "<h1>이클립스로 서블릿 만들기</h1>"
				+ "</body>"
				+ "</html>";
				
		
		out.print(html);
		out.close();
	}
    

}
