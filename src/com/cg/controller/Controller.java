package com.cg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.domain.AdvertisementMedia;
import com.cg.service.ServiceClass;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {
	
	@Value("#{img['ipaddress2']}")	
	String ipaddress;
	@Autowired
	public ServiceClass sc;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showHome(ModelMap map){
		System.out.println("here ok");
		List<AdvertisementMedia> adlist=sc.getAdDetails();
		 List<String> mediapath=new ArrayList<>();
		 for(AdvertisementMedia am:adlist){
			 System.out.println("here");
			 String newMediaPath=ipaddress+am.getMediaPath();
			 System.out.println(ipaddress);
			 mediapath.add(newMediaPath);
		 }
		map.put("list",adlist);
		map.put("list2",mediapath);
		return "Home";
	}	
	@RequestMapping(value="admin",method=RequestMethod.GET)
	public String showAdminLogin(){
		return "Admin_Login";
	}
	@RequestMapping(value="search",method=RequestMethod.GET)
	public String showSearch(HttpServletRequest request,ModelMap map){
		HttpSession session=request.getSession();
		map.addAttribute("session", session);
		String ss=(String) session.getAttribute("userName");
		map.addAttribute("username", ss);
		return "SearchResults";
	}
	
	
	//Abhishek Main Menu
	@RequestMapping(value="Computers",method=RequestMethod.GET)
	public String showComputers(HttpServletRequest request,ModelMap map){
		HttpSession session=request.getSession();
		map.addAttribute("session", session);
		String ss=(String) session.getAttribute("userName");
		map.addAttribute("username", ss);
		return "Computers";
	}
	@RequestMapping(value="Mobiles",method=RequestMethod.GET)
	public String showMobiles(HttpServletRequest request,ModelMap map){
		HttpSession session=request.getSession();
		map.addAttribute("session", session);
		String ss=(String) session.getAttribute("userName");
		map.addAttribute("username", ss);
		return "Mobiles";
	}
	@RequestMapping(value="Cosmetics",method=RequestMethod.GET)
	public String showCosmetics(HttpServletRequest request,ModelMap map){
		HttpSession session=request.getSession();
		map.addAttribute("session", session);
		String ss=(String) session.getAttribute("userName");
		map.addAttribute("username", ss);
		return "Cosmetics";
	}
	@RequestMapping(value="Cloth",method=RequestMethod.GET)
	public String showCloths(HttpServletRequest request,ModelMap map){
		HttpSession session=request.getSession();
		map.addAttribute("session", session);
		String ss=(String) session.getAttribute("userName");
		map.addAttribute("username", ss);
		return "Cloth";
	}
	@RequestMapping(value="Footwear",method=RequestMethod.GET)
	public String showFootwears(HttpServletRequest request,ModelMap map){
		HttpSession session=request.getSession();
		map.addAttribute("session", session);
		String ss=(String) session.getAttribute("userName");
		map.addAttribute("username", ss);
		return "Footwear";
	}

}
