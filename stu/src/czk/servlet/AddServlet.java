package czk.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import czk.domain.Student;
import czk.service.StudentService;
import czk.service.impl.StudentServiceImpl;

/**
 * 用于处理学生的添加请求
 * @author ASUS
 *
 */
@WebServlet("/AddSerlvet")
public class AddServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			//获取提交的信息
			String sname = request.getParameter("sname");
			String gender = request.getParameter("gender");
			String phone = request.getParameter("phone");
			String birthday = request.getParameter("birthday");
			//String hobby = request.getParameter("hobby");
			String info = request.getParameter("info");
			
			String [] h = request.getParameterValues("hobby");
			String hobby = Arrays.toString(h);
			hobby = hobby.substring(1, hobby.length()-1);
			
			
			//添加到数据库
			//String --> date
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
			Student student = new Student(sname, gender, phone, hobby, info, date);
			StudentService service = new StudentServiceImpl();
			service.insert(student);
			
			
			//跳转到列表页
			request.getRequestDispatcher("StudentListServlet").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
