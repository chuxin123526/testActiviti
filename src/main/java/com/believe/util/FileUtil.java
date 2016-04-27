package com.believe.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil
{
	public static String save(MultipartFile file) throws Exception
	{
		File fileStore = null ; 
		if(!file.isEmpty())
		{
			InputStream inputStream = file.getInputStream() ; 
			File directory = new File("F:/fileUpload") ; 
			if(!directory.exists())
			{
				directory.mkdirs() ; 
			}
			String basePath = directory.getAbsolutePath() ; 
			fileStore = new File(basePath + File.separator + file.getOriginalFilename()) ; 
			if(!fileStore.exists())
			{
				fileStore.createNewFile() ; 
			}
			OutputStream outputStream = new FileOutputStream(fileStore)  ; 
			int n = 0 ; 
			byte[] buffer = new byte[1024] ; 
			while((n = inputStream.read(buffer)) != -1)
			{
				outputStream.write(buffer, 0, n);
			}
			inputStream.close();
			outputStream.close(); 
		}
		
		return fileStore.getAbsolutePath() ; 
	}
	
	public static boolean check(MultipartFile file) 
	{
		boolean flag = false ; 
		String contentType = file.getContentType() ; 
		String doc = "application/msword" ; 
		String docx = "application/vnd.openxmlformats-officedocument.wordprocessingml.document" ; 
		String xlsx = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" ; 
		String xls = "application/vnd.ms-excel" ; 
		if(contentType.equals(docx) || contentType.equals(doc) || contentType.equals(xls) || contentType.equals(xlsx))
		{
			flag = true ; 
		}
		return flag ; 
	}
	
	
	public static void delete(String path) throws Exception
	{
		if(path != null)
		{
			File file = new File(path) ; 
			if(file.exists())
			{
				file.delete() ; 
			}
		}
	}
}
