1. To start the application:

$ mvn spring-boot:run

will run at: localhost:8080/repository/{owner}/{name}

2. To run unit test - RepositoryControllerTest:

$ mvn clean test

3. To run the full end-to-end test - ApplicationIT:

$ mvn clean integration-test


Side note:
The end-to-end test uses real github.com site to retrieve repository details of angular project.




