package czk_01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagerTest {
	public static void main(String[] args) throws IOException {
		//定义文件路径
		String  fileName = "student.txt";
		
		while(true) {
		//主界面
		System.out.println("-------欢迎来到学生管理系统-------");
		System.out.println("1.查看所有学生");
		System.out.println("2.添加学生");
		System.out.println("3.删除学生");
		System.out.println("4.修改学生信息");
		System.out.println("5.退出");
		System.out.println("请输入你的选择：");
		//键盘录入对象
		Scanner input = new Scanner(System.in);
		String choiceString = input.nextLine();
		//switch语句选择
		switch(choiceString) {
		case "1":
			//查看所有学生
			findAllStudent(fileName);
			break;
		case "2":
			//添加学生
			addStudent(fileName);
			break;
		case "3":
			//删除学生
			deleteStudent(fileName);
			break;
		case "4":
			//修改学生信息
			updateStudent(fileName);
			break;
		case "5":
			//退出
		default:
			System.out.println("谢谢你的使用！");
			System.exit(0);
			break;
				
		}
	}
	}
	//从文件中读取数据到集合
	
	public static void readData(String fileName,ArrayList<Student> array) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		String line;
		
		while((line=br.readLine())!=null) {
			String[] datas = line.split(",");
			Student s = new Student();
			s.setId(datas[0]);
			s.setName(datas[1]);
			s.setAge(datas[2]);
			s.setAddress(datas[3]);
			array.add(s);
		}
		br.close();
	}
	
	//把集合中的数据写入文件
	public static void writerData(String fileName,ArrayList<Student> array) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		
		for(int x=0;x<array.size();x++) {
			Student s = array.get(x);
			StringBuilder sb = new StringBuilder();
			sb.append(s.getId()).append(",").append(s.getName()).append(",").append(s.getAge()).append(",").append(s.getAddress());
			
			bw.write(sb.toString());
			bw.newLine();
			bw.flush();
		}
		bw.close();
	}
	
	
	//查看所有学生
	public static void findAllStudent(String fileName) throws IOException {
		//创建集合对象
		ArrayList<Student> array = new ArrayList<Student>();
		//从文件中把数据读取到集合中
		readData(fileName,array);
		
		//先判断集合中是否有数据
		if(array.size()==0) {
			System.out.println("目前没有学生信息，请重新选择操作！");
			return ;
		}
		//\t 一个tab键的位置
		System.out.println("学号\t姓名\t年龄\t居住地");
		for(int x = 0; x<array.size();x++) {
			Student s =array.get(x);
			System.out.println(s.getId()+"\t"+s.getName()+"\t"+s.getAge()+"\t"+s.getAddress());
		}
	}
	
	//添加学生
	public static void addStudent(String fileName) throws IOException {
		//创建集合对象
		ArrayList<Student> array = new ArrayList<Student>();
		//从文件中把数据读取到集合中
		readData(fileName,array);
		//创建键盘录入对象
		Scanner input = new Scanner(System.in);
		
		String id;
		
		
		//为了让代码能回到这里
		while(true) {
			System.out.println("请输入学号");
			id = input.nextLine();
			
			//判断学号有没有被占用
			//定义标记
			boolean flag = false;
			//遍历集合，得到每一个学生
			for(int x=0;x<array.size();x++) {
				Student s = array.get(x);
				//获取该学生学号，与键盘新录入的对象比较
				if(s.getId().equals(id)) {
					flag = true;//说明学号被占用
					break;
				}
			}
			
			if(flag) {
				System.out.println("你输入的学号已经被占用，请重新输入！");
			}else {
				break;//结束循环
			}
		}
		System.out.println("请输入姓名");
		String name = input.nextLine();
		System.out.println("请输入年龄");
		String age = input.nextLine();
		System.out.println("请输入住址");
		String add = input.nextLine();
		
		//创建学生对象
		Student s = new Student();
		s.setId(id);
		s.setName(name);
		s.setAge(age);
		s.setAddress(add);
		
		//把学生对象作为元素添加到集合
		array.add(s);
		//把集合中的数据写回到文件
		writerData(fileName,array);
		
		//提示信息
		System.out.println("添加成功！");
	}
	
	//删除学生
	public static void deleteStudent(String fileName) throws IOException {
		//创建集合对象
		ArrayList<Student> array = new ArrayList<Student>();
		//从文件中把数据读取到集合中
		readData(fileName,array);
		//删除学生的思路：键盘录入一个学号。到集合中查找，判断是否有学生是使用的该学号，存在则删除该学生！
		//创建键盘录入对象
		Scanner input = new Scanner(System.in); 
		System.out.println("请输入你要删除的学号");
		String id = input.nextLine();
		
		//给出学号不存在时候的提示
		
		//定义一个索引
		int index = -1;
		
		//遍历集合
		for(int x=0; x<array.size(); x++) {
			Student s = array.get(x);
			if(s.getId().equals(id)) {
				//array.remove(s);
				index = x;
				break;
			}
		}
		
		if(index == -1) {
			System.out.println("不好意思，你想要删除的学生信息不存在！");
		}else {
			array.remove(index);
			//把集合中的数据写回到文件
			writerData(fileName,array);
			System.out.println("删除成功！");
		}
		
	}
	
	//修改学生
	public static void updateStudent(String fileName) throws IOException {
		//创建集合对象
		ArrayList<Student> array = new ArrayList<Student>();
		//从文件中把数据读取到集合中
		readData(fileName,array);
		Scanner input = new Scanner(System.in);
		System.out.println("请输入你需要修改学生的学号：");
		String id = input.nextLine();
		
		//定义索引
		int index =-1;
		//遍历集合
		for(int x=0; x<array.size(); x++) {
			//获取每一个学生对象
			Student  s = array.get(x);
			//拿学生对象的学号与键盘录入的对象进行比较
			if(s.getId().equals(id)) {
				index = x;
				break;
			}
		}
		
		if(index == -1) {
			System.out.println("你输入的学号对应的学生信息不存在，请重新输入");
		}else {
			System.out.println("请输入学生新姓名：");
			String name = input.nextLine();
			System.out.println("请输入学生新年龄：");
			String age = input.nextLine();
			System.out.println("请输入学生新地址：");
			String add = input.nextLine();
			
			Student s = new Student();
			s.setId(id);
			s.setName(name);
			s.setAge(age);
			s.setAddress(add);
			
			array.set(index, s);
			//把集合中的数据写回到文件
			writerData(fileName,array);
			System.out.println("修改学生成功！");
		}
	}

}
	
