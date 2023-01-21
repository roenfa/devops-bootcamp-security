# SpringBoot-REST-api

 
## Usage  

To be able to connect the applicaction to a database the running command must include the connection url, username, password, driver class name, aws access key and aws secret as follows:
~~~
./gradlew bootRun -PdbUrl='****' -PdbUser=**** -PdbPassword=**** -PdbDriverClass='*****' -PawsAccessKey=**** -PawsAccessSecret=*****
~~~

For testing and to avoid using above command, beforementioned propperties can be added in the application.properties file:
~~~
aws.access-key = your access key
aws.access-secret = your access secret
...
spring.datasource.url = your url
spring.datasource.user = your user
spring.datasource.password = your password
spring.datasource.driverClassName = your driver class name
jwt.secret = your secret
~~~