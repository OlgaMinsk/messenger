package com.innowisegroup.messenger.dto.response;

public class AvatarResponse {
    private String contentType;
    private String fileName;
    private byte[] content;

    public AvatarResponse() {
    }

    public AvatarResponse(String contentType, String fileName, byte[] content) {
        this.contentType = contentType;
        this.fileName = fileName;
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

}
