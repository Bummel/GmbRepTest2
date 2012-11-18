package gmb.controller;

import java.lang.reflect.InvocationTargetException;

import gmb.model.member.Admin;
import gmb.model.member.Customer;
import gmb.model.member.Employee;
import gmb.model.member.Member;
import gmb.model.member.Notary;

import javax.servlet.http.HttpSession;

import muffin.Muffin;
import muffin.MuffinMethodNotFoundException;

import org.salespointframework.core.user.PersistentUserManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController 
{
	private final PersistentUserManager userManager = new PersistentUserManager();
	
//	@RequestMapping({"/","/home"})
	@RequestMapping("/")
	public String home()
	{
		return "/home";
	}

	@RequestMapping("/tologin")
	public ModelAndView tologin(HttpSession session, ModelAndView mav)
	{
		try 
		{
			Member member = userManager.getUserByToken(Member.class, session);

			if(member != null && mav != null)
				return (ModelAndView)Muffin.invoke(this, "to_index", member, new CSprinkles(session, mav));

			mav.setViewName("/login"); 
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | MuffinMethodNotFoundException e) 
		{
			e.printStackTrace();
		}

		return mav;
	}
	
	//////////////////////////////////////////////////////////////////
	//==============================================================//
	public ModelAndView to_index(Member member, CSprinkles args) 
	{
		args.mav.setViewName("/loggedIn"); 
		return args.mav;
	}
	
	public ModelAndView to_index(Customer member, CSprinkles args) 
	{
		args.mav.setViewName("/customer_index"); 
		return args.mav;
	}

	public ModelAndView to_index(Employee member, CSprinkles args) 
	{
		args.mav.setViewName("/employee_index"); 
		return args.mav;
	}
	
	public ModelAndView to_index(Admin member, CSprinkles args) 
	{
		args.mav.addObject("members", userManager.find(Member.class));
		args.mav.setViewName("/admin_index"); 
		return args.mav;
	}
	
	public ModelAndView to_index(Notary member, CSprinkles args) 
	{
		args.mav.setViewName("/notary_index"); 
		return args.mav;
	}
	//==============================================================//
	//////////////////////////////////////////////////////////////////

	
	@RequestMapping("/toregistration")
	public String registration()
	{
		return "/register";
	}
	
//	@RequestMapping("/toindex")
//	public String index()
//	{
//		return "register";
//	}
}