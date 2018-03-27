# ASCII Draw

## Pre-requisites ##

  1. Maven 
  2. JDK (v1.7 or higher)

## Build Instructions ##

  1. Navigate to the parent source folder (cd /path/to/source) containing the pom.xml
  2. Run maven build : `mvn compile`
  3. Create the JAR : `mvn package`

## Running the application ##
  1. Locate the snapshot JAR (asciidraw-1.0-SNAPSHOT.jar)
  2. Run the main app class (note: use the correct path under the project folder)

    java -cp target/asciidraw-1.0-SNAPSHOT.jar com.ubs.asciidraw.App`