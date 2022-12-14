package com.habngroup.springboot_kaddem.utils;

import com.habngroup.springboot_kaddem.entities.Departement;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import jdk.dynalink.beans.StaticClass;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ExportpdfService {



    public static ByteArrayInputStream departementsPDFReport(String Title,String tablename,List<?> departements, String getter){
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document,out);
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER,14, BaseColor.BLACK);
            Paragraph para = new Paragraph(Title,font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);
            PdfPTable table = new PdfPTable(1);
            Stream.of(tablename+":").forEach(headerdepartements -> {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(1);
                header.setPhrase(new  Phrase(headerdepartements,headFont));
                table.addCell(header);

            });


            for(Object dept : departements) {

                String testt = null;
                try {
                    Method m= dept.getClass().getDeclaredMethod(getter);
                    testt=(String)m.invoke(dept);
                } catch (Exception exception) {
                    throw new RuntimeException(exception);
                }

                PdfPCell nomDepartCell = new PdfPCell(new Phrase(testt));
                System.out.println(nomDepartCell);
                nomDepartCell.setPaddingLeft(1);
                nomDepartCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                nomDepartCell.setVerticalAlignment(Element.ALIGN_LEFT);
                table.addCell(String.valueOf(testt));

            }

            document.add(table);
            document.close();

        }catch (DocumentException ex) {

            ex.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
