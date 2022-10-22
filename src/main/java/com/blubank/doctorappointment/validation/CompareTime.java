package com.blubank.doctorappointment.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CompareTimeValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface CompareTime {
    String message() default "{com.blubank.doctorappointment.validation.CompareTime.message}";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};

    String before();
    String after();
}
