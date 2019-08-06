package czk.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import czk.domain.PageBean;
import czk.domain.Student;
import czk.service.StudentService;
import czk.service.impl.StudentServiceImpl;

/**
 * 这是用于分页显示学生列表的Servlet
 * @author ASUS
 *
 */
@WebServlet("/StudentListPageServlet")
public class StudentListPageServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//1.获取要显示的页码数
			int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
			
			//2.根据指定的页数，去获取该页面的数据回来
			StudentService service = new StudentServiceImpl();
			PageBean pageBean = service.findStudentByPage(currentPage);
			
			request.setAttribute("pageBean", pageBean);
			
			//3.跳转界面
			request.getRequestDispatcher("list_page.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
