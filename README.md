ArchUnit Rule to detect eagerly fetched JPA associations, because we don't like global fetch plans! 
Make sure to read https://vladmihalcea.com/eager-fetching-is-a-code-smell/

## Usage
Add a Test and import the ArchRule:

```
(...)
import static de.christianvogt.archunit.JpaRules.NO_EAGER_LOADING_ENTITY_ASSOCIATIONS;

@AnalyzeClasses(packages = "org.example")
class MyJpaTest {
    @ArchTest
    private ArchRule no_eager_loading = NO_EAGER_LOADING_ENTITY_ASSOCIATIONS;
}
```

The test will fail when any JPA association is eagerly fetched.  

## Credits
Vlad Mihalcea for his awesome JPA/Hibernate Blog!
