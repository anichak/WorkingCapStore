package com.cg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cg.domain.CustomerDetails;
import com.cg.domain.Feedback;
import com.cg.domain.JoinOrdersTransaction;
import com.cg.domain.Media;
import com.cg.domain.MediaPath;
import com.cg.domain.Product;
import com.cg.domain.ProductDesc;
import com.cg.domain.ProductDescription;
import com.cg.domain.ProductMedia;
import com.cg.domain.ProductMediaPath;
import com.cg.domain.ProductSold;
import com.cg.domain.ProductWish;
import com.cg.domain.SchemeOffer;
import com.cg.service.ServiceClass;
import org.json.JSONObject;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	public ServiceClass sc;
	@RequestMapping(value="AskUserOrMerchant",method=RequestMethod.GET)
	public String ask(){
		return "Ask";
	}

	@RequestMapping(value="SignUpUser",method=RequestMethod.GET)
	public String signUpUser(){
		return "UserSignUp";
	}
	@RequestMapping(value="AddUser",method=RequestMethod.GET)	
	public String addUser(@RequestParam("n1")String userid,@RequestParam("n2")String fname,@RequestParam("n3")String lname,@RequestParam("n4")String pwd,@RequestParam("n5")String email,@RequestParam("n6")String sec_ques,@RequestParam("n7")String ans,@RequestParam("n8")String add){
		boolean flag=sc.addUser(userid,fname,lname,pwd,email,sec_ques,ans,add);
		String message=new String();
		if (flag) {
			message = "Home";
		} else {
			message = "UserNotAddedMessage";
		}
		return message;
	}	
	@RequestMapping(value="FetchUser",method=RequestMethod.GET)
	public String fetchUser(HttpServletRequest req){
		String userid=req.getParameter("userid");
		Long ts=Long.parseLong(req.getParameter("stamp"));
		
		boolean activated=sc.activateUser(userid,ts);
		String x=new String();
		if(activated){
			
			return x="Home";
		}else{
			x="Error";
		}
		return x;
	}
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String validateLogin(ModelMap map,HttpServletRequest request,
			@RequestParam("email") String u1,
			@RequestParam("password") String p1) {
		boolean valid = sc.validateLogin(request, u1, p1);		
		if (valid) {
			HttpSession ses = request.getSession();

			String s =(String) ses.getAttribute("merchantid");
			String ss=(String) ses.getAttribute("userName");
			map.put("username",ss);
			char a[];
			a=s.toCharArray();
			if (a[0] == 'C' && a[1] == '_') {

				return "Home";
			} else if (a[0] == 'M' && a[1] == '_') {
				return "MerchantHome";
			}
		}
		return "Home";
	}
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request) {
		boolean valid = sc.logoutUser(request);
		if (valid) {
			return "Home";
		} else {
			HttpSession ses = request.getSession();
			String s = (String) ses.getAttribute("merchantid");
			char a[];
			a = s.toCharArray();
			if (a[0] == 'C' && a[1] == '_'){
				return "Home";
			} else if (a[0] == 'M' && a[1] == '_') {
				return "MerchantHome";

			}			return "Home";

		}

	}
	@RequestMapping(value="ChangePwd",method=RequestMethod.GET)
	public String goHome(){
		return "ChangePassword";
	}

	@RequestMapping(value="ForgotPwd",method=RequestMethod.GET)
	public String goForgot(){
		return "ForgotPassword";
	}

	@RequestMapping(value="forgot",method=RequestMethod.GET)
	public String forgotData(ModelMap m,@RequestParam("n1") String userid){
		if(userid.startsWith("C_")){
			m.addAttribute("obj", sc.getDetails(userid));
			return "ForgotPasswordUser";
		}
		if(userid.startsWith("M_")){
			m.addAttribute("obj", sc.getDetailsM(userid));
			return "ForgotPasswordMerchant";
		}
		return null;
	}

	@RequestMapping(value="forgotM",method=RequestMethod.GET)
	public String forgotDataForM(ModelMap m,@RequestParam("n1") String merchantid){
		m.addAttribute("obj", sc.getDetails(merchantid));
		return "ForgotPasswordMerchant";
	}

	@RequestMapping(value="change",method=RequestMethod.GET)	
	public String changePwd(@RequestParam("n1")String userid,@RequestParam("n2")String oldPassword,@RequestParam("n3")String newPassword){
		boolean isChanged=false;
		String x=new String();
		if(userid.startsWith("C_"))			
		{

			isChanged=sc.changePwdService(userid,oldPassword,newPassword);
			
		}

		if(userid.startsWith("M_"))			
		{

			isChanged=sc.changePwdServiceForMerchant(userid, oldPassword, newPassword);
			
		}

		if(isChanged)
		{
			x="Home";
		}
		else
			x="Error";

		return x;
	}


	@RequestMapping(value="submit",method=RequestMethod.GET)
	public String forgotPwd(@RequestParam("n1")String userid,@RequestParam("n2")String secQuestion,@RequestParam("n3")String secAnswer)
	{
		boolean isCorrect=false;
		String x=new String();
		isCorrect=sc.forgotPwd(userid,secQuestion,secAnswer);
		
		if(isCorrect)
			x="Home";
		else
			x="Error";

		return x;

	}

	@RequestMapping(value="submitM",method=RequestMethod.GET)
	public String forgotPwdForMerchant(@RequestParam("n1")String merchantid,@RequestParam("n2")String secQuestion,@RequestParam("n3")String secAnswer)
	{
		boolean isCorrect=false;
		String x=new String();
		isCorrect=sc.forgotPwdForMerchant(merchantid,secQuestion,secAnswer);
		
		if(isCorrect)
			x="Home";
		else
			x="Error";	
		return x;

	}
	/*Abhishek+Mrinal+Sk+Dinesh+Ghanshyam+Vishwanath*/
	@Value("#{img['ipaddress2']}")	
	String ipaddress;
	@RequestMapping(value = "tsearch", method = RequestMethod.GET)
	public String printMessage(ModelMap m, @RequestParam("query") String query) {
		List<Product> leftcontentlist = new ArrayList<Product>();
		List<ProductMedia> midcontentlist = new ArrayList<ProductMedia>();
		leftcontentlist = sc.searchAllBrands();
		midcontentlist = sc.getAllProducts(query);
		m.addAttribute("List", leftcontentlist);
		m.addAttribute("MidList", midcontentlist);
		return "Home";
	}
	@RequestMapping(value = "searchedProduct", method = RequestMethod.GET)
	@ResponseBody
	public void getSearchedProduct(HttpServletRequest request, ModelMap m,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		List<ProductMedia> leftcontentlist = new ArrayList<ProductMedia>();
		String searchdata = request.getParameter("searchdata");
		leftcontentlist = sc.getInitialProducts(searchdata);
		String temp="tdijava";
		int co=0;
		for (ProductMedia p : leftcontentlist) {
			co=0;
			if(temp.equals(p.getP().getProductName())){
				co++;
			}
			else{
				co=0;
			}
			if(co<1){
				out.print("<img src=\""+ipaddress+p.getM().getMediaPath()+"\">");
				out.println("<br>"
						+ p.getP().getProductName() + "<br>"
						+ p.getP().getProductBrand() + "<br>"
						+ p.getP().getProductCost() + "<br>");
				out.println("<a href=\"prod?prod_id="+p.getP().getProductId()+"\">"+p.getP().getProductName()+"</a><br>");
		
		}
			temp=p.getP().getProductName();
		}
	}
	@RequestMapping(value = "getInitialSearchData", method = RequestMethod.GET)
	@ResponseBody
	public void getInitialSearchData(HttpServletRequest request, ModelMap m,
			HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		List<ProductMedia> leftcontentlist = new ArrayList<ProductMedia>();

		String temp="tdijava";
		int co=0;
		leftcontentlist = sc.setSearchData();
		out.print("<datalist id=\"awards\" list=\"awards\"><select>");
		for (ProductMedia p : leftcontentlist) {
			co=0;
			if(temp.equals(p.getP().getProductName())){
				co++;
			}
			else{
				co=0;
			}
			if(co<1){

			out.print("<option value=\"" + p.getP().getProductName()
					+ "\"></option>");
			}
			temp=p.getP().getProductName();
		}
		out.print("</select></datalist>");

	}
	@RequestMapping(value = "getItems", method = RequestMethod.GET)
	@ResponseBody
	public void getComputers(HttpServletRequest request, ModelMap m,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		List<ProductMedia> leftcontentlist = new ArrayList<ProductMedia>();
		String searchdata = request.getParameter("dataString");
		leftcontentlist = sc.getItems(searchdata);
		for (ProductMedia p : leftcontentlist) {
			out.print("<img src=\""+ipaddress+p.getM().getMediaPath()+"\">");
			out.println("<br>"
					+ p.getP().getProductName() + "<br>"
					+ p.getP().getProductBrand() + "<br>"
					+ p.getP().getProductCost() + "<br>");
			out.println("<a href=\"prod?prod_id="+p.getP().getProductId()+"\">"+p.getP().getProductName()+"</a><br>");
		}
	}
//*****************MURSID + ABHISHEK + DINESH + GHANSHYAM*************************************
	@RequestMapping(value = "getProductNames", method = RequestMethod.GET)
	@ResponseBody
	public void getProductNames(HttpServletRequest request, ModelMap m,
			HttpServletResponse response) throws IOException {

		String brandCode = request.getParameter("dataString");
		String typeCode = request.getParameter("typeString");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		List<ProductMedia> leftcontentlist = new ArrayList<ProductMedia>();
		List<Product> midcontentlist = new ArrayList<Product>();
		leftcontentlist = sc.searchRelatedProduct(brandCode);
		out.println("<input type=\"hidden\" name="+brandCode+" value="+brandCode+"id="+brandCode+"class=\"types11\" >");
		String temp="tdijava";
		int co=0;
		for (ProductMedia p : leftcontentlist) {
			co=0;
			if(temp.equals(p.getP().getProductName())){
				co++;
			}
			else{
				co=0;
			}
			if(co<1){
			out.print("<img src=\""+ipaddress+p.getM().getMediaPath()+"\">");			
			out.println("<input type=\"checkbox\" value="+p.getP().getProductName()+"/>"+"<a href=\"prod?prod_id="+p.getP().getProductId()+"\">"+p.getP().getProductName()+"</a>"+"<br>"+p.getP().getProductBrand()+"<br>"+p.getP().getProductCost()+"<br>");
			}
			temp=p.getP().getProductName();
			}

	}
	@RequestMapping(value="/prod",method= RequestMethod.GET)
	public String getAllDesc(ModelMap map,@RequestParam("prod_id")String prod_id,HttpServletResponse response, HttpServletRequest request){
		long vew_count=1;
		String cat_gory=sc.getCatgoryOfgivenProduct(prod_id);
		boolean tt=false;
		tt=sc.setOnGivenD(prod_id,vew_count,cat_gory);
		HttpSession session = request.getSession();
		session.setAttribute("productId", prod_id);
		List<ProductDesc> pd= sc.getDetail(prod_id);
		List<Media> path = sc.getMediaPath(prod_id);
		
		map.put("selectedProduct",prod_id);
		sc.findSimilarProducts(prod_id,map);
		List<SchemeOffer> list = sc.getAllSchemeByProductIDInProduct(prod_id);
		map.addAttribute("listDiscount", list);
		map.addAttribute("list1",pd);	
		map.addAttribute("path",ipaddress+path.get(0).getMediaPath());
		double avg=avgrate(request);
		map.addAttribute("rating",avg);
		return "ProductDescription";
	}
//**************************************ABHISHEK WORK***************************************


	@RequestMapping(value = "getBrandNames", method = RequestMethod.GET)
	@ResponseBody
	public void getBrandNames(HttpServletRequest request, ModelMap m,
			HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		List<Product> leftcontentlist = new ArrayList<Product>();
		List<Product> midcontentlist = new ArrayList<Product>();

		String typeCode = request.getParameter("typeString");

		leftcontentlist = sc.getAllBrands(typeCode);

		for (Product p : leftcontentlist) {
			Product prod = new Product();
			prod.setProductBrand(p.getProductBrand());
			midcontentlist.add(prod);
			if(p.getProductTag().toLowerCase().contains(typeCode) || p.getProductTag().toUpperCase().contains(typeCode)){
			out.println("<input type=\"checkbox\" value=" + p.getProductBrand()
					+ "  name=" + p.getProductBrand() + " id="
					+ p.getProductBrand() + " class=\"bnames\"    >"
					+ p.getProductBrand() + "<br>");
			}
		}
	}

	@RequestMapping(value = "getProductNamesPriceWise", method = RequestMethod.GET)
	@ResponseBody
	public void getProductNamesPriceWise(HttpServletRequest request,
			ModelMap m, HttpServletResponse response) throws IOException {

		String minprice = request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice");
		String brandCode = request.getParameter("brandString");

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		List<ProductMedia> leftcontentlist = new ArrayList<ProductMedia>();
		leftcontentlist = sc.searchRelatedProductPriceWise(brandCode, minprice,
				maxprice);
		String temp="tdijava";
		int co=0;
		for (ProductMedia p : leftcontentlist) {
			
			co=0;
			if(temp.equals(p.getP().getProductName())){
				co++;
			}
			else{
				co=0;
			}
			if(co<1){
			out.print("<img src=\""+ipaddress+p.getM().getMediaPath()+"\">");
			out.println("<input type=\"checkbox\" value="+p.getP().getProductName()+"/>"+"<a href=\"prod?prod_id="+p.getP().getProductId()+"\">"+p.getP().getProductName()+"</a>"+"<br>"+p.getP().getProductBrand()+"<br>"+p.getP().getProductCost()+"<br>");
			}
			temp=p.getP().getProductName();
		}
	}
	//********************************VISWANATH WORK**************************************
	@RequestMapping(value="getSuggestions",method=RequestMethod.GET)
	@ResponseBody
	public void uploadHandle4(HttpServletRequest request,ModelMap m,HttpServletResponse response) throws IOException{

		HttpSession  session=request.getSession();
		String prod_id=(String)session.getAttribute("productId");
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		/*prod_id="P_21M_001";*/
		List<Product> list = sc.getSuggestedProducts(prod_id);
		Map<String,List<Media>> mapmedia=new HashMap<String,List<Media>>();
		
		for(Product p:list){
		String path=sc.getMediaPath(p.getProductId()).get(0).getMediaPath();
		out.print("<img src=\""+ipaddress+path+"\">");
		out.print(p.getProductName());
		out.println(p.getProductBrand());
		out.println(p.getProductCost()+"<br>");
		}
	}
	//*************************MRINAL WORK****************************************


	@RequestMapping(value="postfeedback",method=RequestMethod.GET)
	@ResponseBody public  void postFeedback(HttpServletRequest request,ModelMap map,HttpServletResponse response) throws IOException{

		HttpSession  session=request.getSession();
		String userid=(String) session.getAttribute("userid");
		String pid=(String) session.getAttribute("productId");	
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String rate = request.getParameter("rateString");
		String feed=request.getParameter("feedString"); 

		double rate1=0.0;
		if(!rate.isEmpty()){
			
			rate1=Double.parseDouble(rate);
		}	
		if(userid==null){

			userid="Anonymous";
		}
		
		sc.createFeedback(userid,pid,  feed, rate1 );
		List<Feedback> flist= sc.getAll(pid);
		double avg=avgrate(request);
		out.print("<table>");
		for(Feedback f:flist){ 
			out.print("<tr><td>");
			if(f.getUser().getUserId().equals("userid")){
				out.print("<input type=\"radio\"  name=user id="+f.getFeedbackId()+" class=\"usercheck\" value="+f.getFeedbackId()+">");
			}
			out.println("User Id:\t"+f.getUser().getUserId()+"\tRating--:"+f.getRating()+"\tProd Id:"+f.getId()+"</td><td>\tContent:"+f.getFeedbackContent()+"</td><td>\tDate:"+f.getFeedbackDate()+"<br>");
			out.print("</td></tr>");   
		}
		out.print("</table>"+"<br>Average Rating:"+avg);
		}
	@RequestMapping(value="deletefeedback",method=RequestMethod.GET)
	@ResponseBody public  void deleteFeedback(HttpServletRequest request,ModelMap map,HttpServletResponse response) throws IOException{

		HttpSession  session=request.getSession();
		String userid=(String) session.getAttribute("userid");
		String pid=(String) session.getAttribute("productId");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String hidd = request.getParameter("dataString");
		int feedid;  
		feedid=Integer.parseInt(hidd);		
		List<Feedback> dlist= sc.delete(userid, pid, feedid);
		out.print("<table>");
		for(Feedback f:dlist){ 
			out.print("<tr><td>");
			if(f.getUser().getUserId().equals("userid")){
				out.print("<input type=\"radio\"  name=user id="+f.getFeedbackId()+" class=\"usercheck\" value="+f.getFeedbackId()+">");
			}
			out.println("User Id:"+f.getUser().getUserId()+"</td><td>Content:"+f.getFeedbackContent()+"</td><td>Date:"+f.getFeedbackDate()+"<br>");
			out.print("</td></tr>");

		}
		out.print("</table>");
	}
	@RequestMapping(value="view1feedback",method=RequestMethod.GET)
	@ResponseBody public  void viewFeedback(HttpServletRequest request,ModelMap map,HttpServletResponse response) throws IOException{

		HttpSession  session=request.getSession();
		String userid=(String) session.getAttribute("userid");
		String pid=(String) session.getAttribute("productId");

		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		List<Feedback> flist= sc.getAll(pid);  
		double avg=avgrate(request);
		out.print("<table>");
		for(Feedback f:flist){ 
			out.print("<tr><td>");
			if(f.getUser().getUserId().equals(userid)){	
				out.print("<input type=\"radio\" name=u id="+f.getFeedbackId()+" class=\"usercheck\"  value="+f.getFeedbackId()+">" );
			}
			out.println("User Id:"+f.getUser().getUserId()+"</td><td>Content:"+f.getFeedbackContent()+"</td><td>Date:"+f.getFeedbackDate()+"<br>");
			out.print("</td></tr>");        
		}
		out.print("</table>"+"<br>Average Rating:"+avg);
	}
	public Double avgrate(HttpServletRequest request){		
		HttpSession  session=request.getSession();	
		String pid=(String) session.getAttribute("productId");	
			return sc.average(pid);
	} 


	/*================================ghanshyam controller================================*/
	@RequestMapping(value="getProductNamesBySortOrder111",method=RequestMethod.GET)
	@ResponseBody public  void uploadHandleSortMeO(HttpServletRequest request,ModelMap m,HttpServletResponse response) throws IOException{

		String selectCode1= request.getParameter("typeString");

		if(selectCode1.equals("hl")){
			
			String brandCode = request.getParameter("dataString");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			Product product = new Product();
			List<ProductMedia> leftcontentlist = new ArrayList<ProductMedia>();
			List<Product> midcontentlist=new ArrayList<Product>();
			leftcontentlist=sc.sortRelatedProductOnSelect(brandCode);
			/*=======================ghanshyam jsp==============================*/
			out.println("<input type=\"hidden\" name="+brandCode+" value="+brandCode+"id="+brandCode+"class=\"types11\" >");

			/*========================ghanshyam jsp============================*/
			for (ProductMedia p : leftcontentlist) {
				Product prod = new Product();
				prod.setProductBrand(p.getP().getProductBrand());
				prod.setProductCost(p.getP().getProductCost());
				out.print("<img src=\""+ipaddress+p.getM().getMediaPath()+"\">");
				out.println("<input type=\"checkbox\" value="+p.getP().getProductName()+"/>"+"<a href=\"prod?prod_id="+p.getP().getProductId()+"\">"+p.getP().getProductName()+"</a>"+"<br>"+p.getP().getProductBrand()+"<br>"+p.getP().getProductCost()+"<br>");
			}
		}
		else if(selectCode1.equals("lh")){
			
			String brandCode = request.getParameter("dataString");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			Product product = new Product();
			List<ProductMedia> leftcontentlist=new ArrayList<ProductMedia>();
			List<Product> midcontentlist=new ArrayList<Product>();

			leftcontentlist=sc.sortRelatedProductOnSelectLowerTo(brandCode);
			/*=======================ghanshyam jsp==============================*/
			out.println("<input type=\"hidden\" name="+brandCode+" value="+brandCode+"id="+brandCode+"class=\"types11\" >");

			/*========================ghanshyam jsp============================*/
			for (ProductMedia p : leftcontentlist) {
				Product prod = new Product();
				prod.setProductBrand(p.getP().getProductBrand());
				prod.setProductCost(p.getP().getProductCost());
				out.print("<img src=\""+ipaddress+p.getM().getMediaPath()+"\">");
				out.println("<input type=\"checkbox\" value="+p.getP().getProductName()+"/>"+"<a href=\"prod?prod_id="+p.getP().getProductId()+"\">"+p.getP().getProductName()+"</a>"+"<br>"+p.getP().getProductBrand()+"<br>"+p.getP().getProductCost()+"<br>");
			}
		}
		else if(selectCode1.equals("br")){
			
			String brandCode = request.getParameter("dataString");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			Product product = new Product();
			List<ProductMedia> leftcontentlist=new ArrayList<ProductMedia>();
			List<Product> midcontentlist=new ArrayList<Product>();
			leftcontentlist=sc.sortRelatedProductOnMostView(brandCode);
			/*=======================ghanshyam jsp==============================*/
			
			out.println("<input type=\"hidden\" name="+brandCode+" value="+brandCode+"id="+brandCode+"class=\"types11\" >");

			/*========================ghanshyam jsp============================*/
			for (ProductMedia p : leftcontentlist) {
				Product prod = new Product();
				prod.setProductBrand(p.getP().getProductBrand());
				prod.setProductCost(p.getP().getProductCost());
				out.print("<img src=\""+ipaddress+p.getM().getMediaPath()+"\">");
				out.println("<input type=\"checkbox\" value="+p.getP().getProductName()+"/>"+"<a href=\"prod?prod_id="+p.getP().getProductId()+"\">"+p.getP().getProductName()+"</a>"+"<br>"+p.getP().getProductBrand()+"<br>"+p.getP().getProductCost()+"<br>");

			}

		}

	}
	
	/*================================ghanshyam controller============================*/	

/*Sravanthi*/
	
	
	
	@RequestMapping(value="addtowish",method=RequestMethod.GET)	
	public String addToWishList(HttpServletRequest req,HttpServletResponse res){
		HttpSession hs=req.getSession();
		String userid="C_1234";
		String productid=(String) hs.getAttribute("productId");
		boolean checkresult=sc.chechList(userid, productid);		
		if(checkresult==false){
			return "Error";	
		}
		else{
			boolean result=sc.addToWishList(userid,productid);
			return "Home";
		}
	}
	@RequestMapping(value="wishlist",method=RequestMethod.GET)
	public String getAllWishList(ModelMap map){
		List<Product> wishlist=sc.getAllWishListPro(); 	
		List<ProductMedia> medialist=new ArrayList<ProductMedia>();
		for(Product p:wishlist){
			ProductMedia productimage=new ProductMedia(p,sc.getMediaPath(p.getProductId()).get(0));
			medialist.add(productimage);
		}
		map.put("wlist",medialist);
		return "ViewWishList";
	}
/*TIM+Praveen*/

	@RequestMapping(value = "addtocart",method = RequestMethod.GET)
	public String addToCart(ModelMap map, HttpServletRequest request) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String schemeId=request.getParameter("result");
		session.setAttribute("schemeId",schemeId);
		
		List<ProductSold> items = sc.calculateProductDiscount(session,request); //Praveen FUNCTION to be called
		sc.addCart(items, session);
		map.addAttribute("list", items);
		
		return "DisplayCart";
	}	

	@RequestMapping(value = "DisplayCart", method = RequestMethod.GET)
	public String displayCart(HttpServletRequest request, ModelMap map) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<ProductSold> itemlist = (List<ProductSold>) session.getAttribute("itemlist");
		double total = (double) session.getAttribute("total");
		int qty = (int) session.getAttribute("qty");
		
		String pressdel = request.getParameter("del");
		if (pressdel != null) {
			int item_to_Delete = Integer.parseInt(pressdel);
			sc.deleteCart(item_to_Delete, session);
			double deltotal = (double) session.getAttribute("total");
			int delqty = (int) session.getAttribute("qty");
			map.addAttribute("total", deltotal);
			map.addAttribute("qty", delqty);
			map.addAttribute("itemlist", itemlist );
			
			return "DisplayCart";
		}
		
		String checkout = request.getParameter("chkout");
		if (checkout != null) {
			
			return "CheckOut";
		}
		
		map.addAttribute("itemlist", itemlist);
		map.addAttribute("total", total);
		map.addAttribute("qty", qty);
		
		return "DisplayCart";
	}
	
	@RequestMapping(value="calculateCartDiscount",method=RequestMethod.POST)
	public String calculateCartDiscount(ModelMap map, @RequestParam("r1")String find, HttpServletRequest request){
		
		double total_cost=0;
		HttpSession session = request.getSession();
		List<ProductSold> itemlist = (List<ProductSold>) session.getAttribute("itemlist");
  		ProductSold ps4=itemlist.get(0);
		total_cost=sc.calculateCartByDiscount(itemlist, ps4.getUser_id(), find);
		session.setAttribute("total", total_cost);
		double total = (double) session.getAttribute("total");
		int qty = (int) session.getAttribute("qty");
		
		map.addAttribute("itemlist", itemlist);
		map.addAttribute("total", total);
		map.addAttribute("qty", qty);
		return "CheckOut";
	}
	@RequestMapping(value="/checkQty",method=RequestMethod.POST)
	@ResponseBody
	public String checkQty(@RequestParam(value="qty") String quantity,@RequestParam(value="result")String scheme,HttpServletRequest request){
		HttpSession hs=request.getSession();
		/*hs.setAttribute("productid","1");*/
		String product=(String) hs.getAttribute("productId");
		String result;
		boolean b=sc.findQuantity(product,quantity,scheme);
		if (b) {
			result="true";
		} else {
			result="false";
		}
		
		return result;
	}
	@RequestMapping(value="/checkQtywithnoscheme",method=RequestMethod.POST)
	@ResponseBody
	public String checkQtywithnoscheme(@RequestParam(value="qty") String quantity,HttpServletRequest request){
		HttpSession hs=request.getSession();
		/*hs.setAttribute("productid","1");*/
		String product=(String) hs.getAttribute("productId");
		String scheme="";
		String result=null;
		boolean b=sc.findQuantitywithnoscheme(product,quantity,scheme);
		if (b) {
			result="true";
		} else {
			result="false";
		}
		
		return result;
	}

						/*Subhankar*/
	@RequestMapping(value="customerreturn",method=RequestMethod.POST)
	public String enterReturnedItems(ModelMap map,
			@RequestParam("prodid") String prodid) {

		sc.setReturnedItems(prodid);
		sc.setReturnStatus(prodid);
		String userId="C_1234";
		List<CustomerDetails> l = sc.getOrderDetails(userId);
		map.put("list", l);
		return "UserTransactionDetails";
	}
	
	@RequestMapping(value="view_detail")
	public String getDetail(ModelMap map,@RequestParam("order_id") Long order_id ){
	JoinOrdersTransaction getDetails=sc.getdetails(order_id);
	   map.addAttribute("trans_history", getDetails);
	return "OrderDetails";
	}

	@RequestMapping(value = "buyhistory", method = RequestMethod.GET)
	public String findUser(ModelMap map) {
		String userId="C_1234";
		
		List<CustomerDetails> l = sc.getOrderDetails(userId);
		
		map.put("list", l);
		return "UserTransactionDetails";
	}
	/*Mayur*/
	@RequestMapping("compareproducts")
		public String compareProducts(HttpServletRequest request,ModelMap map){
		HttpSession session=request.getSession();	
		String productId=(String)session.getAttribute("productId");
			String[] comparisonProducts=request.getParameterValues("similarProducts");
			sc.getProductDescription(comparisonProducts,productId,map);
			return "CompareProducts";
		}

}
