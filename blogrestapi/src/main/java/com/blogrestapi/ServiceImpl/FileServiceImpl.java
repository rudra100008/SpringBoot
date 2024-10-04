package com.blogrestapi.ServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogrestapi.Service.FileService;
@Service
public class FileServiceImpl  implements FileService{

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        String name=file.getOriginalFilename();
        String randomID=UUID.randomUUID().toString();
        @SuppressWarnings("null")
        String fileName=randomID.concat(name.substring(name.lastIndexOf(".")));
        String fullPath=path+File.separator+fileName;
        File f=new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        Files.copy(file.getInputStream(),Paths.get(fullPath),StandardCopyOption.REPLACE_EXISTING);
       return fileName;
    }

    @Override
    public InputStream getFile(String path, String fileName) throws FileNotFoundException {
        String fullPath=path+File.separator+fileName;
        InputStream is=new FileInputStream(fullPath);
        return is;
    }
    
}
