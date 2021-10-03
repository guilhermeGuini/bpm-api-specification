package br.com.guini.bpmspecification.spring.annotation;

import br.com.guini.bpmspecification.spring.model.enums.BpmOperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BpmOperation {

    String name() default "";

    BpmOperationType type() default BpmOperationType.PROCESSING;

}
