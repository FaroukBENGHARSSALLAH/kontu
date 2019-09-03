package com.farouk.bengarssallah.kontu.export;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.farouk.bengarssallah.kontu.domain.Account;
import com.farouk.bengarssallah.kontu.domain.Transaction;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFExport extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest request,
		                            HttpServletResponse response) throws Exception {

		Account account = (Account) model.get("account");
        		
		
		Paragraph date = new Paragraph("Edited on " + new SimpleDateFormat("dd/MM/yyyyy @ HH:mm").format(new Date()));
		date.setAlignment(Element.ALIGN_RIGHT);
		date.setSpacingAfter(10);
		date.setSpacingBefore(10);
		document.add(date);
		
		Paragraph title = new Paragraph("Account Transaction History", new Font(Font.TIMES_ROMAN, 25, Font.BOLD));
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(30);
		title.setSpacingBefore(10);
		document.add(title);
		
		Paragraph CLIENT = new Paragraph("CLIENT");
		CLIENT.setAlignment(Element.ALIGN_LEFT);
		CLIENT.setSpacingAfter(10);
		CLIENT.setSpacingBefore(10);
		document.add(CLIENT);
		
		String clientdetails = "reference    :     " +  account.getClient().getReference() + " \n" +
		                  "client name :     " +  account.getClient().getName()  + "\n" +
				          "client email  :     " + account.getClient().getEmail();
		        
		Paragraph clientdetail = new Paragraph(clientdetails);
		clientdetail.setAlignment(Element.ALIGN_LEFT);
		clientdetail.setSpacingAfter(30);
		clientdetail.setSpacingBefore(10);
		document.add(clientdetail);
		
		
		PdfPTable table = new PdfPTable(3);
		table.addCell("            Type");
		table.addCell("            Date");
		table.addCell("            Amount");

		Collection<Transaction> list =  account.getTransactions(); 
		for (Transaction transaction : list) {
			    String type = transaction.getClass().getSimpleName().equals("PaymentTransaction") ? "payment" : "withdraw";
			    table.addCell("       " + type);
			    table.addCell("       " + new SimpleDateFormat("dd/MM/yyyyy @ HH:mm").format(new Date()));
			    table.addCell("       " + ""+transaction.getAmount());
			
		}
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=" + account.getClient()
				       .getName()+ "_" + account.getReference() + "_transactions.pdf");
		
		

		document.add(table);
		document.close();
	}
}
