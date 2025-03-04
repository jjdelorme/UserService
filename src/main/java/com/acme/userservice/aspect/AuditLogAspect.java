/*
 * Copyright © 2025 ACME Corp.
 *
 * This work is protected by copyright law and international treaties.
 * Unauthorized reproduction or distribution of this work, or any portion
 * thereof, may result in severe civil and criminal penalties, and will be
 * prosecuted to the maximum extent possible under the law.
 *
 * ACME Corp. Confidential. All Rights Reserved.
 *
 * This software contains the proprietary and confidential information of
 * ACME Corp., and may not be copied, reproduced, or distributed in any
 * form without the express written permission of ACME Corp.
 */

package com.acme.userservice.aspect;

import com.acme.userservice.annotation.AuditLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class AuditLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(AuditLogAspect.class);

    @Pointcut("@annotation(com.acme.userservice.annotation.AuditLog)")
    public void auditLogPointcut() {
        // This is an empty method. The pointcut is defined in the annotation.
    }

    @AfterReturning(pointcut = "auditLogPointcut()", returning = "result")
    public void logAuditTrail(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        String description = "";
        AuditLog annotation = method.getAnnotation(AuditLog.class);
        if (annotation != null) {
            description = annotation.description();
        }
        logger.info("Method {} executed with result: {}. Description: {}", methodName, result, description);
        //Here you would typically add more detailed logging, like user ID, timestamp etc.
    }
}
