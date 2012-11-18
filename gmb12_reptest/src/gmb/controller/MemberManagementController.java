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
import org.salespointframework.core.user.UserIdentifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberManagementController
{
	private final PersistentUserManager userManager = new PersistentUserManager();

	@RequestMapping("/registerNew")
	public ModelAndView registerNew(HttpSession session, ModelAndView mav,
			@RequestParam("PARAM_ID") String userIdentifier,
			@RequestParam("PARAM_NAME") String name,
			@RequestParam("PARAM_PASSWORD") String password)
	{
		if(userManager.get(Member.class, new UserIdentifier(userIdentifier)) != null)
		{
			mav.setViewName("/home"); 
			return mav;
		}

		Member member = new Customer(userIdentifier, name, password);
		userManager.add(member);

		mav.setViewName("/home"); 
		return mav;
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(HttpSession session, ModelAndView mav,
			@RequestParam("PARAM_IDENTIFIER") UserIdentifier userIdentifier,
			@RequestParam("PARAM_PASSWORD") String password) 
	{	
		try 
		{
			Member member = userManager.get(Member.class, userIdentifier);

			if(member != null) 
			{
				if(!member.hasCapability("activated"))
				{
					mav.setViewName("/home"); 
					return mav;
				}
				
				if(member.verifyPassword(password)) 
				{
					userManager.login(member, session);

					return (ModelAndView)Muffin.invoke(this, "to_index", member, new CSprinkles(session, mav));
				}
			} 
		 
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | MuffinMethodNotFoundException e) 
		{
			e.printStackTrace();
			mav.setViewName("/failed");
		}

		return mav;
	}

	@RequestMapping("/toindex")
	public ModelAndView to_index(HttpSession session, ModelAndView mav) 
	{	
		Member member = userManager.getUserByToken(Member.class, session);
		
		try 
		{
			return (ModelAndView)Muffin.invoke(this, "to_index", member, new CSprinkles(session, mav));
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

	@RequestMapping("/logout")
	public String logout(HttpSession session) 
	{
		userManager.logout(session);
		return "home";
	}
	
	@RequestMapping("/toaccount")
	public ModelAndView account(HttpSession session, ModelAndView mav) 
	{
		final PersistentUserManager userManager = new PersistentUserManager();
		
		Member member = userManager.getUserByToken(Member.class, session);
		member = userManager.get(Member.class, member.getIdentifier());
		
		mav.addObject("member", member);
		mav.setViewName("/accountsett"); 
		return mav;
	}

	@RequestMapping(value="/accountsett_change", method=RequestMethod.POST)
	public ModelAndView accountsett_change(HttpSession session, ModelAndView mav,
			@RequestParam("PARAM_MID") UserIdentifier mid,
			@RequestParam("PARAM_NAME") String name,
			@RequestParam("PARAM_PASSWORD") String password) 
	{		
		final PersistentUserManager userManager = new PersistentUserManager();
		
		Member member = userManager.get(Member.class, mid);
		
		if(!name.equals(""))
			member.setName(name);

		if(!password.equals(""))
			member.changePassword(password);

		userManager.update(member);
		
		mav.addObject("member", member);
		mav.setViewName("/accountsett"); 
		return mav;
	}
	
//	@RequestMapping("/tofaccount/{mid}")
//	public ModelAndView faccount(HttpSession session, ModelAndView mav, 
//			@PathVariable("mid") UserIdentifier mid) 
	@RequestMapping("/tofaccount")
	public ModelAndView faccount(HttpSession session, ModelAndView mav, 
			@RequestParam("PARAM_MID") UserIdentifier mid) 
	{
		final PersistentUserManager userManager = new PersistentUserManager();
		
		Member member = userManager.get(Member.class, mid);

		mav.addObject("member", member);
		mav.setViewName("/accountsett"); 
		return mav;
	}
	
	@RequestMapping("/account_activate")
	public ModelAndView account_activate(HttpSession session, ModelAndView mav, 
			@RequestParam("PARAM_MID") UserIdentifier mid) 
	{	
		Member member = userManager.get(Member.class, mid);
		member.activate();
		userManager.update(member);

		mav.addObject("members", userManager.find(Member.class));
		mav.setViewName("/admin_index"); 
		
		return mav;
	}
}
