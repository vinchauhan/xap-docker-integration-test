package com.vin;

import com.vin.config.GigaspaceTestConfiguration;
import com.vin.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GigaspaceTestConfiguration.class})
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class MyTest {

    @Autowired
    public GenericContainer genericContainer;

    private GigaSpace gigaSpace;

    @Before
    public void setUp() throws Exception {
        genericContainer.start();
        //System.out.println("Mapped Port :"+ genericContainer.getMappedPort(4174) + genericContainer.getEnv());
        //genericContainer.getMappedPort(4174);

        //Initialize gigaspace instance
        //Muticast access to the remote space.
        UrlSpaceConfigurer urlSpaceConfigurer = new UrlSpaceConfigurer("jini://localhost/*/demo");
        urlSpaceConfigurer.lookupTimeout(20000);
        GigaSpaceConfigurer gigaSpaceConfigurer = new GigaSpaceConfigurer(urlSpaceConfigurer);
        gigaSpace = gigaSpaceConfigurer.gigaSpace();

//        UrlSpaceConfigurer urlSpaceConfigurer = new UrlSpaceConfigurer("jini://*/*/demo");
//        urlSpaceConfigurer.lookupGroups("xap-14.2.0");
//        urlSpaceConfigurer.lookupLocators("localhost:7174");
//        urlSpaceConfigurer.lookupTimeout(20000);
//        GigaSpaceConfigurer gigaSpaceConfigurer = new GigaSpaceConfigurer(urlSpaceConfigurer);
//        gigaSpace = gigaSpaceConfigurer.gigaSpace();
    }

    @Test
    public void savePosting() {
        User user = new User();
        user.setUserId(Long.parseLong("12121281989182"));
        user.setUserName("Vineet");
        user.setPassword("Vineet");
        System.out.println("Saving User");;
        gigaSpace.write(user);
    }

    @After
    public void tearDown() throws Exception {
        genericContainer.stop();
    }
}
