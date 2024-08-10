package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.company.helper.FIleUploadHelper;

@RestController
public class FIleUploadController {

    @Autowired
    private FIleUploadHelper fIleUploadHelper;
    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile file)throws Exception
    {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getContentType());
        try{
        if(file.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
        }
        if(!file.getContentType().equals("image/jpeg"))
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only JPEG content are allowed");
        }
        //uploadImage
       boolean istrue= fIleUploadHelper.isUploadFile(file);
       if(istrue==true){
        //return ResponseEntity.ok("File is uploaded sucessfuly");
        return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/")
        .path(file.getOriginalFilename()).toUriString());
       }
    
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }
    
}
