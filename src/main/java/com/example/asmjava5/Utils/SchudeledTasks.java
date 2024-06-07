package com.example.asmjava5.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class SchudeledTasks {
    private static final Logger logger = LoggerFactory.getLogger(SchudeledTasks.class);


    @Scheduled(fixedRate = 1000,initialDelay = 5000)//Sau khi khởi động 5 giây sau thì hàm mới chạy
    public void schediledFixedRateDelay() {
        logger.info("scheduledFixedRateDelay");
    }

//    @Scheduled(fixedDelay = 10000)
//    public void scheduleTaskWithFixedDelay() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        logger.info("scheduleTaskWithFixedDelay");
//    }

//@Scheduled(cron = "15 * * * * ?")
//public void scheduleTaskWithCronExpression() {
//    logger.info("Đúng giây thứ 15 mới chạy");
//}

}
