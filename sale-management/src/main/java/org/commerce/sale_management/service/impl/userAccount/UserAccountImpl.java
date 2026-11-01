package org.commerce.sale_management.service.impl.userAccount;

import lombok.Getter;
import lombok.Setter;
import org.commerce.sale_management.model.Employee;
import org.commerce.sale_management.model.User;
import org.commerce.sale_management.repository.UserRepository;
import org.commerce.sale_management.service.EmployeeService;
import org.commerce.sale_management.service.UserAccountService;
import org.commerce.sale_management.specification.UserAccountSpecification;
import org.commerce.sale_management.specification.filter.UserAccountRequestFilter;
import org.commerce.sale_management.util.PasswordUtil;
import org.commerce.sale_management.vo.userAccount.UserAccount;
import org.module.publish_service.constant.App;
import org.module.publish_service.exception.ApiResponseHandler;
import org.module.publish_service.exception.ConflictException;
import org.module.publish_service.exception.ResourceNotFoundException;
import org.module.publish_service.service.impl.BaseInternalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/31/2026 10:22 PM
 */
@Service
@ConfigurationProperties(prefix = "user.account")
public class UserAccountImpl extends BaseInternalServiceImpl<User, Long, UserRepository, UserAccountRequestFilter, UserAccountSpecification> implements UserAccountService {

    @Getter
    @Setter
    private Long duration;

    @Autowired
    private PasswordUtil passwordUtil;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public User save(UserAccount.create req) {
        checkExistsUsername(req, Boolean.TRUE);
        Employee emp = validateOrGetEmployee(req);
        User user = saveInfo(req, emp);
        return this.save(user);
    }

    @Override
    public String login(UserAccount.login req) {
        User user = findUserOrThrow(req.getUsername());
        checkAttempt(user);

        String hash = user.getPasswordHash();
        boolean notMatch = !passwordUtil.isMatch(req.getPassword(), hash);
        if (notMatch) {
            updateAttemptOrWarning(user);
        }
        user.setAttempt(0);
        update(user);
        return "Login success.";
    }

    @Override
    public String resetPassword(UserAccount.reset request) {
        User user = findUserOrThrow(request.getUsername());
        validAllowResetPassword(user);

        String hashPw = passwordUtil.encryptPassword(request.getPassword());
        user.setPasswordHash(hashPw);
        user.setIsAllowResetPw(Boolean.FALSE);
        update(user);
        return "Reset password successfully.";
    }

    private void validAllowResetPassword(User user) {
        boolean notAllow = !user.getIsAllowResetPw();
        if (notAllow) {
            throw new ApiResponseHandler("Your user account not allow to reset password yet. Please contact to admin to enable.");
        }
    }

    @Override
    public String allowResetPw(String username) {
        User user = findUserOrThrow(username);
        user.setIsAllowResetPw(Boolean.TRUE);
        update(user);
        return "Allow user [%s] reset password.".formatted(username);
    }

    private User findUserOrThrow(String username) {
        return repository.findByUsernameAndIsActivateTrue(username).orElseThrow(() -> new ResourceNotFoundException("username: [%s] was not found.".formatted(username)));
    }

    private void updateAttemptOrWarning(User user) {
        user.setAttempt(user.getAttempt() + 1);
        update(user);
        int countAttempt = (3 - user.getAttempt());
        throw new ApiResponseHandler(countAttempt == 0 ? "Your account have locked." : "User account incorrect, you have %d try left.".formatted(countAttempt));
    }

    private void checkAttempt(User user) {
        if (user.getAttempt() == 3) {
            if (user.getResetAt() == null) {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime add10Min = now.plusMinutes(duration);
                user.setResetAt(add10Min);
                update(user);
            }
            throw new ApiResponseHandler("Your user account have been locked for %d minute.".formatted(duration));
        }
    }

    private User saveInfo(UserAccount.create req, Employee employee) {
        String hashPw = passwordUtil.encryptPassword(req.getPassword());
        return User.builder().username(req.getUsername()).isAllowResetPw(Boolean.FALSE).passwordHash(hashPw).employee(employee).build();
    }

    private void checkExistsUsername(UserAccount.create req, boolean isCreate) {
        Optional<User> user = repository.findByUsernameAndIsActivateTrue(req.getUsername());
        if (user.isPresent() && isCreate) {
            throw new ConflictException("Username already existed.");
        }
    }

    private Employee validateOrGetEmployee(UserAccount.create req) {
        Assert.notNull(req.getEmployeeId(), "Employee of this user account is required.");
        User user = repository.findByEmployeeId(req.getEmployeeId()).orElse(null);
        if (user != null) {
            Employee employee = user.getEmployee();
            if (Objects.equals(employee.getId(), req.getEmployeeId())) {
                throw new ConflictException("The employee have user account already.");
            }
        }
        return employeeService.getOneOrThrow(req.getEmployeeId());
    }

    @Override
    public String updateActivateUser(String username, boolean isActivate) {
        User user = repository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("username: [%s] was not found.".formatted(username)));
        user.setIsActivate(isActivate);
        update(user);
        return "%s success.".formatted(isActivate ? "Enabled" : "Disabled");
    }

    @Override
    public void resetAttemptSchedule() {
        List<User> users = repository.findAllByAttempt(3L).orElse(Collections.emptyList());
        if (users.isEmpty()) {
            return;
        }

        /**
         * Reset attempt to allow user can log in
         */
        users.stream().filter(user -> Objects.nonNull(user.getResetAt())).forEach(user -> {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime resetAt = user.getResetAt();

            Duration duration = Duration.between(now, resetAt);
            if (duration.toMinutes() <= 0) {
                user.setAttempt(0);
                user.setResetAt(null);
            }
        });
        update(users);
    }

    @Override
    public String currentUser() {
        return App.USER;
    }
}
