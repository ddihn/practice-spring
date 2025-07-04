package com.kopo.lab0610;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		System.out.println("index 진입");
		return "index";
	}

	// insert 페이지 띄우기만 하는 애
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Locale locale, Model model) {
		System.out.println("insert 진입");
		DataReader db = new DataReader("c:\\kopo\\tomcat.sqlite", "scores");
		db.open();

		try {
			int result = db.createTable();
			if (result == 0) {
				System.out.println("이미 테이블이 존재합니다.");
			} else {
				System.out.println("테이블을 새로 생성했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return "insert";
	}

	// 폼 보내서 처리 하는 애 -> DB에 넣기
	@RequestMapping(value = "/insertAction", method = RequestMethod.POST)
	public String insertAction(Locale locale, Model model, @RequestParam("name") String name,
			@RequestParam("grade") String grade, @RequestParam("midScore") String midScore,
			@RequestParam("finScore") String finScore) {
		System.out.println("insertAction 진입");
		System.out.println(name);
		System.out.println(grade);
		System.out.println(midScore);
		System.out.println(finScore);

		DataReader db = new DataReader("c:\\kopo\\tomcat.sqlite", "scores");
		db.open();

		try {
			db.insertData(name, grade, midScore, finScore);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		model.addAttribute("text", "전송 성공");
		model.addAttribute("name", name);
		model.addAttribute("grade", grade);
		model.addAttribute("midScore", midScore);
		model.addAttribute("finScore", finScore);

		return "message";
	}

	@RequestMapping(value = "/statics", method = RequestMethod.GET)
	public String showStatic(Locale locale, Model model) {
		System.out.println("statics 진입");
		DataReader db = new DataReader("c:\\kopo\\tomcat.sqlite", "scores");
		db.open();

		try {

			double midSum = db.getSumOfMidScore();
			double finSum = db.getSumOfFinScore();
			double midMean = db.getMeanOfMidScore();
			double finMean = db.getMeanOfFinScore();

			model.addAttribute("midSum", midSum);
			model.addAttribute("finSum", finSum);
			model.addAttribute("midMean", midMean);
			model.addAttribute("finMean", finMean);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return "statics";
	}

	@RequestMapping(value = "/rank", method = RequestMethod.GET)
	public String showRank(Locale locale, Model model) {
		System.out.println("rank 진입");
		DataReader db = new DataReader("c:\\kopo\\tomcat.sqlite", "scores");
		db.open();

		try {
			List<StudentRankDTO> rank = db.getRankList();
			model.addAttribute("rank", rank);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return "rank";
	}

	@RequestMapping(value = "/showStu", method = RequestMethod.GET)
	public String showStu(Locale locale, Model model) {
		System.out.println("showStu 진입");
		DataReader db = new DataReader("c:\\kopo\\tomcat.sqlite", "scores");
		db.open();

		try {
			List<StudentRankDTO> stuList = db.showList();

			model.addAttribute("stuList", stuList);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return "showList";
	}

	@RequestMapping(value = "/delStu", method = RequestMethod.GET)
	public String delStu(Locale locale, Model model, @RequestParam("stuId") String stuId,
			@RequestParam("stuName") String stuName) {
		System.out.println("delStu 진입");
		DataReader db = new DataReader("c:\\kopo\\tomcat.sqlite", "scores");
		db.open();

		try {
			int idx = Integer.parseInt(stuId);
			String name = stuName;
			db.deleteScore(idx, name);
			System.out.println("삭제 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return "redirect:/showStu";
	}

}
