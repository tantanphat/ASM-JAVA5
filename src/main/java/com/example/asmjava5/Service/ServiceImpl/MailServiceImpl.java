package com.example.asmjava5.Service.ServiceImpl;


import com.example.asmjava5.Constant.MailConstant;
import com.example.asmjava5.Service.MailService;
import com.example.asmjava5.Utils.SendMa;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void sendMail(String type, String to) {
        MimeMessage message = javaMailSender.createMimeMessage();
        String ma = SendMa.MaXacNhan();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(username);
            helper.setTo(to);

            String title = null;
            String content = null;
            switch (type) {
                case MailConstant.KEY_MAIL_SIGNIN_WELCOME:
                    title = "Đăng ký thành công";
                    content = "<div style=' background-color: #d4edda;\n" +
                            "    color: #155724;\n" +
                            "    border: 1px solid #c3e6cb;\n" +
                            "    border-radius: 5px;\n" +
                            "    padding: 20px;\n" +
                            "    margin-bottom: 20px;'>"
                            + "<h1> <strong>Chúc mừng " + to + " đã đăng ký thành công</strong> </h1>"
                            + "</div>";
                    break;

                case MailConstant.CODE_FORGOT_PASSWORD:
                    title = "Mã code quên mật khẩu";
                    content = "Đây là mã code xác nhận của ban: " +ma;
                    break;
                default:
                    title = "FPT";
                    content = "Xin lỗi vì đã làm phiền";
            }
            helper.setSubject(title);
            helper.setText(content,true);


            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Could not send email");
        }
    }

    @Override
    public void sendMailCode(String type, String to, String code) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(username);
            helper.setTo(to);

            String title = null;
            String content = null;
            switch (type) {
                case MailConstant.CODE_FORGOT_PASSWORD:
                    title = "Mã code quên mật khẩu";
                    content = "Đây là mã code xác nhận của ban: " +code;
                    break;
                default:
                    title = "FPT";
                    content = "Xin lỗi vì đã làm phiền";
            }
            helper.setSubject(title);
            helper.setText(content,true);


            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Could not send email");
        }
    }

    @Override
    public void sendMailFile(String type, String to, byte[] fileBytes) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(username);
            helper.setTo(to);


            String title = "Thanh toán thành công";
            String content = "<div style=' background-color: #d4edda;\n" +
                    "    color: #155724;\n" +
                    "    border: 1px solid #c3e6cb;\n" +
                    "    border-radius: 5px;\n" +
                    "    padding: 20px;\n" +
                    "    margin-bottom: 20px;'>"
                    + "<h1> <strong>Cảm ơn " + to + " đã mua hàng</strong></h1>"
                    + "</div>";

            helper.addAttachment("hoa-don.pdf", new ByteArrayResource(fileBytes));
            helper.setSubject(title);
            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Could not send email");
        }
    }
}
