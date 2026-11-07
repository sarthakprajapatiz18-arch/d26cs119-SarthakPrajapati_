package service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import model.annotation.Id;

/**
 * A small in-memory generic repository.
 * The @Id field may be declared in a parent class, so the class hierarchy
 * is inspected when an entity is stored.
 */
public class Repository<T> {
    private final Map<String, T> data = new LinkedHashMap<>();

    /** Stores an entity using the value of its field annotated with @Id. */
    public T save(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        data.put(readId(entity), entity);
        return entity;
    }

    /** Finds an entity by its identifier, or returns null when absent. */
    public T findById(String id) {
        return data.get(id);
    }

    /** Returns a snapshot of all stored entities in insertion order. */
    public ArrayList<T> findAll() {
        return new ArrayList<>(data.values());
    }

    /** Removes an entity and reports whether one was present. */
    public boolean delete(String id) {
        return data.remove(id) != null;
    }

    /** Saves each supplied entity. */
    @SafeVarargs
    public final void saveAll(T... items) {
        for (T item : items) {
            save(item);
        }
    }

    /**
     * Reads the @Id field from the concrete class or any superclass.
     * This supports inherited identifiers such as Account.accountNumber.
     */
    private String readId(T entity) {
        Class<?> current = entity.getClass();

        while (current != null) {
            for (Field field : current.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    try {
                        field.setAccessible(true);
                        Object value = field.get(entity);
                        if (value == null || String.valueOf(value).isBlank()) {
                            throw new IllegalArgumentException("@Id field cannot be null or blank");
                        }
                        return String.valueOf(value);
                    } catch (IllegalAccessException e) {
                        throw new IllegalArgumentException("Cannot read @Id field", e);
                    }
                }
            }
            current = current.getSuperclass();
        }

        throw new IllegalArgumentException(
            "No @Id field found in " + entity.getClass().getSimpleName()
        );
    }

    /** Calculates the arithmetic mean of a numeric array. */
    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) {
            return 0.0;
        }

        double sum = 0;
        for (N value : values) {
            if (value == null) {
                throw new IllegalArgumentException("Array values cannot be null");
            }
            sum += value.doubleValue();
        }
        return sum / values.length;
    }
}
