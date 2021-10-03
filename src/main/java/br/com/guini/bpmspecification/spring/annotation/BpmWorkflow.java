package br.com.guini.bpmspecification.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BpmWorkflow {

    String name() default  "";

    BpmOperation[] value() default {};

    BpmConditionOperation[] satisfiedCondition() default {};

    BpmConditionOperation[] unsatisfiedCondition() default {};
}
