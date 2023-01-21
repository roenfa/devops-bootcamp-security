# SpringBoot-REST-api

 
## Usage  

To be able to connect the applicaction to a database the running command must include the connection url, username, password and driver class name as follows:
~~~
./gradlew bootRun -PdbUrl='****' -PdbUser=**** -PdbPassword=**** -PdbDriverClass='*****' -PawsAccessKey=**** -PawsAccessSecret=*****
~~~

For testing and to avoid using above command, beforementioned propperties can be added in the application.properties file:
~~~
spring.datasource.url = your url
spring.datasource.user = your user
spring.datasource.password = your password
spring.datasource.driverClassName = your driver class name
jwt.secret = your secret
~~~