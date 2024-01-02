package com.sist.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

/**
 * Servlet implementation class SistController
 */
//@WebServlet("/SistController")
public class SistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SistController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/")+1);
		String viewPage = "";
		BoardDAO dao = new BoardDAO();
		
		if(cmd.equals("listBoard.do")) {
			viewPage = "listBoard.jsp";
			request.setAttribute("list",dao.findAll());
		}
		else if(cmd.equals("insertBoard.do")) {
			viewPage = "insertBoard.jsp";
		}
		else if(cmd.equals("insertBoardOK.do")) {
			viewPage = "insertBoardOK.jsp";
			String path = request.getRealPath("data");
			System.out.println("path:"+path);
			MultipartRequest multi = new MultipartRequest(request, path,1024*1024*5,"utf-8",new DefaultFileRenamePolicy());
			BoardVO b = new BoardVO();
			b.setNo(dao.getNextNO());
			b.setTitle(multi.getParameter("title"));
			b.setWriter(multi.getParameter("writer"));
			b.setPwd(multi.getParameter("pwd"));
			b.setContent(multi.getParameter("content"));
			b.setFname(multi.getOriginalFileName("uploadFile"));
			b.setIp(request.getRemoteAddr());
			int re = dao.insert(b);
			if (re != 1) {
				viewPage = "error.jsp";
				request.setAttribute("msg", "게시물 등록에 실패하였습니다.");
			}
		}
		else if(cmd.equals("deleteBoard.do")) {
			int no = Integer.parseInt(request.getParameter("no"));
			request.setAttribute("no", no);
			viewPage = "deleteBoard.jsp";
		}
		else if(cmd.equals("updateBoard.do")) {
			int no = Integer.parseInt(request.getParameter("no"));
			BoardVO b = dao.detail(no);
			request.setAttribute("b", b);
			viewPage = "updateBoard.jsp";
		}
		else if(cmd.equals("detailBoard.do")) {
			int no = Integer.parseInt(request.getParameter("no"));
			BoardVO vo = dao.detail(no);
			request.setAttribute("vo", vo);
			viewPage = "detailBoard.jsp";
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/")+1);
		BoardDAO dao = new BoardDAO();
		String path = request.getRealPath("data");
		String viewPage = "";
		if(cmd.equals("deleteBoard.do")) {
			int no = Integer.parseInt(request.getParameter("no"));
			BoardVO vo = dao.detail(no);
			File file = new File(path+"/"+vo.getFname());
			file.delete();
			int re = dao.deleteBoard(no,request.getParameter("pwd"));
			viewPage = "deleteBoardOK.jsp";
			if(re!=1) {
				viewPage = "deleteBoardFail.jsp";
			}
		}
		else if(cmd.equals("updateBoard.do")) {
			MultipartRequest multi = 
					new MultipartRequest(request, 
							path, 
							1024*1024*5, 
							"utf-8", 
							new DefaultFileRenamePolicy());
					String oldFname = multi.getParameter("fname");
					String fname = multi.getOriginalFileName("uploadFile");
					BoardVO b = new BoardVO();
					b.setNo(Integer.parseInt(multi.getParameter("no")));
					b.setTitle(multi.getParameter("title"));
					b.setPwd(multi.getParameter("pwd"));
					b.setContent(multi.getParameter("content"));
					b.setFname(oldFname);		
					if(fname != null) {
						b.setFname(fname);
					}
					
					int re = dao.update(b);
					viewPage = "updateBoardOK.jsp";
					if(re == 1) {
						if(fname != null) {
							File file = new File(path + "/" + oldFname);
							file.delete();
						}
					}else {
						viewPage = "error.jsp";
						request.setAttribute("msg", "게시물 수정에 실패하였습니다.");
					}
		}
		 
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
