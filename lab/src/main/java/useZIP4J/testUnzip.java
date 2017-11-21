package useZIP4J;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;


public class testUnzip {
	private static final int buffer = 2048;  
	public static void unZip(String path, String savepath)  
    {  
     int count = -1;  

     File file = null;  
     InputStream is = null;  
     FileOutputStream fos = null;  
     BufferedOutputStream bos = null;  

     new File(savepath).mkdir(); //创建保存目录  
     ZipFile zipFile = null;  
     try  
     {  
         zipFile = new ZipFile(path,"gbk"); //解决中文乱码问题  
         Enumeration<?> entries = zipFile.getEntries();  

         while(entries.hasMoreElements())  
         {  
             byte buf[] = new byte[buffer];  

             ZipEntry entry = (ZipEntry)entries.nextElement();  

             String filename = entry.getName();  
             boolean ismkdir = false;  
             if(filename.lastIndexOf("/") != -1){ //检查此文件是否带有文件夹  
                ismkdir = true;  
             }  
             filename = savepath + filename;  

             if(entry.isDirectory()){ //如果是文件夹先创建  
                file = new File(filename);  
                file.mkdirs();  
                 continue;  
             }  
             file = new File(filename);  
             if(!file.exists()){ //如果是目录先创建  
                if(ismkdir){  
                new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs(); //目录先创建  
                }  
             }  
             file.createNewFile(); //创建文件  

             is = zipFile.getInputStream(entry);  
             fos = new FileOutputStream(file);  
             bos = new BufferedOutputStream(fos, buffer);  

             while((count = is.read(buf)) > -1)  
             {  
                 bos.write(buf, 0, count);  
             }  
             bos.flush();  
             bos.close();  
             fos.close();  

             is.close();  
         }  

         zipFile.close();  

     }catch(IOException ioe){  
         ioe.printStackTrace();  
     }finally{  
            try{  
            if(bos != null){  
                bos.close();  
            }  
            if(fos != null) {  
                fos.close();  
            }  
            if(is != null){  
                is.close();  
            }  
            if(zipFile != null){  
                zipFile.close();  
            }  
            }catch(Exception e) {  
                e.printStackTrace();  
            }  
        }  
    } 
	public static void reandGBK(String source) throws IOException{
		File file = new File(source);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
		String line;
		while((line = reader.readLine())!=null){
			System.out.println(line);
		}
	}
	
	public static void main(String args[]) throws IOException {
		String source = "/home/cc/Downloads/Excels/PMS/20171110/福建省_厦门市.zip";
		String destination = "/home/cc/Desktop";
		String append = "/"+System.nanoTime() +"/";
		unZip(source, destination + append);
		source = "/home/cc/Desktop/6533551518645/厦门市/站址.csv";
		reandGBK(source);
	}
}
