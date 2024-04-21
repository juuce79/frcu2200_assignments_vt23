package com.dt180g.project.support;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public final class TestSupportExtra {
    public static void confirmClassIsAbstract(Class<?> clazz) {
        int modifiers = clazz.getModifiers();
        assertTrue(Modifier.isAbstract(modifiers), "Class " + clazz.getName() + " is not abstract");
    }

    public static void confirmPrivateFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            int modifiers = field.getModifiers();
            assertTrue(Modifier.isPrivate(modifiers),
                    "Field " + field.getName() + " in class " + clazz.getName() + " is not private");
        }
    }

    public static void assertReturnType(Method method, Class<?> primitiveClass, Class<?> wrapperClass) {
        Class<?> returnType = method.getReturnType();
        assertTrue(primitiveClass.equals(returnType) || wrapperClass.equals(returnType),
                "Return type of '" + method.getName() + "' needs to be " + primitiveClass.getSimpleName() + " or " + wrapperClass.getSimpleName() + "."
        );
    }
}
