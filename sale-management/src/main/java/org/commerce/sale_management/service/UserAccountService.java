package org.commerce.sale_management.service;

import org.commerce.sale_management.model.User;
import org.commerce.sale_management.specification.filter.UserAccountRequestFilter;
import org.commerce.sale_management.vo.userAccount.UserAccount;
import org.module.publish_service.service.BaseInternalService;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/1/2026 2:39 PM
 */
public interface UserAccountService extends BaseInternalService<User, Long, UserAccountRequestFilter> {
    User save(UserAccount.create create);
    String login(UserAccount.login req);
    String resetPassword(UserAccount.reset req);
    String updateActivateUser(String username, boolean isActivate);
    String allowResetPw(String username);
    void resetAttemptSchedule();

}
