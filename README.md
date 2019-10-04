#  Integration testing using gigaspace xap docker container.

#### To run the project.

* mvn clean package -DskipTests
* copy /target/demo-1.0-SNAPSHOT.jar to the container's /opt/gigaspaces/lib/required
* run the tests.  

The pojos need to be copied to the /opt/gigaspaces/lib/required so that the RMI classloader know about them.