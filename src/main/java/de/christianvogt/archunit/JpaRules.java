package de.christianvogt.archunit;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import javax.persistence.Entity;

public class JpaRules {
    private static String description = "not contain fields or methods that are eagerly fetched";
    public static ArchRule NO_EAGER_LOADING_ENTITY_ASSOCIATIONS = ArchRuleDefinition
            .classes().that()
            .areAnnotatedWith(Entity.class)
            .should(new JpaAssociationCondition(description));
}
