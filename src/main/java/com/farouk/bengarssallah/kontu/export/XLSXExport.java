package com.farouk.bengarssallah.kontu.export;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.farouk.bengarssallah.kontu.domain.Account;
import com.farouk.bengarssallah.kontu.domain.Transaction;

public class XLSXExport extends AbstractXlsxView  {

	@Override
	protected void buildExcelDocument(Map model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception  {

		
		Account account = (Account) model.get("account");
        XSSFWorkbook workboook =   new XSSFWorkbook(); 
        
        XSSFSheet sheet = workboook.createSheet(account.getClient()
			       .getName()+ "_" + account.getReference() + "_transactions");
        
        XSSFRow edited = sheet.createRow(0);
		edited.createCell(5).setCellValue("Edited on " + new SimpleDateFormat("dd/MM/yyyy @  HH:mm").format(new Date()));
		
		XSSFRow spacerow0 = sheet.createRow(1);
		spacerow0.createCell(0).setCellValue("  ");
		
		XSSFRow company = sheet.createRow(2);
		company.createCell(0).setCellValue("Client");
		
		XSSFRow spacerow1 = sheet.createRow(3);
		spacerow1.createCell(0).setCellValue("  ");
		
		XSSFRow companytitle = sheet.createRow(4);
		companytitle.createCell(0).setCellValue("Name");
		companytitle.createCell(1).setCellValue(account.getClient().getName());
		
		XSSFRow companysymbol = sheet.createRow(5);
		companysymbol.createCell(0).setCellValue("Email");
		companysymbol.createCell(1).setCellValue(account.getClient().getEmail());
				
		XSSFRow spacerow2 = sheet.createRow(9);
		spacerow2.createCell(0).setCellValue("  ");
				
		XSSFRow header = sheet.createRow(10);
		header.createCell(0).setCellValue("   Type");
		header.createCell(1).setCellValue("   Date");
		header.createCell(2).setCellValue("   Amount");
	
		Collection<Transaction> list =  account.getTransactions(); 
		
		    {
		int i = 11;
		for (Transaction transaction : list) {
			    XSSFRow row = sheet.createRow(i++);
			    String type = transaction.getClass().getSimpleName().equals("PaymentTransaction") ? "payment" : "withdraw";
			    row.createCell(0).setCellValue(type);
			    row.createCell(1).setCellValue(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(transaction.getDate()));
			    row.createCell(2).setCellValue(""+transaction.getAmount());
		         }
		    }
		
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + account.getClient()
				       .getName()+ "_" + account.getReference() + "_transactions" +  ".xlsx");
						
			workboook.write(response.getOutputStream());

		
	}

}
