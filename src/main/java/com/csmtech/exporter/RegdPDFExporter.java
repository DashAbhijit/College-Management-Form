package com.csmtech.exporter;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.csmtech.model.Registration;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class RegdPDFExporter {

private List<Registration> listRegisters;
    
    public RegdPDFExporter(List<Registration> listRegisters) {
        this.listRegisters = listRegisters;
    }
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Regd Id", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Applicant Name", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Date Of Birth", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Email Id", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Mobile No", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Document", font));
        table.addCell(cell);       
        
        cell.setPhrase(new Phrase("Branch Name", font));
        table.addCell(cell); 
    }
    private void writeTableData(PdfPTable table) {
        for (Registration regd : listRegisters) {
            table.addCell(String.valueOf(regd.getRegistrationId()));
            table.addCell(regd.getApplicantName());
            table.addCell((String.valueOf(regd.getDob())));
            table.addCell(regd.getEmailId());
            table.addCell(regd.getMobileNo());
            table.addCell(regd.getImagePath());
            table.addCell(regd.getBranch().getBranchName());
            
        }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.RED);
         
        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(80f);
        table.setWidths(new float[] {1.5f, 3.5f, 2.0f, 3.0f, 3.0f, 2.5f,2.0f});
        table.setSpacingBefore(8);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}
