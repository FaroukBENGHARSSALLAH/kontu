package com.farouk.bengarssallah.kontu.export;

import java.util.Collection;
import com.farouk.bengarssallah.kontu.domain.Transaction;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.Font;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.farouk.bengarssallah.kontu.domain.Account;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import java.util.Map;
import org.springframework.web.servlet.view.document.AbstractPdfView;

public class PDFExport extends AbstractPdfView {
	
    protected void buildPdfDocument(final Map model, final Document document, final PdfWriter writer, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final Account account = (Account) model.get("account");
        final Paragraph date = new Paragraph("Edited on " + new SimpleDateFormat("dd/MM/yyyyy @ HH:mm").format(new Date()));
        date.setAlignment(2);
        date.setSpacingAfter(10.0f);
        date.setSpacingBefore(10.0f);
        document.add(date);
        
        final Paragraph title = new Paragraph("Account Transaction History", new Font(2, 25.0f, 1));
        title.setAlignment(1);
        title.setSpacingAfter(30.0f);
        title.setSpacingBefore(10.0f);
        document.add(title);
        
        final Paragraph CLIENT = new Paragraph("CLIENT");
        CLIENT.setAlignment(0);
        CLIENT.setSpacingAfter(10.0f);
        CLIENT.setSpacingBefore(10.0f);
        document.add(CLIENT);
        
        final String clientdetails = "Reference    :     " + account.getClient().getReference() + " \nClient name :     " + account.getClient().getName() + "\nClient email  :     " + account.getClient().getEmail();
        final Paragraph clientdetail = new Paragraph(clientdetails);
        clientdetail.setAlignment(0);
        clientdetail.setSpacingAfter(30.0f);
        clientdetail.setSpacingBefore(10.0f);
        document.add(clientdetail);
        
        final Paragraph ACCOUNT = new Paragraph("ACCOUNT");
        ACCOUNT.setAlignment(0);
        ACCOUNT.setSpacingAfter(10.0f);
        ACCOUNT.setSpacingBefore(10.0f);
        document.add(ACCOUNT);
		
        final String accountdetails = "Reference    :     " + account.getReference() + " \nBalance :     " + String.format("%,14.2f", account.getBalance()).replace(",", ".") + "\nCurrency  :     " + account.getCurrency();
        final Paragraph accountdetail = new Paragraph(accountdetails);
        accountdetail.setAlignment(0);
        accountdetail.setSpacingAfter(30.0f);
        accountdetail.setSpacingBefore(10.0f);
        document.add(accountdetail);
        
        final PdfPTable table = new PdfPTable(3);
        table.addCell("            Type");
        table.addCell("            Date");
        table.addCell("            Amount");
        final Collection<Transaction> list = account.getTransactions();
        for (final Transaction transaction : list) {
            final String type = transaction.getClass().getSimpleName().equals("PaymentTransaction") ? "payment" : "withdraw";
            table.addCell("       " + type);
            table.addCell("       " + new SimpleDateFormat("dd/MM/yyyyy @ HH:mm").format(new Date()));
            table.addCell("       " + transaction.getAmount());
        }
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=" + account.getClient().getName() + "_" + account.getReference() + "_transactions.pdf");
        document.add(table);
        document.close();
    }
    
}
