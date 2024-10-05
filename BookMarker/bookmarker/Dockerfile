 #base image
# FROM eclipse-temurin:21-jre
#
# #springboot fat jar
# ADD target/*.jar app.jar
# RUN sh -c 'touch /app.jar'
# EXPOSE 8080
# ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]


#create docker image using the build command
#docker build -t akhildev/bookmarker.

FROM openjdk:17-jdk
COPY target/Bookmarker.jar .
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "Bookmarker.jar"]