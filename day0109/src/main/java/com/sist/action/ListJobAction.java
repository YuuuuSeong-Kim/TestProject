package com.sist.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sist.dao.EmpDAO;

public class ListJobAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpDAO dao = EmpDAO.getInstance();
		ArrayList<String> list = dao.findJobList();
		Gson gson = new Gson();
		String jobList = gson.toJson(list);
		request.setAttribute("jobList", jobList);
		return "listJob.jsp";
	}

}
