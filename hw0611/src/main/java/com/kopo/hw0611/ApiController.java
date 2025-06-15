package com.kopo.hw0611;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	private final DB db;
	
	@Autowired
	public ApiController(DB db) throws SQLException {
		this.db = db;
		this.db.setDbTableName("user");
		db.createUserTable();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("/ 컨트롤러 진입");
		return "home";
	}
	// 로그인 페이지를 보여주는 컨트롤러
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String showSignin(Model model) {
		logger.info("signin 컨트롤러 진입");
		return "signin";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showSignup(Model model) {
		logger.info("signup 컨트롤러 진입");	
		return "signup";
	}	
	
	@RequestMapping(value = "/signupAction", method = RequestMethod.POST)
	public String signupAction(HttpServletRequest request, @RequestParam(value = "userType", defaultValue = "user") String userType, @RequestParam(value = "adminKey", required = false) String adminKey, @RequestParam("id") String id, @RequestParam("pwd") String pwd, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address, @RequestParam("created") String created, @RequestParam("lastupdated") String lastupdated) {
		logger.info("signupAction 컨트롤러 진입");
		Map<String, Object> result = new HashMap<>();
		final String ADMIN_KEY = "iamadmin";
		
		// 사용자가 입력한 키가 admin 키와 일치하는지 확인
		if("admin".equals(userType) && !ADMIN_KEY.equals(adminKey)) {
			userType = "user";
		}
		try {
			boolean success = db.signup(id,pwd,userType,name, phone, address, created, lastupdated);
			
			if(success) {
				HttpSession session = request.getSession();
	            session.setAttribute("userName", name);
	            session.setAttribute("userType", userType);
	            User newUser = db.findUserById(id);
	            session.setAttribute("userIdx", newUser.getIdx());
	            
	            return "redirect:/greeting";
			}else {
				return "redirect:/signup?error=fail";
			}
			
		} catch (Exception e) {
	        return "redirect:/signup?error=db";
	    }
	}
	
	// 회원가입 성공 시 greeting 페이지로 이동
	@RequestMapping(value="/greeting", method=RequestMethod.GET)
	public String showGreeting(HttpServletRequest request, Model model) {
	    logger.info("greeting 컨트롤러 진입");

	    try {
	        int totalUsers = db.countTotalUsers();
	        int todayUsers = db.countTodayUsers();

	        // 세션에 저장
	        HttpSession session = request.getSession();
	        session.setAttribute("totalUsers", totalUsers);
	        session.setAttribute("todayUsers", todayUsers);

	        model.addAttribute("totalUsers", totalUsers);
	        model.addAttribute("todayUsers", todayUsers);

	        return "greeting";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}

	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logout(Model model) {
		logger.info("logout 컨트롤러 진입");	
		return "logout";
	}
	
	@RequestMapping(value="/signinAction", method = RequestMethod.POST)
	public String signinAction(HttpServletRequest request, @RequestParam("id") String id, @RequestParam("pwd") String pwd) {
	    try {
	        User user = db.signin(id, pwd);
	        
	        if (user != null) {
	            HttpSession session = request.getSession();
	            session.setAttribute("userName", user.getName());
	            session.setAttribute("userType", user.getUserType());
	            session.setAttribute("userIdx", user.getIdx()); 
	            return "redirect:/greeting";
	        } else {
	            return "redirect:/signin?error=fail";
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:/signin?error=db";
	    }
	}

	
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public String showList(Model model) {
	    try {
	        List<User> users = db.showUserList();
	        model.addAttribute("userList", users);
	        return "userList";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error"; // 에러 페이지 또는 redirect 가능
	    }
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String showDeleteUserPage(Model model) {
	    try {
	        List<User> users = db.showUserList(); // 삭제용 목록 보여주기
	        model.addAttribute("userList", users);
	        return "deleteUser";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}

	@RequestMapping(value = "/deleteUserAction", method = RequestMethod.GET)
	public String deleteUser(@RequestParam("idx") int idx) {
	    try {
	        boolean success = db.deleteUser(idx);
	        if (success) {
	            return "redirect:/deleteUser"; // 목록 새로고침
	        } else {
	            return "redirect:/deleteUser?error=fail";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:/deleteUser?error=exception";
	    }
	}


	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String goMypage(HttpServletRequest request, Model model) {
	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("userIdx") == null) {
	        return "redirect:/signin";
	    }

	    String userIdx = (String) session.getAttribute("userIdx"); // ✅ String으로 캐스팅
	    try {
	        User user = db.getUserByIdx(userIdx); 
	        model.addAttribute("user", user);
	        return "mypage";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}

	@RequestMapping(value = "/mypageUpdate", method = RequestMethod.POST)
	public String updateMypage(HttpServletRequest request,
	                           @RequestParam("phone") String phone,
	                           @RequestParam("address") String address,
	                           @RequestParam(value = "pwd", required = false) String pwd) {
	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("userIdx") == null) {
	        return "redirect:/signin";
	    }

	    String userIdx = (String) session.getAttribute("userIdx");
	    try {
	        boolean success = db.updateUserByIdx(userIdx, phone, address, pwd);
	        if (success) {
	            return "redirect:/mypage?success=true";
	        } else {
	            return "redirect:/mypage?error=fail";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:/mypage?error=db";
	    }
	}



	
	

}
