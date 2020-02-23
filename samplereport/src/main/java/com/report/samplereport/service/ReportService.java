package com.report.samplereport.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.report.samplereport.jdbc.Models.JdbcDAO;
import com.report.samplereport.models.DataModel;
import com.report.samplereport.repository.DataModelRepository;

@Service
public class ReportService {
	private static Logger log = LoggerFactory.getLogger(ReportService.class);
	@Value("${fileLocation}")
	String fileLocation;
	@Value("${destinationfilelocation}")
	String destinationFileLocation;
	@Autowired
	DataModelRepository dataRepository;
	@Autowired
	JdbcDAO dao;
	
	public List<DataModel> syncReportDataToDB() {
		try {
			FileInputStream file = new FileInputStream(new File(fileLocation));
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			 
			Map<Integer, List<String>> data = new HashMap<>();
			int i = 0;
			for (Row row : sheet) {
				DataModel record = new DataModel();
				if (row.getRowNum()==0) {
					continue;
				}
				int  rowcells = row.getPhysicalNumberOfCells();
				for(int j=0;j<rowcells;j++) {
					if(j==0) {
						record.setExternalTransactionId(row.getCell(j).getStringCellValue().trim());
					}else if(j==1) {
							record.setClientId(row.getCell(j).getStringCellValue().trim());
					}else if(j==2) {
						record.setSecurityId(row.getCell(j).getStringCellValue().trim());
					}else if(j==3) {
						record.setTransactionType(row.getCell(j).getStringCellValue().trim());
					}else if(j==4) {
						record.setTransactionDate(row.getCell(j).toString().trim());
					}else if(j==5) {
						record.setMarketValue(row.getCell(j).getNumericCellValue());
					}else if(j==6) {
						record.setPriorityFlag(row.getCell(j).getStringCellValue().trim());
					}
					
				}
				dataRepository.save(record);
			    data.put(i, new ArrayList<String>());
			    i++;
			}
			workbook.close();
			return dataRepository.findAll();
		}catch(Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}
	
	public void generateReport() throws IOException {
		List<Map<String, Object>>  result = dao.getReportData();
		FileOutputStream fos = new FileOutputStream(destinationFileLocation);
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("FinalReport");
		int i=0;
		Row row0 = sheet.createRow(i); 
		Cell cell0 = row0.createCell(0);
		cell0.setCellValue("Client Id");
		Cell cell1 = row0.createCell(1);
		cell1.setCellValue("Transaction Type");
		Cell cell2 = row0.createCell(2);
		cell2.setCellValue("Transaction Date");
		Cell cell3 = row0.createCell(3);
		cell3.setCellValue("Priority");
		Cell cell4 = row0.createCell(4);
		cell4.setCellValue("Processing Fee");
		i++;
		for(Map<String, Object> record:result) {
			Row row = sheet.createRow(i); 
			int j=0;
			for(String key: record.keySet()) {
				Cell cell = row.createCell(j);
				cell.setCellValue(record.get(key).toString());
				j++;
			}
			i++;
		}
		workbook.write(fos);
        fos.flush();
        fos.close();
	}
	
	public Resource loadFileAsResource(String fileName) throws Exception {
        try {
        	Path filePath = Paths.get(destinationFileLocation).toAbsolutePath().normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new Exception("File not found " + fileName);
            }
        } catch (Exception ex) {
            throw new Exception("File not found " + fileName, ex);
        }
    }
}
