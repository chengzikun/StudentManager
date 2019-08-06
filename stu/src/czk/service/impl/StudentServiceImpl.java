package czk.service.impl;

import java.sql.SQLException;
import java.util.List;

import czk.dao.StudentDao;
import czk.dao.impl.StudentDaoImpl;
import czk.domain.PageBean;
import czk.domain.Student;
import czk.service.StudentService;
/**
 * 这是学生业务实现
 * @author ASUS
 *
 */
public class StudentServiceImpl implements StudentService{

	@Override
	public List<Student> findAll() throws SQLException {
		StudentDao dao = new StudentDaoImpl();
		return dao.findAll();
	}

	@Override
	public void insert(Student student) throws SQLException {
		StudentDao dao = new StudentDaoImpl();
		dao.insert(student);
	}

	@Override
	public void delete(int sid) throws SQLException {
		StudentDao dao = new StudentDaoImpl();
		dao.delete(sid);
		
	}

	@Override
	public Student findStudentById(int sid) throws SQLException {
		StudentDao dao = new StudentDaoImpl();
		return dao.findStudentById(sid);
	}

	@Override
	public void update(Student student) throws SQLException {
		StudentDao dao = new StudentDaoImpl();
		dao.update(student);
		
	}

	@Override
	public List<Student> searchStudent(String sname, String sgender) throws SQLException {
		StudentDao dao = new StudentDaoImpl();
		return dao.searchStudent(sname, sgender);
		
	}

	@Override
	public PageBean<Student> findStudentByPage(int currentPage) throws SQLException {
		
		//封装分页的该页数据
		PageBean<Student> pageBean = new PageBean<Student>();
		
		int pageSize = StudentDao.page_size;
		pageBean.setCurrentPage(currentPage);//设置当前页
		pageBean.setPageSize(pageSize);//设置每页显示多少数据
		
		StudentDao dao = new StudentDaoImpl();
		List<Student> list = dao.findStudentByPage(currentPage);
		pageBean.setList(list);//设置这一页的学生数据
		
		
		//总的记录数
		int count = dao.findcount();
		pageBean.setTotalSize(count);
		pageBean.setTotalPage(count % pageSize==0 ? count / pageSize : (count / pageSize) + 1);
			
		return pageBean;
	}

}
