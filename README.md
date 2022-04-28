#London API
## Purpose
This simple microservice provides an API that returns users that either have London listed
as their city in their profile, or have located within 50 miles of London.

## Environment
* Java 8
* Springboot

## Usage
1. Package the code - `mvn clean install`
2. From a terminal window, `java -jar target/<jar name>` (replacing <jar name> by the name
of the packaged JAR file.)
3. Open a browser to http://localhost:8080/v1/london-users 

## Limitations
This project has only been configured to identify users in or around London.  However, the
`UsersService` class has been written in a way that could allow for additional queries to
take place, for example, using different cities and/or location.

