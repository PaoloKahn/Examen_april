package edu.ap.spring.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ap.spring.InhaalExamenRepository;
import edu.ap.spring.model.InhaalExamen;


@Controller
public class RedisController {
   
   @Autowired
   private InhaalExamenRepository examenRedisTemplate;
   
   @RequestMapping("/add")
   @ResponseBody
   public String addForm() {
	   String html = "<html>\r\n" + 
	   		"<head>\r\n" + 
	   		"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\r\n" + 
	   		"\r\n" + 
	   		"<link rel='stylesheet' href='/resources/css/bootstrap.min.css'>\r\n" + 
	   		"<script type='text/javascript' src='/resources/js/app.js'></script>\r\n" + 
	   		"\r\n" + 
	   		"<title>Inhaal examen</title>\r\n" + 
	   		"</head>\r\n" + 
	   		"\r\n" + 
	   		"<body>\r\n" + 
	   		"\r\n" + 
	   		"<div class='well'>\r\n" + 
	   		"<h1>Inhaal examen</h1>\r\n" + 
	   		"<br/>\r\n" + 
	   		"<form method=POST action='new' onsubmit='return validate()'>\r\n" + 
	   		"		<div class='form-group row'>\r\n" + 
	   		"		 	<div class='col-xs-4'>\r\n" + 
	   		"				<label for='firstName'>Your name : </label>\r\n" + 
	   		"		    		<input type='text' class='form-control' name='student' id='student'>\r\n" + 
	   		"	    		</div>\r\n" + 
	   		"	    	</div>\r\n" + 
	   		"		<div class='form-group row'>\r\n" + 
	   		"			<div class='col-xs-4'>\r\n" + 
	   		"				<label for='lastName'>Course: </label>\r\n" + 
	   		"		    		<input type='text' class='form-control' name='exam' id='exam'>\r\n" + 
	   		"		    	</div>\r\n" + 
	   		"	    	</div>\r\n" + 
	   		"<div class='form-group row'>\r\n" + 
	   		"			<div class='col-xs-4'>\r\n" + 
	   		"				<label for='lastName'>Reason: </label>\r\n" + 
	   		"		    		<input type='text' class='form-control' name='reason' id='reason'>\r\n" + 
	   		"		    	</div>\r\n" + 
	   		"	    	</div>\r\n" + 
	   		"	    	\r\n" + 
	   		"		<input type=SUBMIT value='Sumbit'>\r\n" + 
	   		"</form>\r\n" + 
	   		"\r\n" + 
	   		"<br/><br/>\r\n" + 
	   		"<a href='/list'>Lijst all indieningen op</a>\r\n" + 
	   		"</div>\r\n" + 
	   		"\r\n" + 
	   		"</body>\r\n" + 
	   		"</html>";
	   return html;
   }
   
   
   @PostMapping(value = "/new")
   @ResponseBody
   public String addExam(@RequestParam(value = "student") String name, @RequestParam("exam") String course,@RequestParam("reason") String reason) {
	   InhaalExamen e = new InhaalExamen(name,course,reason,Calendar.getInstance().getTime().toString());
	   /* check of het bestaat */
	   for(InhaalExamen in : this.examenRedisTemplate.getAll()) {
		   if(in.getExam().equalsIgnoreCase(e.getExam()) && in.getStudent().equalsIgnoreCase(e.getStudent()) && in.getReason().equalsIgnoreCase(e.getReason())) {
			   return "<html>BESTAAT AL</html>";
		   }
	   }
	  this.examenRedisTemplate.saveInhaalExamen(e);
      return listPerson();
   }
   
   @RequestMapping("/listAll")
   @ResponseBody
   public String listPerson() {

	   String html = "<HTML>";
	   html += "<BODY><h1> Aantal aanvragen :" +this.examenRedisTemplate.getAll().size()+" Inhaal examens</h1><br/><br/><ul>";
	   
	   for(InhaalExamen e : this.examenRedisTemplate.getAll()) {
		   html +="<h3>"+e.getStudent()+"</h3>";
		   html+="<li> Course: "+e.getExam()+"</li>";
		   html+="<li> Date: "+e.getReason()+"</li>";
		   html+="<li> Date: "+e.getDate()+"</li>";
	   }
	   html += "</BODY></HTML>";
	   
	   return html;
   }
   
   @RequestMapping("/list")
   @ResponseBody
   public String listOnePerson(  @RequestParam("student") String student) {
	   
	   String html = "<HTML>";
	   html += "<BODY><h1> Aantal aanvragen :" +this.examenRedisTemplate.getAll().size()+" Inhaal examens</h1><br/><br/><ul>";
	   List<InhaalExamen> list = this.examenRedisTemplate.getAll();
	   list.sort(Comparator.comparing(InhaalExamen::getReason));
	   for(InhaalExamen e : list) {
		   if(student.equalsIgnoreCase(e.getStudent())) {
			   html +="<h3>"+e.getStudent()+"</h3>";
			   html+="<li> Course: "+e.getExam()+"</li>";
			   html+="<li> Date: "+e.getReason()+"</li>";
			   html+="<li> Date: "+e.getDate()+"</li>";
		   }
	   }
	   html += "</BODY></HTML>";
	   
	   return html;
   }
   
   
}
