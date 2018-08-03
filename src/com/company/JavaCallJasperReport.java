package com.company;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ExporterFilter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.*;

import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JavaCallJasperReport {

    public static void main(String[] args) throws JRException,
    ClassNotFoundException, SQLException{

        String reportSrcFile = "C:/Users/belyak/JaspersoftWorkspace/HelloJasperReport/FirstJasperReportNoImage.jrxml";

        //First, compile jrxml file.
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

        Connection conn = ConnectionUtils.getConnection();

        // Parameters for report
        Map<String, Object> parameters = new HashMap<>();

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

        // Make sure the output directory exists.
        File outDir = new File("C:/java/jasperoutput");
        outDir.mkdirs();

        // PDF Exporter.
        JRPdfExporter exporter = new JRPdfExporter();
        ExporterInput exporterInput = new SimpleExporterInput(print);
        // ExporterInput
        exporter.setExporterInput(exporterInput);

        // ExporterOutput
        OutputStreamExporterOutput exporterOutput =
                new SimpleOutputStreamExporterOutput("C:/java/jasperoutput/FirstJasperReport.pdf");
        // Output
        exporter.setExporterOutput(exporterOutput);

        //
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();

        System.out.println("Done!");
    }

}
