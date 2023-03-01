package self.made.annotation.demo.common.anntotation;

import self.made.annotation.demo.common.validation.MyNotBlankValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {MyNotBlankValidator.class})
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface MyNotBlank {
    String message() default "空欄です（全角含む）";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
