package czk.dao;

import java.sql.SQLException;
import java.util.List;

import czk.domain.Student;

/**
 * 这是针对学生表的数据访问
 * @author ASUS
 *
 */
public interface StudentDao {
	
	int page_size = 3;
	
	/**
	 * 查询当页学生数据
	 * @param currentPage
	 * @return
	 * @throws SQLException
	 */
	List<Student> findStudentByPage(int currentPage) throws SQLException;
	
	/**
	 * 查询所有学生
	 * @return List<Student>
	 */
	List<Student> findAll() throws SQLException;
	
	/**
	 * 根据ID查询单个学生对象
	 * @param sid
	 * @return
	 * @throws SQLException
	 */
	Student findStudentById(int sid) throws SQLException;
	
	/**
	 * 模糊查询，根据姓名或者根据性别，或者两者都有
	 * @param sname
	 * @param sgender
	 * @throws SQLException
	 */
	List<Student> searchStudent(String sname,String sgender) throws SQLException;
	
	/**
	 * 添加学生
	 * @param student 需要添加到数据库的学生对象
	 * @throws SQLException
	 */
	void insert(Student student) throws SQLException;
	
	/**
	 * 删除学生
	 * @param sid 按照学生id删除
	 * @throws SQLException
	 */
	void delete(int sid) throws SQLException;
	
	/**
	 * 更新学生信息
	 * @param student
	 * @throws SQLException
	 */
	void update(Student student) throws SQLException;
	
	/**
	 * 查询总的学生记录数
	 * @return
	 * @throws SQLException
	 */
	int findcount() throws SQLException;
	

	
}
