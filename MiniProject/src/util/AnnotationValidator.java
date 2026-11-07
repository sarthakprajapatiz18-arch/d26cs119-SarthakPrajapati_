package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import model.annotation.Positive;
import model.annotation.MaxLength;

/** Validates fields carrying the project's custom validation annotations. */
public class AnnotationValidator {

    /** Returns validation error messages for annotated fields in the class hierarchy. */
    public static String[] validate(Object object) {
        ArrayList<String> errors = new ArrayList<>();
        if (object == null) {
            return new String[] {"Object cannot be null"};
        }

        // Walk through superclasses so inherited annotated fields are validated too.
        Class<?> current = object.getClass();
        while (current != null) {
            for (Field field : current.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(object);

                    if (field.isAnnotationPresent(Positive.class)
                            && value instanceof Number number
                            && number.longValue() < 0) {
                        errors.add(field.getName() + ": "
                            + field.getAnnotation(Positive.class).message());
                    }

                    if (field.isAnnotationPresent(MaxLength.class)
                            && value instanceof String text
                            && text.length() > field.getAnnotation(MaxLength.class).value()) {
                        errors.add(field.getName() + ": maximum length is "
                            + field.getAnnotation(MaxLength.class).value());
                    }
                } catch (IllegalAccessException e) {
                    errors.add("Could not validate " + field.getName());
                }
            }
            current = current.getSuperclass();
        }

        return errors.toArray(new String[0]);
    }
}
