package de.christianvogt.archunit;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import jakarta.persistence.Entity;

/**
 * JPA-ArchRules to be used in ArchUnit-Tests. Run with @ArchTest
 *
 */
public class JpaRules {
    private static String description = "not contain fields or methods that are eagerly fetched";
    /** Find eagerly fetched JPA associations in JPA Entities. */
    public static ArchRule NO_EAGER_LOADING_ENTITY_ASSOCIATIONS = ArchRuleDefinition
            .classes().that()
            .areAnnotatedWith(Entity.class)
            .should(new JpaAssociationCondition(description));
}
