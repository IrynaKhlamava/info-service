# info-service
Infoservice is a service that provides information about the current exchange rates that the service receives via IP from an external resource 
on a daily schedule. 
Currencies are stored in the database and in the Redis cache. When currencies are requested, they are returned from the cache.  
Has an endpoint for obtaining one currency by the currency code. 
Technology stack: Java 17, Spring Boot, Gradle, Liquibase, Redis, PostgreSQL, Junit, Mockito, REST API
