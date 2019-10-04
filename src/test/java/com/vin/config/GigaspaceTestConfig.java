package com.vin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

@Configuration
public class GigaspaceTestConfig {

    @Bean
    public GenericContainer gigaspaceContainer() {
        return new FixedHostPortGenericContainer("gigaspaces/xap")
                .withFixedExposedPort(4174, 4174)
                .waitingFor(
                        //Wait strategy to look for log file contents and not forListening port as even if the LUS start the space is not up fully.
                        //Wait.forListeningPort()
                        //Below line ensures that the container is is fully started
                        Wait.forLogMessage(".*Started successfully*\\n", 4)
                );
    }
}
