package com.udacity.jwdnd.course1.cloudstorage.services;
import java.util.List;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public File getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public List<File> getUserFiles(Integer userId) {
        return fileMapper.findFilesForUser(userId);
    }

    public void deleteFile(Integer fileId) {
        fileMapper.deleteFile(fileId);
    }

    public boolean doesFileExist(Integer fileId,Integer userId) {
        return fileMapper.getFile(fileId) != null;
    }

    public void storeFile(File file) {
        fileMapper.insertFile(file);
    }
}
