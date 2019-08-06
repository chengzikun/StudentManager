package czk.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import czk.dao.StudentDao;
import czk.domain.Student;
import czk.util.JDBCUtil02;
import czk.util.TestUtils;

/**
 * 这是StudentDaoImpl的实现，针对前面定义的规范，做出具体的实现。
 * @author ASUS
 *
 */
public class StudentDaoImpl implements StudentDao{

	/**
	 * 查询所有学生
	 * @throws SQLException 
	 */
	@Override
	public List<Student> findAll() throws SQLException {
		 QueryRunner runner = new QueryRunner(JDBCUtil02 .getDataSource());
		 return runner.query("select *from stu",new BeanListHandler<Student>(Student.class));

	}

	@Override
	public void insert(Student student) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02 .getDataSource());
		runner.update("insert into stu values(null,?,?,?,?,?,?)",
				student.getSname(),
				student.getGender(),
				student.getPhone(),
				student.getBirthday(),
				student.getHobby(),
				student.getInfo()
				);
		
	}

	@Override
	public void delete(int sid) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02 .getDataSource());
		runner.update("delete from stu where sid = ?",sid);
		
	}

	@Override
	public Student findStudentById(int sid) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02 .getDataSource());
		return  runner.query("select * from stu where sid = ?", new BeanHandler<Student>(Student.class),sid);
	}

	@Override
	public void update(Student student) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02 .getDataSource());
		runner.update("update stu set sname=?,gender=?,phone=?,birthday=?,hobby=?,info=? where sid= ?",
				student.getSname(),
				student.getGender(),
				student.getPhone(),
				student.getBirthday(),
				student.getHobby(),
				student.getInfo(),
				student.getSid()
				);
	}

	@Override
	public List<Student> searchStudent(String sname, String sgender) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02 .getDataSource());
		
		String sql = "select * from stu where 1 = 1";
		List<String> list = new ArrayList<String>();
		
		if(!TestUtils.isEmpty(sname)) {
			sql = sql + " and sname like ?";
			list.add("%"+sname+"%");
		}
		if(!TestUtils.isEmpty(sgender)) {
			sql = sql +" and gender = ?";
			list.add(sgender);
		}
		
		return runner.query(sql, new BeanListHandler<Student>(Student.class),list.toArray());
		
		
	}

	@Override
	public List<Student> findStudentByPage(int currentPage) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02 .getDataSource());
		return runner.query("select * from stu limit ? offset ?", new BeanListHandler<Student>(Student.class), page_size,(currentPage-1)*page_size);		
	}

	@Override
	public int findcount() throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02 .getDataSource());
		long result = (long) runner.query("select count(*) from stu", new ScalarHandler());
		int r = (int)result;
		return r;
		
	}

	

}
