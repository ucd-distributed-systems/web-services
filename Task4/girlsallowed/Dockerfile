# Necessary environment to run application
FROM eclipse-temurin:21-jdk-alpine
# copy our local jar file from target directory to container as /girlsallowed.jar
COPY target/girlsallowed-0.0.1-shaded.jar /girlsallowed.jar
# run the java application packaged as a jar file
# changed to just java because /usr/bin/java not guaranteed to exist within docker container
# might change based on the environment, java is recognized by docker
CMD ["java", "-jar", "/girlsallowed.jar"]