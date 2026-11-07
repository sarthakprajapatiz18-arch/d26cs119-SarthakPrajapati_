package model.annotation;
// Purpose: Marks a field as the identifier used by the generic repository.
import java.lang.annotation.*;
@Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME)
public @interface Id { }
