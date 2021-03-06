package com.pavikumbhar.javaheart.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
 
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.web.multipart.MultipartFile;
 
public class UploadFileHelper {
 
     
public static  File singleUpload(MultipartFile file, HttpServletRequest request, boolean encrypt_file_name, String Upload_folder)
{
 
    String filename = null;
    File serverFile=null;
    try{
         
        if(!file.isEmpty())
             
        {
            String applicationPath=request.getServletContext().getRealPath("");
            if(encrypt_file_name){
                String currentFileName = file.getOriginalFilename();
                String extention= currentFileName.substring(currentFileName.lastIndexOf("."), currentFileName.length());
                Long nameRadom = Calendar.getInstance().getTimeInMillis();
                String newfilename= nameRadom + extention;
                filename=newfilename;
            }else
                filename=file.getOriginalFilename();
            byte[] bytes=file.getBytes();
            String rootPath=applicationPath;
            File dir=new File(rootPath + File.separator + Upload_folder);
            if(!dir.exists())
                dir.mkdirs();
            serverFile=new File(dir.getAbsolutePath() + File.separator + filename);
            BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return serverFile;
        }
        else {
            serverFile=null;
        }
    }
        catch (Exception e) {
        serverFile=null;
         
        }
        return serverFile;
    }
 
     
     
    public  List<String> MultipleFileUpload(List<MultipartFile> files, HttpServletRequest request, boolean encrypt_file_name, String Upload_folder)
    {
        List<String> filenames=new ArrayList<>();
        try
        {
            if(files.size() !=0)
            {
                BufferedOutputStream stream = null;
                String applicationPath = request.getServletContext().getRealPath("");
                for(MultipartFile file:files)
                {
                    String filename="";
                    if(encrypt_file_name) {
                        String currentFileName = file.getOriginalFilename();
                        String extention= currentFileName.substring(currentFileName.lastIndexOf("."), currentFileName.length());
                        Long nameRadom = Calendar.getInstance().getTimeInMillis();
                        String newfilename= nameRadom + extention;
                        filename=newfilename;
                    }else
                        filename=file.getOriginalFilename();
                    byte[] bytes=file.getBytes();
                     
                     
                    String rootPath=applicationPath;
                    File dir=new File(rootPath + File.separator + Upload_folder);
                    if(!dir.exists())
                        dir.mkdirs();
                    File serverFile=new File(dir.getAbsolutePath() + File.separator + filename);
                    stream=new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(bytes);
                    System.out.println(filename);
                    filenames.add(filename);
                }
                stream.close();
            }else
            {
                filenames=null;
        }
        }
        catch(Exception e)
         
        {
            System.out.println(e.getMessage());
            filenames =null;
        }
        return filenames;
             
    }
    
    
    public static  File singleUpload(MultipartFile file,  String uploadFolder)
    {
     
        String filename = null;
        File serverFile=null;
        try{
             
            if(!file.isEmpty())
                 
            {
               filename=file.getOriginalFilename();
                byte[] bytes=file.getBytes();
                File dir=new File( uploadFolder);
                if(!dir.exists())
                    dir.mkdirs();
                serverFile=new File(dir.getAbsolutePath() + File.separator + filename);
                BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                return serverFile;
            }
            else {
                serverFile=null;
            }
        }
            catch (Exception e) {
            serverFile=null;
             
            }
            return serverFile;
        }
    }