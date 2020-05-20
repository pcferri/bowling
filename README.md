## Bowling Java Challenge

![Java CI with Maven](https://github.com/pcferri/bowling/workflows/Java%20CI%20with%20Maven/badge.svg)


### Software Requirements

- Java (JDK 8)
- Apache Maven

### Build artifact

To build the JAR artifact, runs the next command line (Also will be executed the unit tests):

  `$ mvn clean package`
  
### Execute application

To execute the application use this command to execute the jar:

  `$ java -jar bowling-app-1.0-SNAPSHOT.jar`
  
Or you can run a maven command to start the application:
  
  `$ mvn exec:java`
  
The application will ask you to put the path of a file to start the game:

Please, type the path of the text file (put the path and press ENTER):
   
   `c:\sample.txt`

### Unit tests

Was created 33 unit tests, 4 tests cases for integration tests using real examples.
   
   
### Sample

You can use this example inserted into GitHub to test the Game:

`sample-input.txt`

### Change the configurations of the Game

You can change the parameters inside of the `application.properties` to extend the Game to be able to work with different styles of the game, like five-pin bowling (But this feature was not cover by the tests). 

`limit.frames=10`

`rolls.last.frame=3`

`rolls.normal.frame=2`

`max.pins=10`
