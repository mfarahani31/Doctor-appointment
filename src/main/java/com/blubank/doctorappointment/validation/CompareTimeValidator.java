package com.blubank.doctorappointment.validation;

import com.blubank.doctorappointment.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CompareTimeValidator implements ConstraintValidator<CompareTime, Object> {
    private String beforeFieldName;
    private String afterFieldName;

    @Override
    public void initialize(final CompareTime constraintAnnotation) {
        beforeFieldName = constraintAnnotation.before();
        afterFieldName = constraintAnnotation.after();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            final Field beforeDateField = value.getClass().getDeclaredField(beforeFieldName);
            beforeDateField.setAccessible(true);

            final Field afterDateField = value.getClass().getDeclaredField(afterFieldName);
            afterDateField.setAccessible(true);

            final Date beforeDate = (Date) beforeDateField.get(value);
            final Date afterDate = (Date) afterDateField.get(value);

            return beforeDate.before(afterDate);
        } catch (final Exception e) {
            e.printStackTrace();

            return false;
        }
    }
}
