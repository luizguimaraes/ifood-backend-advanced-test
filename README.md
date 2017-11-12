# iFood Backend Advanced Test

Create a micro-service able to accept RESTful requests receiving as parameter either city name or lat long coordinates and returns a playlist (only track names is fine) suggestion according to the current temperature.

## Business rules

* If temperature (celcius) is above 30 degrees, suggest tracks for party
* In case temperature is above 15 and below 30 degrees, suggest pop music tracks
* If it's a bit chilly (between 10 and 14 degrees), suggest rock music tracks
* Otherwise, if it's freezing outside, suggests classical music tracks 

## Hints

You can make usage of OpenWeatherMaps API (https://openweathermap.org) to fetch temperature data and Spotify (https://developer.spotify.com) to suggest the tracks as part of the playlist.

## Non functional requirements

As this service will be a worldwide success,it must be prepared to be fault tolerant,responsive and resilient.

Use whatever language, tools and frameworks you feel comfortable to. 

Also, briefly elaborate on your solution, architecture details, choice of patterns and frameworks.

Fork this repository and submit your code.

## Instructions 
To build and execute the application you need to install [gradle](https://gradle.org/), then you must execute the following line:
``./gradlew build && java -jar build/libs/ifood-backend-advanced-test-1.0.0.jar``

For test and documentation of the API, access the following address:
http://localhost:8080/playlistSugestion/swagger-ui.html

To access the monitoring services of Spring Actuator (they will require authentication):
* http://localhost:8080/playlistSugestion/admin/health 
* http://localhost:8080/playlistSugestion/admin/env
* http://localhost:8080/playlistSugestion/admin/configprops 
* http://localhost:8080/playlistSugestion/admin/loggers
* http://localhost:8080/playlistSugestion/admin/info
* http://localhost:8080/playlistSugestion/admin/auditevents
* http://localhost:8080/playlistSugestion/admin/liquibase
* http://localhost:8080/playlistSugestion/admin/trace
* http://localhost:8080/playlistSugestion/admin/metrics
* http://localhost:8080/playlistSugestion/admin/dump
* http://localhost:8080/playlistSugestion/admin/beans
* http://localhost:8080/playlistSugestion/admin/heapdump
* http://localhost:8080/playlistSugestion/admin/autoconfig
* http://localhost:8080/playlistSugestion/admin/mappings

The user and password is configured in the application.properties

## Implementation
Frameworks and libraries used:
* Spring Boot - for dependency injection e configuration management
* Spring Data JPA - to access data
* Liquibase - to manage database changes
* H2 - the database to store Weather information
* Spring Security - to secure monitoring services
* Spring Actuator - to provide monitoring services
* Caffeine - for caches
* Swagger and Springfox - to generate documentation for exposed services

The application is divided in the following packages:
* br.com.ifood.backend.advanced.test - root package and hold the configuration classes
* br.com.ifood.backend.advanced.test.controller - package for exposed controller
* br.com.ifood.backend.advanced.test.error.handling - package for exceptions 
* br.com.ifood.backend.advanced.test.model - package for model classes
* br.com.ifood.backend.advanced.test.repository - package for jpa repositories
* br.com.ifood.backend.advanced.test.service - package for services interfaces and implementations