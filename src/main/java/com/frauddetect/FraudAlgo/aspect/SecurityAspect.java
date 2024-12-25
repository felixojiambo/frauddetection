package com.frauddetect.FraudAlgo.aspect;
import com.frauddetect.FraudAlgo.dto.UserDTO;
import com.frauddetect.FraudAlgo.exception.UnauthorizedException;
import com.frauddetect.FraudAlgo.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Autowired
    private UserService userService;

    // Pointcut for processing transactions
    @Pointcut("execution(* com.frauddetect.FraudAlgo.service.TransactionService.processTransaction(..))")
    public void transactionProcessing() {}

    // Before advice to check authorization
    @Before("transactionProcessing() && args(transactionDTO)")
    public void checkAuthorization(JoinPoint joinPoint, Object transactionDTO) {

        UserDTO user = userService.getUser(1L); // Fetch user with ID 1
        if (!"ADMIN".equals(user.getRole())) {
            throw new UnauthorizedException("Unauthorized: User does not have permission to process transactions");
        }
        System.out.println("Authorization successful for user: " + user.getUsername());
    }
}
