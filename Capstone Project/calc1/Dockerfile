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
WORKDIR /usr/spring/calc1
COPY --from=build /home/app/target/calc1-0.0.1-SNAPSHOT.jar /usr/spring/calc1
EXPOSE 9002
CMD ["java", "-jar", "calc1-0.0.1-SNAPSHOT.jar"]