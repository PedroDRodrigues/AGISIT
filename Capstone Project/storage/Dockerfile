#
# Build stage
#
FROM maven:3-amazoncorretto-21 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM amazoncorretto:21-alpine-jdk
WORKDIR /usr/spring/storage
COPY --from=build /home/app/target/storage-0.0.1-SNAPSHOT.jar /usr/spring/storage
EXPOSE 8000
CMD ["java", "-jar", "storage-0.0.1-SNAPSHOT.jar"]