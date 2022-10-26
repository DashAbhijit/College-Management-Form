package com.csmtech.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bytebuddy.utility.RandomString;


@WebServlet("/captcha-servlet")
public class CaptchaServlet extends HttpServlet{
	
	private static final long serialVersionUID = -5840561187921381583L;
	
	public static final String FILE_TYPE = "jpeg";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Progma", "no-cache");
		response.setDateHeader("Max-Age", 0);
		
		String captcha2 = generateCaptcha(6);//how many character that you want to see in frontend fox just pass
		
		int width = 180, height = 55;//box size
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.OPAQUE);
		Graphics graphics = bufferedImage.createGraphics();
		graphics.setFont(new Font("Roboto",Font.BOLD,30));
		graphics.setColor(new Color(169, 169, 169));//background colour
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(new Color(7, 3, 6));// font colour
		graphics.drawString(captcha2, 40, 35);//gap between box and string
		
		HttpSession session = request.getSession(true);
		session.setAttribute("captcha", captcha2);
		
		OutputStream outputStream = response.getOutputStream();
		ImageIO.write(bufferedImage, FILE_TYPE, outputStream);
		outputStream.close();
		
	}
	

		
//		---------------------------------------------------------------
		 static String generateCaptcha(int n)
		 {
		 
		  // chose a Character random from this String
		  String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		         + "0123456789"
		         + "abcdefghijklmnopqrstuvxyz";
		 
		  // create StringBuffer size of AlphaNumericString
		  StringBuilder sb = new StringBuilder(n);
		 
		  for (int i = 0; i < n; i++) {
		 
		   // generate a random number between
		   // 0 to AlphaNumericString variable length
		   int index
		    = (int)(AlphaNumericString.length()
		      * Math.random());
		 
		   // add Character one by one in end of sb
		   sb.append(AlphaNumericString
		      .charAt(index));
		  }
		 
		  return sb.toString();
		 }
		 
		


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
