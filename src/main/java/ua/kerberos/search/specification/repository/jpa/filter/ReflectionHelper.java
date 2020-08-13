package ua.kerberos.search.specification.repository.jpa.filter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectionHelper {

    public static boolean isEnumListType(Type genericType) {
        if ((genericType instanceof ParameterizedType) == false) return false;

        val pType = (ParameterizedType) genericType;
        Class<?> clazz = (Class<?>) pType.getActualTypeArguments()[0];

        return clazz.isEnum();
    }

    public static boolean isFieldValueEmpty(Field f, Object object) {
        val value = ReflectionUtils.getField(f, object);

        if (value == null) return true;

        boolean result = false;
        result |= "".equals(value);
        result |= isValueEmptyList(f, value);

        return result;
    }

    public static boolean isFieldValueNotEmpty(Field f, Object object) {
        return isFieldValueEmpty(f, object) == false;
    }

    public static boolean isValueEmptyList(Field f, Object value) {
        if (Arrays.asList(f.getType().getInterfaces()).contains(Collection.class) == false) return false;

        return ((Collection<Object>) value).isEmpty();
    }

    public static <T> T newInstance(Class<? extends T> handlerType) {
        T result = null;
        try {
            result = ReflectionUtils.accessibleConstructor(handlerType).newInstance();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return result;
    }

    @SneakyThrows
    public static Class extractFieldType(Class targetClass, String fieldName) {
        try {
            return targetClass.getDeclaredField(fieldName).getType();
        } catch (Exception e) {
            return targetClass.getSuperclass().getDeclaredField(fieldName).getType();
        }
    }

    public static void setAccessible(Field field) {
        field.setAccessible(true);
    }

    public static boolean checkFieldNotStatic(Field field) {
        return Modifier.isStatic(field.getModifiers()) == false;
    }

    public static Predicate<Field> fieldValueNotEmpty(Object object) {
        return field -> isFieldValueNotEmpty(field, object);
    }
}
