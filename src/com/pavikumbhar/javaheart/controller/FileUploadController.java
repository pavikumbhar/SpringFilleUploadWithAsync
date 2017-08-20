/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.monitorjbl.xlsx.StreamingReader;
import com.pavikumbhar.javaheart.service.Upload;

/**
 *
 * @author pravinkumbhar
 */
@Controller
public class FileUploadController {

	private static final Logger LOG = LoggerFactory.getLogger(FileUploadController.class);
	@Autowired
	private Upload upload;
	
	 @Autowired
	 private AtomicInteger counter = null;

	@RequestMapping(value = "/processAsync", method = RequestMethod.POST)
	public String processExcel(Model model, @RequestParam("file") MultipartFile excelfile) {

		try {
			String filePath = "E:/";

			File fileTemp = UploadFileHelper.singleUpload(excelfile, filePath);
			System.out.println(fileTemp.getAbsolutePath());

			String ext = FilenameUtils.getExtension(fileTemp.toString());

			String fileName = fileTemp.getName();
			String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());

			System.out.println("ext" + ext);
			System.out.println("fileTemp.getName()" + fileTemp.getName());
			LOG.info("fileTemp.getName()" + fileTemp.getName());
			boolean validfileformat = false;
			
			if (ext.equalsIgnoreCase("xlsx")) {
				XSSFWorkbook workbook = new XSSFWorkbook(fileTemp.toString());
				XSSFSheet spreadsheet = workbook.getSheetAt(0);

				if (spreadsheet.getRow(0).getCell(0).toString().trim().toLowerCase().contains("ID".toLowerCase()) &&  
			    spreadsheet.getRow(0).getCell(1).toString().trim().toLowerCase().contains("Name".toLowerCase())) {
					System.out.println(" In  upload Success ");
					validfileformat=true;
				}else{
					 System.out.println(">> " + spreadsheet.getRow(0).getCell(0).toString().trim().toLowerCase().contains("ID".toLowerCase()));
	                 System.out.println(">>" + spreadsheet.getRow(0).getCell(1).toString().trim().toLowerCase().contains("Name".toLowerCase()));
	                   
					model.addAttribute("error", " FIELDS ORDER MUST BE  ID | Name ");
					model.addAttribute("css", "danger");
					System.out.println("Invalid header");
				}
			} else if (ext.equalsIgnoreCase("xls")) {
				
				HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(fileTemp.toString()));
                HSSFSheet sheet = wb.getSheetAt(0);
                
            	if (sheet.getRow(0).getCell(0).toString().trim().toLowerCase().contains("ID".toLowerCase())&& 
            		sheet.getRow(0).getCell(1).toString().trim().toLowerCase().contains("Name".toLowerCase())) {
            		validfileformat=true;
					System.out.println(" In upload Success ");
				}else{
					
					 System.out.println(" In execute for upload Success " + sheet.getRow(0).getCell(0).toString().trim().toLowerCase().contains("ID".toLowerCase()));
	                 System.out.println(">>" + sheet.getRow(0).getCell(1).toString().trim().toLowerCase().contains("Name".toLowerCase()));
	                   
	                    
					model.addAttribute("error", " FIELDS ORDER MUST BE  ID | Name ");
					model.addAttribute("css", "danger");
					System.out.println("Invalid header");
				}
			} else {model.addAttribute("error", "Wrong File is selected. i.e. file should be in xls or xlsx.");
				System.out.println("Invalid extension");
			}

			fileTemp.deleteOnExit();
			

			if(validfileformat){
				String newFileName=counter.incrementAndGet()+ "_" + fileTemp.getName();
				File fileToCreate = new File(filePath,newFileName );
				FileUtils.copyFile(fileTemp, fileToCreate);
				upload.process(fileToCreate);
				LOG.info("Thread released and response sent. Task is still running in another thread...");				
				model.addAttribute("error", "upload Success  ");
				model.addAttribute("css", "danger");
				
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("@@@@@@@@@@@@@ CONTROLLER    @@@@@@@@@@@@@@@@@@@@@@@@");
		return "fileUpload";

	}


	public static void main(String[] args) throws FileNotFoundException, IOException {
		InputStream is = new FileInputStream(new File("/home/pravinkumbhar/Desktop/WebiView - Copy.xlsx"));

		Workbook workbook = StreamingReader.builder().rowCacheSize(100) // number of rows to keep in memory (defaults t0 10)
				.bufferSize(4096) // buffer size to use when reading InputStream
									// to file (defaults to 1024)
				.open(is);

		org.apache.poi.ss.usermodel.Sheet sheetAt = workbook.getSheetAt(0);

		for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
			System.out.println(sheet.getSheetName());
			for (Row r : sheet) {
				for (Cell c : r) {
					System.out.println(c.getStringCellValue());

				}
			}
		}

	}
}
