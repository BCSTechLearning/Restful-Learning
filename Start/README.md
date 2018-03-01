# RESTFUL-LEARNING MASTER - OAUTH2.0 AUTHORIZATION

### Introduction:

- Helping in learning and testing integration between `Spring` and `Spring Security`.

- Helpful for the Developers who are interested in `Oauth2.0 Authorization Application`.


Below Diagram is showing the structure of Restful learning showing how we are getting the output with Oauth2 Authentication:


- The `/birds/{birdId}` get end point is protected by Oauth2.0 Authorization and require an oauth token for access.
- The `/birds/noException/{birdId}` get end point is protected by Oauth2.0 Authorization and require an oauth token for access.
- The `/birds` post end point is protected by Oauth2.0 Authorization and require an oauth token for access.

![Diagram]()


![Diagram]()


### Getting Started:

- It starts by making a [spring-boot template](start.spring.io) .

- You can get information from this learning for testing and development purpose.

- Helpful in Rest Applications.



### You will learn: 

- You will be able to develop and design RESTful web services.

- Understand the best practices in designing RESTful web services.

- Able to understand how to connect RESTful Services to a back end with API.

- Able to understand how to use Oauth2.0 Authorization.


### Prerequisites:

This should be installed in your system:

- [JDK 1.8](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)

- [Tomcat 8]( http://tomcat.apache.org/tomcat-8.0-doc/)


### Installing:

To get a project running successfully you have to follow the given steps:


##### STEPS-


**1 Open application in IDE using import maven project:**

- Run the application.

 
**2 To run the development network:** 
 
- Navigate
```
localhost:8080/birds/{birdId}
```

- Ensure that you will get `Information of Bird to corrosponding birdId` in return.

### Looking for something in particular?

| Spring Boot Configuration |Class or Java property files|
| --------------------------|----------------------------|
|   Main Class              |[StartApplication.main()]( https://github.com/BCSTechLearning/Restful-Learning/blob/atul/Start/src/main/java/com/BCS/training/Start/StartApplication.java )|
|   Properties Files        |[Application.properties]( https://github.com/BCSTechLearning/Restful-Learning/blob/atul/Start/src/main/resources/application.properties )| 
|   Maven Configuration     | [pom.xml ]( https://github.com/BCSTechLearning/Restful-Learning/blob/atul/Start/pom.xml ) |



### Deployment:

Follow the installation steps on your desired platform:

- Build the application with imbedded IntelliJ build/run option.


### Appplication Built With:

**1 Springboot-**  Framework

**2 IntelliJ-**  IDE

**3 Maven-**  Dependency Management



### Credits:

[**Atul Kumar**](atul.lkumar@bcstechnology.com.au)




### Acknowledgment:

- Spring Team
- [Maven](https://maven.apache.org/what-is-maven.html)


 













