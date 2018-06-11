package com.nghia.libraries.commons.mss.infrustructure.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.validation.*;
import java.util.Map;
import java.util.Set;

@Component
public class AbstractCustomValidator implements org.springframework.validation.Validator, InitializingBean,
        ApplicationContextAware, ConstraintValidatorFactory {

    protected final Logger LOGGER = LoggerFactory.getLogger(AbstractCustomValidator.class);
    protected ApplicationContext applicationContext;
    protected Validator validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.warn("AbstractCustomValidator.validate");
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target);
//        if (constraintViolations.size() > 0) {
//            throw new UserException(123, "Validation failded");
//        }
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
            errors.rejectValue(propertyPath, "", message);
        }
        addExtraValidation(target, errors);

    }


    @Override
    public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
        LOGGER.warn("AbstractCustomValidator.getInstance");
        Map<String, T> beansByNames = applicationContext.getBeansOfType(key);
        if (beansByNames.isEmpty()) {
            try {
                return key.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("Could not instantiate constraint validator class '" + key.getName() + "'", e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Could not instantiate constraint validator class '" + key.getName() + "'", e);
            }
        } else if (beansByNames.size() > 1) {
            throw new RuntimeException("Only one bean of type '" + key.getName() + "' is allowed in the application context");
        }
        return beansByNames.values().iterator().next();
    }

    @Override
    public void releaseInstance(ConstraintValidator<?, ?> instance) {
        LOGGER.warn("AbstractCustomValidator.releaseInstance");
    }

    @Override
    public void afterPropertiesSet() {
        LOGGER.warn("AbstractCustomValidator.afterPropertiesSet");
        ValidatorFactory validatorFactory = Validation
                .byDefaultProvider()
                .configure()
                .constraintValidatorFactory(this)
                .buildValidatorFactory();

        this.validator = validatorFactory
                .usingContext()
                .getValidator();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOGGER.warn("AbstractCustomValidator.setApplicationContext");
        this.applicationContext = applicationContext;
    }

    public void addExtraValidation(Object target, Errors errors) {
        // TODO: for override validation.
    }
}
