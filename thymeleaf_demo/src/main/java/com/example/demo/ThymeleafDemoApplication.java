package com.example.demo;

import com.example.demo.bean.Person2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
@Controller
@SpringBootApplication
public class ThymeleafDemoApplication {

	@RequestMapping(value = "/")
	public String index(Model model){
		Person2 single = new Person2("aa",11);
		List<Person2> people = new ArrayList<Person2>();
		Person2 p1 = new Person2("xx",11);
		Person2 p2 = new Person2("yy",22);
		Person2 p3 = new Person2("zz2",33);
		people.add(p1);
		people.add(p2);
		people.add(p3);
		model.addAttribute("singlePerson", single);
		model.addAttribute("people", people);
		return "index"; //视图解析（自带）
	}
	public static void main(String[] args) {
		SpringApplication.run(ThymeleafDemoApplication.class, args);
	}

}
