package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {

    private Integer fileId;
    private String fileName;
    private byte[] fileData;
    private long fileSize;
    private Integer userId;
    private String contentType;

    public File() {}

    public File(Integer fileId, Integer userId) {
        this.fileId = fileId;
        this.userId = userId;
    }

    public File(String fileName, long fileSize, String contentType, byte[] fileData, Integer userId) {
        this.fileName = fileName;
        this.fileData = fileData;
        this.fileSize = fileSize;
        this.userId = userId;
        this.contentType = contentType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
