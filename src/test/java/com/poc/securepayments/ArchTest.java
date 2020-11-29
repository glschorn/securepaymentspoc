package com.poc.securepayments;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.poc.securepayments");

        noClasses()
            .that()
            .resideInAnyPackage("com.poc.securepayments.service..")
            .or()
            .resideInAnyPackage("com.poc.securepayments.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.poc.securepayments.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
