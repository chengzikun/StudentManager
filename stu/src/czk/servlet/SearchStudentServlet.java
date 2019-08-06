package czk.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import czk.domain.Student;
import czk.service.StudentService;
import czk.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class SearchStudentServlet
 */
@WebServlet("/SearchStudentServlet")
public class SearchStudentServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		try {
			//获取要查询的关键数据，姓名，性别
			String sname = request.getParameter("sname");
			String sgender = request.getParameter("sgender");
			
			//找service
			StudentService service = new StudentServiceImpl();
			List<Student> list = service.searchStudent(sname, sgender);
			
			request.setAttribute("list", list);
			
			//跳转界面
			request.getRequestDispatcher("list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
