# KeyStone

create a new repo for your project on github abd clone it in your device 

open repo in your IDE 

now go to [Spring initializer](https://start.spring.io/) 
choose :
    Project: maven
    Language: java
    version: 4.1.0 

    Group: com.KeyStone
    Artifact: DeliveryService
    Package name: com.KeyStone.DeliveryService

    Packaging: Jar
    Configuration: Properties
    java: 17 or 21 
    
    add Dependencies like 
    *   MySQL Driver SQL
        <!-- MySQL JDBC driver. -->

    *   Spring Web Web
    <!-- Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container. -->
    *   Spring Security Security
        <!-- Highly customizable authentication and access-control framework for Spring applications. -->
    *   Spring Data JPA SQL
    <!-- Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate. -->
    Validation I/O
    <!-- Bean Validation with Hibernate validator. -->
    Lombok Developer Tools
    <!-- Java annotation library which helps to reduce boilerplate code. -->
    Spring Boot DevTools Developer Tools
    <!-- Provides fast application restarts, LiveReload, and configurations for enhanced development experience. -->

download the zip in your project repo/folder

open terminal for project 
run unzip <filename> 

for me unzip DeliveryService.zip

you can remove the zip by 
rm -rf DeliveryService.zip