package com.kopo.lab0611.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kopo.lab0611.DB;
import com.kopo.lab0611.StudentRankDTO;
import com.kopo.lab0611.util.HashUtil;
import com.kopo.lab0611.util.TimeUtil;

@Controller
public class ApiController {
	private static final String DB_FILE = "c:\\kopo\\tomcat.sqlite";
	private static final String SCORE_TABLE = "scores";
	private static final String USER_TABLE = "user";

	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public HashMap<String, String> create(Locale locale, Model model) throws Exception {
		HashMap<String, String> data = new HashMap<>();

		DB db = new DB(DB_FILE, USER_TABLE);
		try {
			db.createTable();
			data.put("message", "테이블이 생성되었습니다.");
			System.out.println(data.values());
		} catch (Exception e) {
			e.printStackTrace();
			data.put("message", "테이블 생성에 실패했습니다.");
			System.out.println("DB 생성에 실패했습니다.");
		}

		return data;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showSignupPage(Locale locale, Model model) {
		return "signUp";
	}

	@RequestMapping(value = "/signupAction", method = RequestMethod.POST)
	public String signUp(@RequestParam("id") String id, @RequestParam("pw") String pw,
			@RequestParam(value = "userType", defaultValue = "user") String userType,
			@RequestParam(value = "adminKey", required = false) String adminKey, @RequestParam("name") String name,
			@RequestParam("phone") String phone, @RequestParam("address") String address,
			RedirectAttributes redirectAttrs) throws Exception {
		String hashedPW = HashUtil.sha512(pw);
		String now = TimeUtil.getCurrentTimestamp();
		String SECRET_KEY = "IAMADMIN";
		System.out.println(name);
		System.out.println(address);
		if (SECRET_KEY.equals(adminKey)) {
			System.out.println(adminKey);
			userType = "ADMIN";
		}
		System.out.println(userType);
		DB db = new DB(DB_FILE, USER_TABLE);
		try {
			db.insertData(id, hashedPW, name, userType, phone, address, now, now);
			// 플래시 속성으로 1회성 메시지 전달
			redirectAttrs.addFlashAttribute("msg", "회원가입이 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("error", "회원가입 중 오류가 발생했습니다.");
			// 실패 시, 다시 가입 폼으로
			return "redirect:/signup";
		}
		// 성공 시, 메인 페이지(홈)로
		return "redirect:/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(Locale locale, Model model) {
		return "login";
	}

	@RequestMapping(value = "/loginAction", method = RequestMethod.POST)
	public String login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session,
			RedirectAttributes redirectAttrs) throws Exception {
		String hashedPW = HashUtil.sha512(pw);

		DB db = new DB(DB_FILE, USER_TABLE);
		try {
			if (db.checkUser(id, hashedPW)) {
				System.out.println("로그인 성공");
				// DB 에서 id/hashedPw 일치 확인 후 userType 조회
				String userType = db.getUserType(id); // "ADMIN" 또는 "USER"

				session.setAttribute("loginId", id);
				session.setAttribute("userType", userType);
				redirectAttrs.addFlashAttribute("msg", "로그인이 완료되었습니다.");
			}
			return "redirect:/dashboard";
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("error", "로그인 실패");
			// 실패 시, 다시 가입 폼으로
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(HttpSession session) {
		String type = (String) session.getAttribute("userType");
		if ("ADMIN".equals(type)) {
			// 관리자용 성적 관리 페이지
			return "redirect:/adminDashboard";
		} else {
			// 일반 사용자는 통계/입력 페이지
			return "redirect:/userDashboard";
		}
	}

	@RequestMapping("/adminDashboard")
	public String showAdminDashboard(Model model, HttpSession session) throws Exception {
		// (선택) 세션 확인: userType 이 ADMIN 아니면 접근 불가
		String type = (String) session.getAttribute("userType");
		if (!"ADMIN".equals(type)) {
			return "redirect:/accessDenied";
		}

		DB db = new DB(DB_FILE, SCORE_TABLE);
		List<StudentRankDTO> list = db.showList();
		model.addAttribute("scoreList", list);
		return "adminDashboard";
	}

	@RequestMapping("/admin/deleteScore")
	public String deleteScore(@RequestParam int idx, @RequestParam String name, RedirectAttributes ra)
			throws Exception {
		DB db = new DB(DB_FILE, SCORE_TABLE);
		db.deleteScore(idx, name);
		ra.addFlashAttribute("msg", "성적이 삭제되었습니다.");
		return "redirect:/adminDashboard";
	}

	@RequestMapping("/userDashboard")
	public String showUserDashboard(Model model, HttpSession session) throws Exception {
		if (session.getAttribute("loginId") == null) {
			return "redirect:/login";
		}

		DB db = new DB(DB_FILE, SCORE_TABLE);
		model.addAttribute("sumMid", db.getSumOfMidScore());
		model.addAttribute("sumFin", db.getSumOfFinScore());
		model.addAttribute("avgMid", db.getMeanOfMidScore());
		model.addAttribute("avgFin", db.getMeanOfFinScore());
		model.addAttribute("rankList", db.getRankList());
		return "userDashboard";
	}

	@RequestMapping(value = "/admin/insertScore", method = RequestMethod.POST)
	public String insertAction(Locale locale, Model model, @RequestParam("name") String name,
			@RequestParam("grade") String grade, @RequestParam("midScore") String midScore,
			@RequestParam("finScore") String finScore, RedirectAttributes ra) {
		System.out.println("insertAction 진입");
		System.out.println(name);
		System.out.println(grade);
		System.out.println(midScore);
		System.out.println(finScore);

		DB db = new DB(DB_FILE, SCORE_TABLE);

		try {
			db.insertScore(name, grade, midScore, finScore);
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

		ra.addFlashAttribute("msg", "성적이 등록되었습니다.");
		return "redirect:/adminDashboard";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes ra) {
		session.invalidate();
		ra.addFlashAttribute("msg", "로그아웃 되었습니다.");
		return "redirect:/login";
	}
}
