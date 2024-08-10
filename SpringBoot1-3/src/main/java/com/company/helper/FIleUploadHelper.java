package com.company.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FIleUploadHelper {
    // public final String upload_dir="E:\\Spring-Boot\\SpringBoot1-3\\src\\main\\resources\\static";
    public final String upload_dir=new ClassPathResource("static/images/").getFile().getAbsolutePath();

    public FIleUploadHelper()throws IOException{

    }

    public boolean isUploadFile(MultipartFile file){
        boolean f=false;
        try{
            //for uploading image code
            // InputStream is=file.getInputStream();
            // byte[] data=new byte[is.available()];
            // is.read(data);

            // FileOutputStream fos=new FileOutputStream(upload_dir+File.separator+file.getOriginalFilename());
            // fos.write(data);
            // fos.flush();
            // fos.close();

            //simple format for uploading code
            Files.copy(file.getInputStream(),Paths.get(upload_dir+File.separator+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);

            f=true;

        }catch(Exception e){
            System.out.println(e.getMessage());

        }

        return f;

    }
}
