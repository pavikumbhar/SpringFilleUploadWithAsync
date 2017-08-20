/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.service;

import com.pavikumbhar.javaheart.entity.UserInfo;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.util.Arrays;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author pravinkumbhar
 */
@Component(BeanDefinition.SCOPE_PROTOTYPE)
public class Upload {

    @Async
    public void process(MultipartFile file) throws InterruptedException, IOException {
        
    	System.out.println("Starting reading");
    	
    	
    	for (int i = 0; i < 100000; i++) {
			
		}
    	sleep(2000);
    	System.out.println("end of loop");
       // System.out.println(new String(readFully(file.getInputStream(), -1, false)));
    	
    	
        System.out.println("com.pavikumbhar.javaheart.service.Upload.process()" +file.getOriginalFilename());
        
           try {
                
                
                List<UserInfo> lstUser = new ArrayList<>();
                int i = 0;
                // Creates a workbook object from the uploaded excelfile
                HSSFWorkbook workbook = new HSSFWorkbook( new FileInputStream(new File("C:/Users/pavikumbhar/Desktop/"+file.getOriginalFilename())));
                // Creates a worksheet object representing the first sheet
                HSSFSheet worksheet = workbook.getSheetAt(0);
                // Reads the data in excel file until last row is encountered
                while (i <= worksheet.getLastRowNum()) {
                // Creates an object for the UserInfo Model
                UserInfo user = new UserInfo();
                // Creates an object representing a single row in excel
                HSSFRow row = worksheet.getRow(i++);
                
                if(row.getRowNum() != 0){
                	// Sets the Read data to the model class
                     user.setId((int) row.getCell(0).getNumericCellValue());
                     user.setUsername(row.getCell(1).getStringCellValue());
                     	
                }
                //user.setInputDate(row.getCell(2).getDateCellValue());
                
                row.getCell(2).getStringCellValue();
                // persist data into database in here
                lstUser.add(user);
                }
                
                for (UserInfo userInfo : lstUser) {
                    
                    System.out.println("userInfo"+userInfo);
                   
               }
                workbook.close();
              
                } catch (Exception e) {
                e.printStackTrace();
                }
                System.out.println("######################   Upload Component      ################################");
    }
    
    @Async
public void process(File file) throws InterruptedException, IOException {
        
    	System.out.println("Starting reading");
    	
    	
    	for (int i = 0; i < 100000; i++) {
    		//System.out.println(i);
    		
		}
    	TimeUnit.SECONDS.sleep(30);
    	System.out.println("end of loop>>>");
       // System.out.println(new String(readFully(file.getInputStream(), -1, false)));
    	
    	
        System.out.println("com.pavikumbhar.javaheart.service.Upload.process()");
        
           try {
                
        	   System.out.println("processeng file "+file.getName());
                List<UserInfo> lstUser = new ArrayList<>();
                int i = 0;
                String extension=FilenameUtils.getExtension(file.toString());
                
                 System.out.println("[extension] : "+extension);
    			if (extension.equalsIgnoreCase("xlsx")){
    				
    				// Creates a workbook object from the uploaded excelfile
    				XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
    				// Creates a worksheet object representing the first sheet
    				XSSFSheet worksheet = workbook.getSheetAt(0);
    				// Reads the data in excel file until last row is encountered
    				while (i <= worksheet.getLastRowNum()) {
    					// Creates an object for the UserInfo Model
    					UserInfo user = new UserInfo();
    					// Creates an object representing a single row in excel
    					XSSFRow row = worksheet.getRow(i++);
    					if(row.getRowNum() != 0){
    						// Sets the Read data to the model class
    						user.setId((int) row.getCell(0).getNumericCellValue());
    						user.setUsername(row.getCell(1).getStringCellValue());
    						//user.setInputDate(row.getCell(2).getDateCellValue());
    							 
    				 }// persist data into database in here
    					lstUser.add(user);
    					
    				}	
    				 for (UserInfo userInfo : lstUser) {
                         
                         System.out.println("userInfo"+userInfo);
                        
                    }
    				workbook.close();
    				
    			}else if (extension.equalsIgnoreCase("xls")){

                    // Creates a workbook object from the uploaded excelfile
                    HSSFWorkbook workbook = new HSSFWorkbook( new FileInputStream(file));
                    // Creates a worksheet object representing the first sheet
                    HSSFSheet worksheet = workbook.getSheetAt(0);
                    // Reads the data in excel file until last row is encountered
                    while (i <= worksheet.getLastRowNum()) {
                    // Creates an object for the UserInfo Model
                    UserInfo user = new UserInfo();
                    // Creates an object representing a single row in excel
                    HSSFRow row = worksheet.getRow(i++);
                    if(row.getRowNum() != 0){
                    	// Sets the Read data to the model class
                         user.setId((int) row.getCell(0).getNumericCellValue());
                         user.setUsername(row.getCell(1).getStringCellValue());
                         	
                    } //user.setInputDate(row.getCell(2).getDateCellValue());
                    
                    row.getCell(2).getStringCellValue();
                    // persist data into database in here
                    lstUser.add(user);
                    }
                    
                    for (UserInfo userInfo : lstUser) {
                        
                        System.out.println("userInfo"+userInfo);
                       
                   }
                    workbook.close();
                  
                  	
    			}
                  
                } catch (Exception e) {
                e.printStackTrace();
                }
    System.out.println("######################   Upload Component      ################################");
    }

    @Async
    public void process(byte[] bs) {
        System.out.println(new String(bs));
        //do some long running processing of bs here
    }

    public byte[] readFully(InputStream is, int length, boolean readAll)
            throws IOException {
        byte[] output = {};
        if (length == -1) {
            length = Integer.MAX_VALUE;
        }
        int pos = 0;
        while (pos < length) {
            int bytesToRead;
            if (pos >= output.length) { // Only expand when there's no room
                bytesToRead = Math.min(length - pos, output.length + 1024);
                if (output.length < pos + bytesToRead) {
                    output = Arrays.copyOf(output, pos + bytesToRead);
                }
            } else {
                bytesToRead = output.length - pos;
            }
            int cc = is.read(output, pos, bytesToRead);
            if (cc < 0) {
                if (readAll && length != Integer.MAX_VALUE) {
                    throw new EOFException("Detect premature EOF");
                } else {
                    if (output.length != pos) {
                        output = Arrays.copyOf(output, pos);
                    }
                    break;
                }
            }
            pos += cc;
        }
        return output;
    }
}
