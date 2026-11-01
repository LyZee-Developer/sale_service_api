package org.commerce.sale_management.schedule;

import lombok.extern.slf4j.Slf4j;
import org.commerce.sale_management.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/1/2026 10:10 AM
 */
@Slf4j
@Component
public class UserAccountSchedule {

    @Autowired
    private UserAccountService userAccountService;


    //  At every minute.
    @Scheduled(cron = "0 * * * * *")
    public void runResetAttempt() {
        log.info("Run reset Attempt");
        userAccountService.resetAttemptSchedule();
    }
}
