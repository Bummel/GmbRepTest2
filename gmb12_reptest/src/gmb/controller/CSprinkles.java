package gmb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import muffin.Sprinkles;

public class CSprinkles extends Sprinkles
{
	public HttpSession session;
	public ModelAndView mav;
	
	public CSprinkles(HttpSession session, ModelAndView mav)
	{
		this.session =session;
		this.mav = mav;
	}
}
