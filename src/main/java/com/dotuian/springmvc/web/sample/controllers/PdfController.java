package com.dotuian.springmvc.web.sample.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dotuian.springmvc.web.base.controllers.BaseController;
import com.dotuian.springmvc.web.sample.dto.Book;

@Controller
@RequestMapping(value = "/pdf")
public class PdfController extends BaseController{
	
	@RequestMapping(value = "/listbooks", method = RequestMethod.GET)
	public ModelAndView listbooks() {
		List<Book> listBooks = new ArrayList<Book>();
		listBooks.add(new Book("Spring in Action 1.0", "Craig Walls", "1935182358", "June 29th 2011", 31.98F));
		listBooks.add(new Book("Spring in Action 2.0", "Craig Walls", "1935182358", "June 29th 2012", 31.98F));
		listBooks.add(new Book("Spring in Action 3.0", "Craig Walls", "1935182358", "June 29th 2013", 31.98F));
		listBooks.add(new Book("Spring in Action 4.0", "Craig Walls", "1935182358", "June 29th 2014", 31.98F));
		
		// pdfView : 对应 views.properties 中的key
		return new ModelAndView("pdfView", "listBooks", listBooks);
	}
	
	
}
