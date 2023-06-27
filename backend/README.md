# Spring Boot application for Mocha - Back-End
---

<br/>

![ntnu_bredde_eng](https://user-images.githubusercontent.com/44703456/222424176-cb7248c2-33d5-49cd-8e0c-742bab24c4c1.png)

<br/>

## Introduction
This is the back-end application for Mocha. The application is written as a Java Spring Boot project for a portfolio assessment during the spring 
of 2023.

<b>Group:</b> 10</br>
<b>Course:</b> <i><a href="https://www.ntnu.edu/studies/courses/IDATA2306#tab=omEmnet">IDATA2306 - Application Development</a></i></br>
<b>Year:</b> 2023</br>
<b>University:</b> NTNU - Ålesund

<br/><br/>

### Authors

<table>
    <tr>
        <th>Student name:</th>
        <th>GitHub account:</th>
        <th>Email account:</th>
    </tr>
    <tr>
        <td>Jørgen Finsveen</td>
        <td><a href="https://github.com/jorgenfinsveen">jorgenfinsveen</a></td>
        <td>jorgfi@stud.ntnu.no</td>
    </tr>
    <tr>
        <td>Petter Edvardsen</td>
        <td><a href="https://github.com/Edvardsn">Edvardsn</a></td>
        <td>petteed@stud.ntnu.no</td>
    </tr>
    <tr>
        <td>Even Johan Pereira Haslerud</td>
        <td><a href="https://github.com/ejhasler">ejhasler</a></td>
        <td>ejhasler@stud.ntnu.no</td>
    </tr>
    <tr>
        <td>Oskar Lothe</td>
        <td><a href="https://github.com/oskarlothe">oskarlothe</a></td>
        <td>oskarlo@stud.ntnu.no</td>
    </tr>
</table>


<br/><br/>


## Requirements

In order to run this application, you will need the following:

* Apache Maven
  * Download: <a href="https://maven.apache.org/download.cgi">Apache Maven</a>
* GIT
* Java Development Kit (JDK) 17 or 19
  * Download: <a href="https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html">JDK 19</a>
  * Download: <a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html">JDK 17</a>


<br/><br/>

## Getting Started

First, clone the repository to your computer. This can be done by running the following command:

```sh
git clone https://github.com/Group10-IDATA2301-IDATA2306/Mocha-cafe.git
```

<br/>

There are several options available for starting the application. The different options
starts the application with different application.properties files. For convenience, there
are two executables in this directory which starts the application in either:

* Production launch
* Testing launch


<br/>


### Production
For production launching i.e., normal launch of the application. One may use the command:
```sh
mvn spring-boot:run
```
One may also launch the application by executing the following executable: &nbsp;&nbsp;&nbsp; <i><a href="https://github.com/Group10-IDATA2301-IDATA2306/Mocha-cafe/blob/main/backend/prod">prod</a></i>

---

<br/>

### Test
For test launching, one may use the following command:

```sh
mvn spring-boot:run -Dspring-boot.run.profiles=test
```

One may also launch the application by executing the following executable: &nbsp;&nbsp;&nbsp; <i><a href="https://github.com/Group10-IDATA2301-IDATA2306/Mocha-cafe/blob/main/backend/test">test</a></i>

---

<br/>

### JAR
One may also compress the project into a JAR and execute it. This is what has been done when deploying the application on the OpenStack server. In order to do this, first execute the following command:

```sh
mvn clean compile package install
```

Then, locate the file named MochaServer.jar, which should be available here:
```sh
.
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── prod
├── src/
├── target/
│   └── MochaServer.jar
└── test
```

In order to execute the application, open a Terminal window and execute the following command:

```sh
java -jar /PATH/TO/THE/JAR/FILE/MochaServer.jar
```

<br/><br/>


## REST API

<br/>

### Swagger documentation

The REST API implementation has been documented using Swagger. If you would like to
consult the documentation, please see the following links:
* <a href="https://localhost:8080/swagger-ui/index.html">localhost:8080/swagger-ui</a>
* <a href="http://group10.web-tek.ninja/swagger-ui/index.html">web-tek.ninja/swagger-ui</a>

<br/>

### Postman tests

For testing the API, the group has used Postman tests. The tests should be available here:
* Postman test collection: <a href="https://ntnu-iir-students.postman.co/workspace/Team-Workspace~8b23436b-0cdb-468a-8652-f57c420e9db8/collection/27687274-53b8f976-0479-4d92-84bf-735368e5f7dd?action=share&creator=27687274">AppDev 2023</a>
* Exported to JSON: <a href="https://github.com/Group10-IDATA2301-IDATA2306/Mocha-cafe/tree/main/backend/postman">⁓/postman</a>

<br/>

### MockMvc tests with JUnit

The group has also conducted MockMvc tests. The tests completes without errors, but it may not do so if all tests are runned at the same time. We would therefore recomend to only run one test-class at a time, and wait for it to finish before running the next. The tests can be viewed here: 
* MockMvc tests: <a href="https://github.com/Group10-IDATA2301-IDATA2306/Mocha-cafe/tree/main/backend/src/test/java/no/ntnu/mocha">⁓/src/test/java/no/ntnu/mocha</a>


<br/><br/>

## Application configuration

The application is deployed on an OpenStack server borrowed from NTNU. The entire application is secured with a self-signed keystore.p12 SSL-certificate, enabling HTTPS for the application. For authentication, the application uses JSON Web Tokens. The database is running on a MySQL/MariaDB server provided by Loopia, which also provided the email client used for sending emails. The secret information in application.properties are in a file named env.properties, which is not included in the GitHub repository.


<br/>

<b>OpenStack</b>:
* Domain name: <i><a href="http://group10.web-tek.ninja">group10.web-tek.ninja</a></i>
* Owner: Norwegian University of Science and Technology
* Platform:
  * Operating system: Ubuntu 22.04
  * Cores: 2x vCPU
  * Memory: 6GB
  * Storage: 40GB (SSD)
  * Internal IPv4: 10.212.26.80
  * Public IPv6: 2001:700:300:6018:f816:3eff:fe31:b3c6
  * Nginx:
    * Forwarding proxy from default port 80 to https://localhost:8080

<br/>

<b>Database</b>:
* Service provider: <i><a href="https://www.loopia.no">Loopia</a></i>
* Host: <i><a href="mysql678.loopia.se">mysql678.loopia.se</a></i>
* Owner: Jørgen Finsveen (Bought for non-related reasons)
* DBMS: MariaDB/MySQL


<br/>

<b>SMTP client</b>:
* Service provider: <i><a href="https://www.loopia.no">Loopia</a></i>
* Host: <i><a href="mailcluster.loopia.se">mailcluster.loopia.se</a></i>
* Owner: Jørgen Finsveen (Bought for non-related reasons)
* Username: noreply@finsveen.dev


<br/><br/>

## See

<b>Some documentation</b>:
* Database Schema: <i><a href="https://github.com/Group10-IDATA2301-IDATA2306/Mocha-cafe/wiki/Database-Schema">Schema</a></i>
* Sprint Reports: <i><a href="https://github.com/Group10-IDATA2301-IDATA2306/Mocha-cafe/blob/main/backend/Sprint-IDATA2306.pdf">Sprints</a></i>