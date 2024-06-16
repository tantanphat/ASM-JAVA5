package com.example.asmjava5.Service;

import java.io.File;

public interface MailService {
    void sendMail(String type,String to);

    void sendMailCode(String type,String to,String code);

    void sendMailFile(String type, String to, byte[] hoaDon);

}
