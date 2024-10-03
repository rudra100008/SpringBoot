package com.blogrestapi.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public interface FileService {
    String uploadFile(String path,MultipartFile file)throws IOException;
    InputStream getFile(String path,String fileName)throws FileNotFoundException;
}
