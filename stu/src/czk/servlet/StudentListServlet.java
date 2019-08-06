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
 * 负责查询所有的学生信息，然后呈现到list.jsp页面上
 */
@WebServlet("/StudentListServlet")
public class StudentListServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//1.查询出来所有的学生
			StudentService service = new StudentServiceImpl();
			List<Student> list = service.findAll();
			
			//2.先把数据存到作用域中
			request.setAttribute("list", list);
			
			//3.跳转页面
			request.getRequestDispatcher("list.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
