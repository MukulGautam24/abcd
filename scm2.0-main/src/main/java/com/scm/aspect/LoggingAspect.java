package com.scm.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // @Pointcut("execution(* com.yourpackage.service.*.*(..))") // Apply to all service methods
    // public void serviceMethods() {}



    @Before("execution(* com.scm.controllers.UserController.userDashboard())")
    public void printBefore(){
        System.out.println("User Dashboard is being accessed");
    }

    @After("execution(* com.scm.controllers.UserController.userDashboard())")
    public void printAfter(){
        System.out.println("User Dashboard is accessed");
    }



    

    // @Before("serviceMethods()")
    // public void beforeMethod() {
    //     System.out.println("Before method execution...");
    // }

    // @After("serviceMethods()")
    // public void afterMethod() {
    //     System.out.println("After method execution...");
    // }
}
