package com.example.asmjava5.Service;

public interface MailService {
    void sendMail(String type,String to);

    void sendMailCode(String type,String to,String code);

}
