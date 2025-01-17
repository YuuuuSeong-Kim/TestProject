package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BookDAO;

public class DetailBookAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO dao = new BookDAO();
		int bookid = Integer.parseInt(request.getParameter("bookid"));
		request.setAttribute("vo",dao.detailBook(bookid));
		return "detailBook.jsp";
	}

}
