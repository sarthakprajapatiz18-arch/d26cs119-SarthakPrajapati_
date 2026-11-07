package model.annotation;
// Purpose: Declares that an annotated numeric field must not be negative.
import java.lang.annotation.*;
@Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME)
public @interface Positive { String message() default "must be > 0"; }
