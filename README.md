# SpringFramework-Translater
this is the same translater app as in BootstrapIntegrated_Translater repository, but based on Spring Framework and JSTL tags


#### Prerequisities
- jdk 1.8.07
- MySQL 5.6
- Maven 3.3.3
- Tomcat 8.0.9
- Yandex API Key
- JSTL tag library


#### How to configure a Maven WebApp Project
- make a directory for Maven projects
- `cd /path to the directory/` in the terminal
- then execute `mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app`
  `DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false`                 
- move to the created directory `my-app`
- Then build the project as `mvn package`
- newly compiles and packaged jar file is seen


#### How to deploy in Tomcat
- move to the tomcat installation directory. It contains a `webapp` directory
- copy and paste the `war` file created from buliding the maven project
- start tomcat by running `./startup.sh` file in the bin directory 

#### How to access the application
- In the browser type`127.0.0.1:8080/my-app`


> Other than the above mentioned basic funtionalities, there are some integrated features. They are listed below

#### Integrated Features
- BootStrap is integrated to give a rich look to the app
- TestNG is integrated for Unit Testing and thereby to test each of the class
- All the parameters are gathered in system.properties file and read from that file

> below shows the basic file structure of where to insert the above mentioned feature rending packages and files. In addition to that pom.xml file has to be updated adding dependencies as in the project

#### File Structure
- src
  * main
      + java
      - resources
          - system.properties  
      - webapp
          - bootstrap
          - css
          - images
  - test
      - java
    
- pom.xml

#### Considerations
- package structure of `main/java` should be identical to that of `test/java`.
- Give a valid name for the test class so as to relate to the particular class it is testing 

#### Integrated features
- Bootstrap table
- Bootstrap modals
- user Authentication with the MD5 hashed password
- User Add, User Delete, User Update funtionalities
- Seperate User Search function
