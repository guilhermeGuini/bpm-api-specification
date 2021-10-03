package br.com.guini.bpmspecification.spring.annotation;

import br.com.guini.bpmspecification.spring.BpmApiSpecificationSpringConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = {java.lang.annotation.ElementType.TYPE})
@Import(BpmApiSpecificationSpringConfiguration.class)
public @interface EnableBpmApi {
}
