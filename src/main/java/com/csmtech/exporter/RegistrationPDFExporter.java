package com.csmtech.exporter;

import java.awt.Color;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.csmtech.model.Registration;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class RegistrationPDFExporter {

	private Registration register;

	public RegistrationPDFExporter(Registration register) {
		this.register = register;
	}
	
	
	    public void export(HttpServletResponse response) throws DocumentException, IOException {
	        Document document = new Document(PageSize.A2);
	        PdfWriter.getInstance(document, response.getOutputStream());
	        
	        Font font=null;
	        Paragraph p=null;
	        document.open();
	        	
	        
	        
	        font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(30);
	        font.setColor(Color.RED);
	        p = new Paragraph("Information of Applicant", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	        document.add(p);
	        
	        font = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
	        font.setSize(18);
	        font.setColor(Color.DARK_GRAY);
	        p = new Paragraph("Applicant Id : "+register.getRegistrationId(), font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	        document.add(p);
	       
	        font = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
	        font.setSize(18);
	        font.setColor(Color.DARK_GRAY);
	        p = new Paragraph("Applicant Name : "+register.getApplicantName(), font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	        document.add(p); 
	        
	        font = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
	        font.setSize(18);
	        font.setColor(Color.DARK_GRAY);
	        p = new Paragraph("Email Id : "+register.getEmailId(), font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	        document.add(p);
	        
	        font = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
	        font.setSize(18);
	        font.setColor(Color.DARK_GRAY);
	        p = new Paragraph("Mobile Number : "+register.getMobileNo(), font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	        document.add(p);
	        
	        font = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
	        font.setSize(18);
	        font.setColor(Color.DARK_GRAY);
	        p = new Paragraph("Gender : "+register.getGender(), font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	        document.add(p);
	       
	        font = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
	        font.setSize(18);
	        font.setColor(Color.DARK_GRAY);
	        p = new Paragraph("College Name : "+register.getBranch().getCollege().getCollegeName(), font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	        document.add(p);
	        
	        font = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
	        font.setSize(18);
	        font.setColor(Color.DARK_GRAY);
	        p = new Paragraph("Branch Name : "+register.getBranch().getBranchName(), font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	        document.add(p);
	        
	        document.close();
	         
	    }
}
