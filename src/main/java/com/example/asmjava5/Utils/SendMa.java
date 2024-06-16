package com.example.asmjava5.Utils;

import java.util.Random;

public class SendMa {
    public static String MaXacNhan() {
        int leftLimit = 48; // Unicode  '0'
        int rightLimit = 122; // Unicode  'z'
        int targetStringLength = 6;//Số kí tự có trong mã
        Random random = new Random();
        String randomCode = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint , StringBuilder::append).toString();
        return randomCode;
    }
}
