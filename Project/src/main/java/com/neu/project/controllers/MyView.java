package com.neu.project.controllers;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.neu.project.pojo.Order;

public class MyView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter pdfwriter, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Font  helvetica_18_black = new Font(Font.HELVETICA, 18, Font.BOLDITALIC, Color.BLACK);
        Paragraph title = new Paragraph("Order Details", helvetica_18_black);
        document.add(title);
        
        Phrase firstPhrase = new Phrase("Details of the Order");
        document.add(firstPhrase);
        
        @SuppressWarnings("unchecked")
        List<Order> list = (List<Order>)model.get("list");
        
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
        document.add(new Paragraph(""));
        document.add(new Paragraph(""));
        document.add(new Paragraph(""));
        document.add(Chunk.NEWLINE);
        
        PdfPTable table = new PdfPTable(6);
        table.setTotalWidth(500);
        table.setLockedWidth(true);
        table.setWidths(new float[]{1, 1, 1,1,1,1});
        PdfPCell cell;
        
        table.addCell(new Paragraph("Product Name",FontFactory.getFont(FontFactory.TIMES, 15,Font.BOLD)));
        table.addCell(new Paragraph("Quantity",FontFactory.getFont(FontFactory.TIMES, 15,Font.BOLD)));
        table.addCell(new Paragraph("Price",FontFactory.getFont(FontFactory.TIMES, 15,Font.BOLD)));
        table.addCell(new Paragraph("Total Price",FontFactory.getFont(FontFactory.TIMES, 15,Font.BOLD)));
        table.addCell(new Paragraph("Date",FontFactory.getFont(FontFactory.TIMES, 15,Font.BOLD)));
        table.addCell(new Paragraph("Seller Name",FontFactory.getFont(FontFactory.TIMES, 15,Font.BOLD)));
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        
        double totalprice = 0;
        for(Order order:list)
        {
        	String requiredDate = df.format(order.getDate()).toString();
            table.addCell(new Paragraph(order.getProduct().getProductName(),FontFactory.getFont(FontFactory.TIMES, 11,Font.BOLD)));
            table.addCell(String.valueOf(order.getQuantity()));
            table.addCell(String.valueOf(order.getProduct().getProductPrice()));
            table.addCell(String.valueOf(order.getQuantity() * order.getProduct().getProductPrice()));
            table.addCell(requiredDate);
            table.addCell(String.valueOf(order.getProduct().getSeller().getCompanyName()));
            totalprice = totalprice +((order.getQuantity())*order.getProduct().getProductPrice());
        }
        
        document.add(table);
        
        Paragraph totalPrice = new Paragraph("Total Price: "+totalprice,FontFactory.getFont(FontFactory.TIMES, 10,Font.UNDERLINE));
        document.add(totalPrice);
	}

}
