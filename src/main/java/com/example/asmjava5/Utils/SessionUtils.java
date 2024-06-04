package com.example.asmjava5.Utils;


import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SessionUtils {
    @Autowired
    HttpSession session;

    public void addSession(String key,Object obj) {
            session.setAttribute(key,obj);
            session.setMaxInactiveInterval(60*60*24);
    }

    public Object laySession(String key) {
        return session.getAttribute(key);
    }

    public void removeSession(String key) {
        session.removeAttribute(key);
    }
}
