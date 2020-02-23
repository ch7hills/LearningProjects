package com.report.samplereport.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.report.samplereport.models.DataModel;
import com.report.samplereport.service.DataService;
import com.report.samplereport.service.ReportService;

@RestController
public class ReportController {
	@Value("${destinationfilelocation}")
	String destinationFileLocation;
	@Autowired
	ReportService reportService;
	@Autowired
	DataService dataService;
	
	@GetMapping("/excelSync")
	public List<DataModel> dataSync() throws IOException {
		return reportService.syncReportDataToDB();
	}
	
	@GetMapping("/caliculateCharges")
	public List<DataModel> getReport(){
		return dataService.caliculateProcessingCharges();
	}
	
	@GetMapping(value="/genarateReport",produces="application/vnd.ms-excel")
	public ResponseEntity<Resource> generateReport(HttpServletRequest request) throws Exception{
		reportService.generateReport();
		FileInputStream file = new FileInputStream(new File(destinationFileLocation));
		
        Resource resource = reportService.loadFileAsResource(destinationFileLocation);

        // Try to determine file's content type
        String contentType = null;
  
        contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        
        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}

}
