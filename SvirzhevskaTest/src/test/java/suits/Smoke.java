package suits;

import loginTest.loginTestWithPO;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTest.RegistrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        loginTestWithPO.class,
        RegistrationTest.class
})

public class Smoke {
}
