package com.itbank.mavenStudent;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.mavenStudent.model.StudentDTO;
import com.itbank.mavenStudent.service.StudentMapper;


@Controller
public class StudentController {
	
	@Autowired
	private StudentMapper studentMapper;
	
	@RequestMapping("/student.do") //student.do ��� ���� �������� ���� ���� �޼ҵ�� mapping�� �����
	public String indexStudent() {
		return "student/student";
	}
	//void, String, View��ü, Map, ModelAndView, Model ���� �پ��� ��ȯ���� �����ϴ�.
	
	@RequestMapping("/list.do")
	public String listStudent(HttpServletRequest req) {
		//ArrayList<StudentDTO> list = studentDAO.listStudent();
		ArrayList<StudentDTO> list = (ArrayList)studentMapper.listStudent();
		req.setAttribute("listStudent", list);
		return "student/list";
}
	
	@RequestMapping("/insert.do")
	public String insertStudent(HttpServletRequest req, @ModelAttribute StudentDTO dto) {
		int res = studentMapper.insertStudent(dto);
		String msg = null, url = null;
		if (res>0) {
			msg = "�л���ϼ���!! �л������������ �̵��մϴ�.";
			url = "list.do";
		}else {
			msg = "�л���Ͻ���!! �л������������ �̵��մϴ�.";
			url = "student.do";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "student/message";
		
	}

	@RequestMapping("/delete.do")
	public ModelAndView deleteStudent(@RequestParam String id) {
		int res = studentMapper.deleteStudent(id);		
		ModelAndView mav = new ModelAndView("student/message");
		String msg = null, url = null;
		if (res>0) {
			msg = "�л���������!! �л������������ �̵��մϴ�.";
			url = "list.do";
		}else {
			msg = "�л���������!! �л������������ �̵��մϴ�.";
			url = "student.do";
		}
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		
		return mav;
	}
	
	@RequestMapping("/find.do")
	public String findStudent(HttpServletRequest req, @RequestParam String name) {
		ArrayList<StudentDTO> find = (ArrayList)studentMapper.findStudent(name);
		req.setAttribute("listStudent", find);
		return "student/list";
	} 
}