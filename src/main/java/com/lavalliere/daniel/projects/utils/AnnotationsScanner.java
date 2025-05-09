package com.lavalliere.daniel.projects.utils;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

// https://www.baeldung.com/java-scan-annotations-runtime
public class AnnotationsScanner {
    public static List<String> scan(
        Class<? extends java.lang.annotation.Annotation> annotationClass,
        String basePackage,
        Consumer<Object> consumer
    ) {
        // useDefaultFilters - whether to register the default filters for the @Component, @Repository, @Service, and @Controller stereotype annotations
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);

        // The annotated class to scan for
        provider.addIncludeFilter(new AnnotationTypeFilter(annotationClass));

        List<String> annotatedBeans = new ArrayList<>();
        Set<BeanDefinition> beanDefs = provider.findCandidateComponents(basePackage);

        for (BeanDefinition bd : beanDefs) {
            if (bd instanceof AnnotatedBeanDefinition bean) {
                Map<String, Object> annotAttributeMap = bean
                    .getMetadata()
                    .getAnnotationAttributes(annotationClass.getCanonicalName());
                annotatedBeans.add(annotAttributeMap.get("name").toString());
                if (consumer != null && !annotAttributeMap.isEmpty()) {
                    try {
                       System.out.printf("+++ Demo of class %s\n",bd.getBeanClassName());

                       // Class must have a default constructor … for this implementation
                       consumer.accept(Class.forName(bd.getBeanClassName()).getConstructor().newInstance());
                    } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                             InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return annotatedBeans;
    }
}
