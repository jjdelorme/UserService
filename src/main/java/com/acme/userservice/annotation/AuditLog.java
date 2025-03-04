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

package com.acme.userservice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditLog {
    String description() default "";
}