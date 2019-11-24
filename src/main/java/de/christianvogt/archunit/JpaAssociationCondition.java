package de.christianvogt.archunit;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import javax.persistence.*;
import java.util.Set;

class JpaAssociationCondition extends ArchCondition<JavaClass> {

    private static final String EAGER_ANNOTATION_FOUND_MESSAGE = "Eager annotation found at %s %s";

    JpaAssociationCondition(String description, Object... args) {
        super(description, args);
    }

    @Override
    public void check(JavaClass item, ConditionEvents events) {
        handleFields(item, events);
        handleMethods(item, events);
    }

    private void handleFields(JavaClass item, ConditionEvents events) {
        Set<JavaField> allFields = item.getAllFields();
        allFields.stream()
                .filter(f -> f.tryGetAnnotationOfType(OneToOne.class).isPresent())
                .filter(f -> !FetchType.LAZY.equals(f.getAnnotationOfType(OneToOne.class).fetch()))
                .forEach(a -> events.add(buildViolationEvent(a)));
        allFields.stream()
                .filter(f -> f.tryGetAnnotationOfType(OneToMany.class).isPresent())
                .filter(f -> !FetchType.LAZY.equals(f.getAnnotationOfType(OneToMany.class).fetch()))
                .forEach(a -> events.add(buildViolationEvent(a)));
        allFields.stream()
                .filter(f -> f.tryGetAnnotationOfType(ManyToOne.class).isPresent())
                .filter(f -> !FetchType.LAZY.equals(f.getAnnotationOfType(ManyToOne.class).fetch()))
                .forEach(a -> events.add(buildViolationEvent(a)));
        allFields.stream()
                .filter(f -> f.tryGetAnnotationOfType(ManyToMany.class).isPresent())
                .filter(f -> !FetchType.LAZY.equals(f.getAnnotationOfType(ManyToMany.class).fetch()))
                .forEach(a -> events.add(buildViolationEvent(a)));
    }

    private void handleMethods(JavaClass item, ConditionEvents events) {
        Set<JavaMethod> allMethods = item.getAllMethods();
        allMethods.stream()
                .filter(m -> m.isAnnotatedWith(OneToOne.class))
                .filter(f -> !FetchType.LAZY.equals(f.getAnnotationOfType(OneToOne.class).fetch()))
                .forEach(f -> events.add(buildViolationEvent(f)));
        allMethods.stream()
                .filter(m -> m.isAnnotatedWith(OneToMany.class))
                .filter(f -> !FetchType.LAZY.equals(f.getAnnotationOfType(OneToMany.class).fetch()))
                .forEach(f -> events.add(buildViolationEvent(f)));
        allMethods.stream()
                .filter(m -> m.isAnnotatedWith(ManyToOne.class))
                .filter(f -> !FetchType.LAZY.equals(f.getAnnotationOfType(ManyToOne.class).fetch()))
                .forEach(f -> events.add(buildViolationEvent(f)));
        allMethods.stream()
                .filter(m -> m.isAnnotatedWith(ManyToMany.class))
                .filter(f -> !FetchType.LAZY.equals(f.getAnnotationOfType(ManyToMany.class).fetch()))
                .forEach(f -> events.add(buildViolationEvent(f)));
    }

    private SimpleConditionEvent buildViolationEvent(JavaField item) {
        return new SimpleConditionEvent(
                item,
                false,
                String.format(EAGER_ANNOTATION_FOUND_MESSAGE, item.getFullName(), item.getSourceCodeLocation().toString())
        );
    }

    private SimpleConditionEvent buildViolationEvent(JavaMethod method) {
        return new SimpleConditionEvent(
                method,
                false,
                String.format(EAGER_ANNOTATION_FOUND_MESSAGE, method.getFullName(), method.getSourceCodeLocation().toString())
        );
    }
}
