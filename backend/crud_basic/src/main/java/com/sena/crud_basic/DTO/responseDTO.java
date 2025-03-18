package com.sena.crud_basic.DTO;

public class responseDTO {

    private String status;
    private String message;

    public responseDTO(String status, String message){

        this.status = status;
        this.message = message;
    }

    public responseDTO(){   
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
