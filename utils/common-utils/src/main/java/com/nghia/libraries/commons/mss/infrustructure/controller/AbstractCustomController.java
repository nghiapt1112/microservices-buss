package com.nghia.libraries.commons.mss.infrustructure.controller;

import com.nghia.libraries.commons.mss.infrustructure.domain.AbstractObject;
import com.nghia.libraries.commons.mss.infrustructure.exception.DomainException;
import com.nghia.libraries.commons.mss.infrustructure.validator.AbstractCustomValidator;
import com.nghia.libraries.commons.mss.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;


// Target all Controllers annotated with @RestController
//@ControllerAdvice(annotations = {RestController.class})

// Target all Controllers within specific packages
@ControllerAdvice("com.nghia.tut.mss.controller")


// Target all Controllers assignable to specific classes
//@ControllerAdvice(assignableTypes = {UserController.class})
public class AbstractCustomController {

    protected static final Logger CONTROLLER_LOGGER = LoggerFactory.getLogger(AbstractCustomController.class);

    @Autowired
    Environment env;


    @Autowired
    private AbstractCustomValidator abstractCustomValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof AbstractObject) {
            CONTROLLER_LOGGER.info("\n\n****InitBinder added customValidator****\n\n");
            binder.addValidators(abstractCustomValidator);
        }

//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(PATTERN);
//        simpleDateFormat.setLenient(false);
//        binder.registerCustomEditor( Date.class, new CustomDateEditor( simpleDateFormat,false));
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(PATTERN), true));

    }


    @ExceptionHandler
    public ResponseEntity<String> handle(DomainException ex) {
        CONTROLLER_LOGGER.info("exception: exCode {}, exMessage {}", ex.getCode(), ex.getErrorResponse());
        return ResponseEntity.status(500).body(JsonUtils.toJson(ex.getErrorResponse()));
    }
}
