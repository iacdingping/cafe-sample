package com.vach.cafe.test;


import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionTest {

  public static class SomeType{

    @BlackMark
    void doSomething(){

    }
  }

  @Test
  public void testName() {

    Class<? extends SomeType> aClass = new SomeType().getClass();
    System.out.println(aClass);

  }

  public static List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
    final List<Method> methods = new ArrayList<>();
    Class<?> klass = type;
    while (klass != Object.class) { // need to iterated thought hierarchy in order to retrieve methods from above the current instance
      // iterate though the list of methods declared in the class represented by klass variable, and add those annotated with the specified annotation
      final List<Method> allMethods = new ArrayList<>(Arrays.asList(klass.getDeclaredMethods()));

      for (final Method method : allMethods) {
        if (annotation == null || method.isAnnotationPresent(annotation)) {
          Annotation annotInstance = method.getAnnotation(annotation);
          // TODO process annotInstance
          methods.add(method);
        }
      }
      // move to the upper class in the hierarchy in search for more methods
      klass = klass.getSuperclass();
    }
    return methods;
  }
}
