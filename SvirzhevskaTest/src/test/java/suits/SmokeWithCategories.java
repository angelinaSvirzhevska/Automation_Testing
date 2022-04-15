package suits;

import categories.SmokeTestFilter;
import loginTest.loginTestWithPO;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTest.RegistrationTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        loginTestWithPO.class,
        RegistrationTest.class
})

public class SmokeWithCategories {

}
