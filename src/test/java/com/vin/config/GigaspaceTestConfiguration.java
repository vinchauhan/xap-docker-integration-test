package com.vin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

@Configuration
public class GigaspaceTestConfiguration {

    @Bean
    public GenericContainer gigaspaceContainer() {
//        int hostPort = 4174;
//        int containerExposedPort = 4174;
//        Consumer<CreateContainerCmd> cmd = e -> e.withPortBindings(new PortBinding(Ports.Binding.bindPort(hostPort), new ExposedPort(containerExposedPort)));

        return new FixedHostPortGenericContainer("gigaspaces/xap")
                .withFixedExposedPort(4174, 4174)
                .waitingFor(
                        //Wait.forListeningPort()
                        Wait.forLogMessage(".*Started successfully*\\n", 4)
                );
//                .waitingFor(
//                        Wait.forLogMessage(".*INFO [org.openspaces.pu.container.integrated.IntegratedProcessingUnitContainer] - Started successfully*\\n",1)
//                );
    }

//    @Bean
//    public GigaSpace gigaSpace() {
//        //Muticast access to the remote space.
//        UrlSpaceConfigurer urlSpaceConfigurer = new UrlSpaceConfigurer("jini://localhost/*/demo?groups=xap-14.2.0");
//        urlSpaceConfigurer.lookupTimeout(20000);
//        GigaSpaceConfigurer gigaSpaceConfigurer = new GigaSpaceConfigurer(urlSpaceConfigurer);
//        return gigaSpaceConfigurer.gigaSpace();
//    }
}
