package com.udacity.jwdnd.course1.cloudstorage.controller;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;
    private final AuthenticationService authenticationService;
    private final UserService users;
    public FileController(FileService fileService, AuthenticationService authenticationService, UserService users) {
        this.fileService = fileService;
        this.authenticationService = authenticationService;
        this.users = users;
    }
    @PostMapping("/UploadFile")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Model model,Authentication  authentication) {
        //Authenticated User
        Integer userId = users.getUser(authentication.getName()).getUserId();

        //Getting File Info
        String fileName = fileUpload.getOriginalFilename();
        String contentType = fileUpload.getContentType();
        long fileSize = fileUpload.getSize();
        //FileList array
        try {
            //if no file Uploaded
            if (fileUpload.isEmpty()){
                model.addAttribute("success",false);
                return "result";
            }
            byte[] fileData = fileUpload.getBytes();
            //Creating the new file and Uploading
            File file = new File( fileName,fileSize, contentType, fileData, userId);
            fileService.storeFile(file);
            List<File> Files = fileService.getUserFiles(file.getUserId());
            //Adding Attributes to our Model
            model.addAttribute("Files", Files);
            model.addAttribute("success",true);

            if(fileService.doesFileExist(file.getFileId(), file.getUserId())){
                model.addAttribute("success",false);
                return "result";
            }
            return "redirect:/result";
        } catch (IOException e) {
            model.addAttribute("success",false);
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/DownloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId, Authentication authentication,Model model) {
        int userId = users.getUser(authentication.getName()).getUserId();
        File file = fileService.getFile(fileId);

        if (file != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .header("Content-Disposition", "attachment; filename=" + file.getFileName())
                    .body(new ByteArrayResource(file.getFileData()));
        }
        model.addAttribute("success",true);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/DeleteFile/{fileId}")
    public String removeFile(@PathVariable Integer fileId, Authentication authentication, Model model
    ) {
        int userId = users.getUser(authentication.getName()).getUserId();
        File file = fileService.getFile(fileId);
        fileService.deleteFile(fileId);
        model.addAttribute("file",file);
        model.addAttribute("success",true);
        return "result";
    }

}