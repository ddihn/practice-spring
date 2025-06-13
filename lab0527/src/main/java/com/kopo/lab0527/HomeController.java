package com.kopo.lab0527;

import java.sql.SQLException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		DataReader dataReader = new DataReader("c:\\kopo\\tomcat.sqlite", "students");
		dataReader.open();
		try {
			model.addAttribute("query_result", dataReader.selectData());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			dataReader.close();
		}
		return "home";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertTbl(Locale locale, Model model) {
		DB db = new DB("c:\\kopo\\tomcat.sqlite", "scores");
		db.open();
		try {
			db.insertData("하하", 100, 100);
			db.insertData("랄랄", 100, 100);
			db.insertData("우왕", 100, 100);
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		db.close();
		return "insert";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createTbl(Locale locale, Model model) {
		DB db = new DB("c:\\kopo\\tomcat.sqlite", "scores");
		db.open();
		try {
			db.createTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
		db.close();
		return "insert";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String selectTbl(Locale locale, Model model) {
		DB db = new DB("c:\\kopo\\tomcat.sqlite", "scores");
		db.open();
		try {
			db.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		db.close();
		return "insert";
	}

}
