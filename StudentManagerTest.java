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
		//�����ļ�·��
		String  fileName = "student.txt";
		
		while(true) {
		//������
		System.out.println("-------��ӭ����ѧ������ϵͳ-------");
		System.out.println("1.�鿴����ѧ��");
		System.out.println("2.���ѧ��");
		System.out.println("3.ɾ��ѧ��");
		System.out.println("4.�޸�ѧ����Ϣ");
		System.out.println("5.�˳�");
		System.out.println("���������ѡ��");
		//����¼�����
		Scanner input = new Scanner(System.in);
		String choiceString = input.nextLine();
		//switch���ѡ��
		switch(choiceString) {
		case "1":
			//�鿴����ѧ��
			findAllStudent(fileName);
			break;
		case "2":
			//���ѧ��
			addStudent(fileName);
			break;
		case "3":
			//ɾ��ѧ��
			deleteStudent(fileName);
			break;
		case "4":
			//�޸�ѧ����Ϣ
			updateStudent(fileName);
			break;
		case "5":
			//�˳�
		default:
			System.out.println("лл���ʹ�ã�");
			System.exit(0);
			break;
				
		}
	}
	}
	//���ļ��ж�ȡ���ݵ�����
	
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
	
	//�Ѽ����е�����д���ļ�
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
	
	
	//�鿴����ѧ��
	public static void findAllStudent(String fileName) throws IOException {
		//�������϶���
		ArrayList<Student> array = new ArrayList<Student>();
		//���ļ��а����ݶ�ȡ��������
		readData(fileName,array);
		
		//���жϼ������Ƿ�������
		if(array.size()==0) {
			System.out.println("Ŀǰû��ѧ����Ϣ��������ѡ�������");
			return ;
		}
		//\t һ��tab����λ��
		System.out.println("ѧ��\t����\t����\t��ס��");
		for(int x = 0; x<array.size();x++) {
			Student s =array.get(x);
			System.out.println(s.getId()+"\t"+s.getName()+"\t"+s.getAge()+"\t"+s.getAddress());
		}
	}
	
	//���ѧ��
	public static void addStudent(String fileName) throws IOException {
		//�������϶���
		ArrayList<Student> array = new ArrayList<Student>();
		//���ļ��а����ݶ�ȡ��������
		readData(fileName,array);
		//��������¼�����
		Scanner input = new Scanner(System.in);
		
		String id;
		
		
		//Ϊ���ô����ܻص�����
		while(true) {
			System.out.println("������ѧ��");
			id = input.nextLine();
			
			//�ж�ѧ����û�б�ռ��
			//������
			boolean flag = false;
			//�������ϣ��õ�ÿһ��ѧ��
			for(int x=0;x<array.size();x++) {
				Student s = array.get(x);
				//��ȡ��ѧ��ѧ�ţ��������¼��Ķ���Ƚ�
				if(s.getId().equals(id)) {
					flag = true;//˵��ѧ�ű�ռ��
					break;
				}
			}
			
			if(flag) {
				System.out.println("�������ѧ���Ѿ���ռ�ã����������룡");
			}else {
				break;//����ѭ��
			}
		}
		System.out.println("����������");
		String name = input.nextLine();
		System.out.println("����������");
		String age = input.nextLine();
		System.out.println("������סַ");
		String add = input.nextLine();
		
		//����ѧ������
		Student s = new Student();
		s.setId(id);
		s.setName(name);
		s.setAge(age);
		s.setAddress(add);
		
		//��ѧ��������ΪԪ����ӵ�����
		array.add(s);
		//�Ѽ����е�����д�ص��ļ�
		writerData(fileName,array);
		
		//��ʾ��Ϣ
		System.out.println("��ӳɹ���");
	}
	
	//ɾ��ѧ��
	public static void deleteStudent(String fileName) throws IOException {
		//�������϶���
		ArrayList<Student> array = new ArrayList<Student>();
		//���ļ��а����ݶ�ȡ��������
		readData(fileName,array);
		//ɾ��ѧ����˼·������¼��һ��ѧ�š��������в��ң��ж��Ƿ���ѧ����ʹ�õĸ�ѧ�ţ�������ɾ����ѧ����
		//��������¼�����
		Scanner input = new Scanner(System.in); 
		System.out.println("��������Ҫɾ����ѧ��");
		String id = input.nextLine();
		
		//����ѧ�Ų�����ʱ�����ʾ
		
		//����һ������
		int index = -1;
		
		//��������
		for(int x=0; x<array.size(); x++) {
			Student s = array.get(x);
			if(s.getId().equals(id)) {
				//array.remove(s);
				index = x;
				break;
			}
		}
		
		if(index == -1) {
			System.out.println("������˼������Ҫɾ����ѧ����Ϣ�����ڣ�");
		}else {
			array.remove(index);
			//�Ѽ����е�����д�ص��ļ�
			writerData(fileName,array);
			System.out.println("ɾ���ɹ���");
		}
		
	}
	
	//�޸�ѧ��
	public static void updateStudent(String fileName) throws IOException {
		//�������϶���
		ArrayList<Student> array = new ArrayList<Student>();
		//���ļ��а����ݶ�ȡ��������
		readData(fileName,array);
		Scanner input = new Scanner(System.in);
		System.out.println("����������Ҫ�޸�ѧ����ѧ�ţ�");
		String id = input.nextLine();
		
		//��������
		int index =-1;
		//��������
		for(int x=0; x<array.size(); x++) {
			//��ȡÿһ��ѧ������
			Student  s = array.get(x);
			//��ѧ�������ѧ�������¼��Ķ�����бȽ�
			if(s.getId().equals(id)) {
				index = x;
				break;
			}
		}
		
		if(index == -1) {
			System.out.println("�������ѧ�Ŷ�Ӧ��ѧ����Ϣ�����ڣ�����������");
		}else {
			System.out.println("������ѧ����������");
			String name = input.nextLine();
			System.out.println("������ѧ�������䣺");
			String age = input.nextLine();
			System.out.println("������ѧ���µ�ַ��");
			String add = input.nextLine();
			
			Student s = new Student();
			s.setId(id);
			s.setName(name);
			s.setAge(age);
			s.setAddress(add);
			
			array.set(index, s);
			//�Ѽ����е�����д�ص��ļ�
			writerData(fileName,array);
			System.out.println("�޸�ѧ���ɹ���");
		}
	}

}
	
