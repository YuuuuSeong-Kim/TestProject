package com.sist.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

public class UpdateBoardActionOK implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		String path = request.getRealPath("data");
		MultipartRequest multi = new MultipartRequest(request, path, 1024 * 1024 * 5, "utf-8",
				new DefaultFileRenamePolicy());
		String oldFname = multi.getParameter("fname");
		String fname = multi.getOriginalFileName("uploadFile");
		BoardVO b = new BoardVO();
		b.setNo(Integer.parseInt(multi.getParameter("no")));
		b.setTitle(multi.getParameter("title"));
		b.setPwd(multi.getParameter("pwd"));
		b.setContent(multi.getParameter("content"));
		b.setFname(oldFname);
		if (fname != null) {
			b.setFname(fname);
		}

		int re = dao.update(b);
		String viewPage = "updateBoardOK.jsp";
		if (re == 1) {
			if (fname != null) {
				File file = new File(path + "/" + oldFname);
				file.delete();
			}
		} else {
			viewPage = "error.jsp";
			request.setAttribute("msg", "게시물 수정에 실패하였습니다.");
		}
		return viewPage;
	}

}
