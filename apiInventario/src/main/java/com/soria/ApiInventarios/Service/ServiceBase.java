package com.soria.ApiInventarios.Service;
import java.lang.reflect.Field;

public class ServiceBase {

    protected  <T> T getValue(Object obj, String prop) {
        if (obj == null || prop == null || prop.isEmpty()) {
            return null; // Evita NullPointerException
        }

        try {
            Field field = obj.getClass().getDeclaredField(prop);
            field.setAccessible(true); // Permite acceso a campos privados
            return (T) field.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace(); // Manejo de errores (puedes cambiarlo a logs)
            return null;
        }
    }

    protected  <T> T getValue2(Object obj, String prop, T defaultValue) {
        if (obj == null || prop == null || prop.isEmpty()) {
            return defaultValue; // Retorna el valor por defecto si hay parámetros inválidos
        }

        try {
            Field[] f = obj.getClass().getFields();
            Field field = obj.getClass().getDeclaredField(prop);
            field.setAccessible(true); // Permite acceso a campos privados
            return (T) field.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return defaultValue; // Retorna el valor por defecto si la propiedad no existe
        }
    }

    protected <T> T getValue3(Object obj, String prop, T defaultValue) {
        if (obj == null || prop == null || prop.isEmpty()) {
            System.out.println("⚠️ Error: Objeto o propiedad nulos o vacíos.");
            return defaultValue;
        }

        try {
            Field field = getFieldRecursive(obj.getClass(), prop);
            if (field == null) {
                System.out.println("⚠️ Campo no encontrado: " + prop);
                return defaultValue;
            }

            field.setAccessible(true);
            Object value = field.get(obj);
            System.out.println("✔ Campo encontrado: " + field.getName() + " = " + value);
            return (T) value;
        } catch (IllegalAccessException e) {
            System.out.println("⚠️ No se pudo acceder al campo: " + e.getMessage());
            return defaultValue;
        }
    }

    private Field getFieldRecursive(Class<?> clazz, String prop) {
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(prop);
                return field;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass(); // Buscar en la clase padre
            }
        }
        return null; // No encontrado
    }
}
