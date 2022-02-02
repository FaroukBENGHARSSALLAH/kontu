package com.farouk.bengarssallah.kontu.export;

import java.util.Collection;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.io.OutputStream;
import com.farouk.bengarssallah.kontu.domain.Transaction;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.farouk.bengarssallah.kontu.domain.Account;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.ss.usermodel.Workbook;
import java.util.Map;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public class XLSXExport extends AbstractXlsxView {
    
	protected void buildExcelDocument(final Map model, final Workbook workbook, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final Account account = (Account) model.get("account");
        final XSSFWorkbook workboook = new XSSFWorkbook();
        final XSSFSheet sheet = workboook.createSheet(account.getClient().getName() + "_" + account.getReference() + "_transactions");
        final XSSFRow edited = sheet.createRow(0);
        edited.createCell(5).setCellValue("Edited on " + new SimpleDateFormat("dd/MM/yyyy @  HH:mm").format(new Date()));
        final XSSFRow spacerow0 = sheet.createRow(1);
        spacerow0.createCell(0).setCellValue("  ");
        
        final XSSFRow client = sheet.createRow(2);
        client.createCell(0).setCellValue("Client");
        final XSSFRow spacerow2 = sheet.createRow(3);
        spacerow2.createCell(0).setCellValue("  ");
        final XSSFRow clientName = sheet.createRow(4);
        clientName.createCell(0).setCellValue("Name");
        clientName.createCell(1).setCellValue(account.getClient().getName());
        final XSSFRow clientEmail = sheet.createRow(5);
        clientEmail.createCell(0).setCellValue("Email");
        clientEmail.createCell(1).setCellValue(account.getClient().getEmail());
        final XSSFRow spacerow3 = sheet.createRow(6);
        spacerow3.createCell(0).setCellValue("  ");
        
        final XSSFRow accountrow = sheet.createRow(7);
        accountrow.createCell(0).setCellValue("Client");
        final XSSFRow spacerow4 = sheet.createRow(8);
        spacerow4.createCell(0).setCellValue("  ");
        final XSSFRow acountrowreference = sheet.createRow(9);
        acountrowreference.createCell(0).setCellValue("Reference");
        acountrowreference.createCell(1).setCellValue(account.getReference());
        final XSSFRow acountrowbalance = sheet.createRow(10);
        acountrowbalance.createCell(0).setCellValue("Balance");
        acountrowbalance.createCell(1).setCellValue(String.format("%,14.2f", account.getBalance()).replace(",", "."));
        final XSSFRow acountrowcurrency = sheet.createRow(11);
        acountrowcurrency.createCell(0).setCellValue("Balance");
        acountrowcurrency.createCell(1).setCellValue(account.getReference());
        final XSSFRow spacerow5 = sheet.createRow(12);
        spacerow5.createCell(0).setCellValue("  ");
        
        
        final XSSFRow header = sheet.createRow(13);
        header.createCell(0).setCellValue("   Type");
        header.createCell(1).setCellValue("   Date");
        header.createCell(2).setCellValue("   Amount");
        final Collection<Transaction> list = account.getTransactions();
        int i = 14;
        for (final Transaction transaction : list) {
            final XSSFRow row = sheet.createRow(i++);
            final String type = transaction.getClass().getSimpleName().equals("PaymentTransaction") ? "payment" : "withdraw";
            row.createCell(0).setCellValue(type);
            row.createCell(1).setCellValue(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(transaction.getDate()));
            row.createCell(2).setCellValue("" + transaction.getAmount());
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + account.getClient().getName() + "_" + account.getReference() + "_transactions.xlsx");
        workboook.write((OutputStream)response.getOutputStream());
    }
	
 }