MohsenFattahi
Rasool
effect# Objective 1 
1. How would you access a *NIX server remotely in order to debug a problem?
```
To be honest I do not have any experience about NIX server but I think you would be able to run your application 
in your server with choosing an open port for listening and tracking a problem during running your 
application and according the open port you will be able to debug your problem by using
Intellij and Eclipse.They have a facility to listen the open port in specific server.
And you can make a break point in your code and during sending a requrst or getting a response you should be able to
debug your code step by step.
For example:
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -jar jarName.jar
That means port 5005 is a listen port to debug a problem.
```
2. How would you version your application?
```
versioning in the application can be really important because on the versioning anyone can understand about 
changing inside of the application. as usual versioning consists three things such as MAJOR.MINOR.PATCH
that means:
MAJOR version means when you have or make incompatible API changes.
MINOR version means when you add functionality in a backwards-compatible manner.
PATCH version means when you have or make backwards-compatible bug fixes.
And you should be able to use two words such as SNAPSHOP AND RELEASE to show the stability of application.
```
3. How do you deliver your application to your team and for deployment?
```
We can use CI/CD of devops culture. In this area we will be able to use a plenty of applications and tools
such as jenkins, docker, kubernetes, GIT and etc that they give us a lot of facilities to deliver your application to
your team and even for deployment.
for example a simple push in your repository can run automatically a build trigger on the jenkins.
And then jenkins can make a version of application and docker should be able to deploy them.
The effect of using devops culture can be definitely useful to devilver your application in your team.
Devops culture would be able to help us for betterment to devliver and create large application.
In my opinion using agile methodology should be used.Due to the agile methodology, the development process is aligned to deliver the changing business requirement.
In agile methodology we have some ceremonies such as Scrum planning, Scrum review, Scrum Daily stand up, and scrum retrospective.
for example Scrum Daily stand up can be one of the best way to devliver your application in your team.
Because in the Scrum Daily stand up you are able to decsribe about your tasks and activities in your team members.
Mix of agile and devops culture help us for having a good procees of create application. 
```
4. If you would have to implement an authorisation / authentication system, what kind of patterns you
would choose?
```
I would implement an authorisation / authentication system by Oauth2 and token pattern because you will be able 
to compatible with other technologies and other external services and it can be implemented by spring security
and spring-oauth2 as well. The effect of using oauth2 can be really useful and faster than another pattern because 
you are able to login only one time and then you are authorised in other applications that should be compatible with oauth2 application.
```
# Objective 2

# Book Store
This a sample project for a book store that is implemented by spring and spring boot.
It took 4 hours to complete it.

## the pre-requisites
* You have to install Java 8.
* You have to install Maven 3.

## Technologies: 
* Spring REST
* Spring AOP
* Spring Data JPA
* HSQL
* Swagger
* Spring Test
* Spring web  

### Run test methods:
```
Use "mvn clean package" to run the tests with HSQL DB.
```

### Run in development envirenment:
To run the project with **spring-boot:run** in development environment.
In development enviroment you will be able to make more instances according the number of request 
if you can config spring cloud(Eureka) in this project and make a instance from its Eureka server.
```
This sample uses a HSQL database and you do not need to install and config a data base.
After runing you can test and see API(s) on a browser by this URL http://localhost:8080/swagger-ui.html.  
```

### Run in production environment:
Use java -jar file with the below command:  
**java -jar -Dspring.profiles.active=prod jarName.jar**

## Task lists
You can use these following stack technology to make the application more enterprise:
- [ ] Spring Security
- [ ] Spring Cloud
- [ ] Spring Session
- [ ] Spring Oauth2
- [ ] API gateway like Zuul
- [ ] Circuit breaker
- [ ] Spring Cache

## Objective 3:
 If we assume that there is one table to save history of access book that who access to the book and in the table 
 there is a field for saving date of access.Each time when you are giving a book a client you should be able to insert 
 a record in this table.
 There are 2 different approaches to show the last access time of a book.
 
 ## Approach 1
 1- You would be able to create a SQL query and getting last access date of the book.
 for example : select max(date_access) from book_access where bookID=1
 ## Pros and cons approach 1
 1. Redundancy of data in approach 1 is really low .
 2. you do not need to have a update command.You have only a insert command.
 3. It is really fast during the giving a book a client but can be really slow during the getting last access time of the book.

 
 ## Approach 2
You would be able to create a field in book table and each time when a client wants to access a book you can update 
The field of book table by value of current date and insert a record in book_access at the same time. 
With this way you do not need to creat a sql query and all time when you load a book the last access time is loaded. 
## Pros and cons approach 2 
1. Redundancy of data in approach 2 is really high because you have to save the last time of a book in 2 table.
2. It is really slow during the giving a book a client but really fast during the getting last access time of the book.
3. you need to have a update and insert command.


## Choose one approach
In my opinion choosing approach 1 and 2 can be selected. It depends on how much of your data.
If we have a small application with a few data approach 1 should be selected.Redundancy of data in approach 1 is really low.
If we have a lot of transaction, approach 2 should be selected because in this way retrieving data can be really fast.

Eventually if I want choose one approach I will choose approach 1 and as time goes on if I see that I will have a lot of data
I can convert approach 1 to approach 2.     

  





 
 
 
   
 
