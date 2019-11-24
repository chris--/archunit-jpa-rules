package de.christianvogt.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static de.christianvogt.archunit.JpaRules.NO_EAGER_LOADING_ENTITY_ASSOCIATIONS;
import static org.junit.jupiter.api.Assertions.*;


class JpaRulesTest {

    @Test
    void shouldFindEagerAssociationsOnFields() {
        JavaClasses classes = new ClassFileImporter().importClasses(MyEntity.class);
        try {
            NO_EAGER_LOADING_ENTITY_ASSOCIATIONS.check(classes);
            fail(); // AssertionError should have been thrown here
        } catch (AssertionError error) {
            String message = error.getMessage();
            assertTrue(message.contains("was violated (6 times)"));
            assertTrue(message.contains("Eager annotation found at de.christianvogt.archunit.MyEntity.field2_bad"));
            assertTrue(message.contains("Eager annotation found at de.christianvogt.archunit.MyEntity.field3_bad"));
            assertTrue(message.contains("Eager annotation found at de.christianvogt.archunit.MyEntity.field5_bad"));
            assertTrue(message.contains("Eager annotation found at de.christianvogt.archunit.MyEntity.field8_bad"));
            assertTrue(message.contains("Eager annotation found at de.christianvogt.archunit.MyEntity.field9_bad"));
            assertFalse(message.contains("_good"));
        }
    }

    @Test
    void shouldFindEagerAssociationsOnMethods() {
        JavaClasses classes = new ClassFileImporter().importClasses(MyEntityGetterBased.class);
        try {
            NO_EAGER_LOADING_ENTITY_ASSOCIATIONS.check(classes);
            fail(); // AssertionError should have been thrown here
        } catch (AssertionError error) {
            String message = error.getMessage();
            assertTrue(message.contains("was violated (6 times)"));
            assertTrue(message.contains("Eager annotation found at de.christianvogt.archunit.MyEntityGetterBased.getField2_bad()"));
            assertTrue(message.contains("Eager annotation found at de.christianvogt.archunit.MyEntityGetterBased.getField3_bad()"));
            assertTrue(message.contains("Eager annotation found at de.christianvogt.archunit.MyEntityGetterBased.getField5_bad()"));
            assertTrue(message.contains("Eager annotation found at de.christianvogt.archunit.MyEntityGetterBased.getField8_bad()"));
            assertTrue(message.contains("Eager annotation found at de.christianvogt.archunit.MyEntityGetterBased.getField9_bad()"));
            assertFalse(message.contains("_good"));
        }
    }
}