package self.made.annotation.demo.common.validation;

import lombok.extern.slf4j.Slf4j;
import self.made.annotation.demo.common.anntotation.MyNotBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class MyNotBlankValidator implements ConstraintValidator<MyNotBlank, String> {
    @Override
    public void initialize(MyNotBlank myNotBlank) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }

        return value.strip().length() > 0;
    }
}
