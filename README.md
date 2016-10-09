# routefinder

Simple service which can provide information whether two given stations are connected by a bus route. 
The information is based on the list of bus routes in a bus route data file.

## Project Structure / Architecture

Implementation is based on Spring Boot framework, which provide an easy way to build spring based applications and with minimal configuration allows to package/run it on J2EE application server such as Tomcat or Jetty.

Application contains single controller class which implements a REST-API serving GET requests to http://localhost:8088/api/direct?dep_sid={}&arr_sid={}.
The parameters dep_sid (departure) and arr_sid (arrival) are two station ids (sid) represented by integers.

## Package

In order to package the application execute following command:
```mvn clean package```

Alternatively run build.sh

## Run

To start application execute run service.sh.
Accepts start|stop|block and the path to a bus routes data file as arguments (bash service.sh start FILE).