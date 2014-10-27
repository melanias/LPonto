package br.com.lponto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import br.com.lponto.validation.impl.FuncionarioValidator;

/**
 *
 * @author Phelipe Melanias
 */
@Documented
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy=FuncionarioValidator.class)
public @interface FuncionarioValidation {

    String message() default "{br.com.lponto.validation.FuncionarioValidation.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}