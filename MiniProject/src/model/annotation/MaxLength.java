package model.annotation;
// Purpose: Declares the maximum permitted length for an annotated string field.
import java.lang.annotation.*;
@Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME)
public @interface MaxLength { int value(); }
