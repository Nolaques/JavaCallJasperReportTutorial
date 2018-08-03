package com.company;

import net.sf.jasperreports.engine.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PdfFromXmlFile {

    public static void main(String[] args) throws JRException, IOException {
        Logger.getRootLogger().setLevel(Level.OFF);
        // Compile jrxml file.
        JasperReport jasperReport = JasperCompileManager
                .compileReport("C:/java/jasperoutput/StyledTextReport.jrxml");

        // Parameters for report
        Map<String, Object> parameters = new HashMap<String, Object>();

        // DataSource
        // This is simple example, no database.
        // then using empty datasource.
        JRDataSource dataSource = new JREmptyDataSource();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                parameters, dataSource);


        // Make sure the output directory exists.
        File outDir = new File("C:/java/jasperoutput");
        outDir.mkdirs();

        // Export to PDF.
        JasperExportManager.exportReportToPdfFile(jasperPrint,
                "C:/java/jasperoutput/StyledTextReport.pdf");

        System.out.println("Done!");
    }
}
