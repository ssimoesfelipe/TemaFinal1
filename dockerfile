FROM gradle:5.3.0-jdk-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:8
COPY --from=build /home/gradle/src/build/libs/calculator-1.0.jar calculator-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/calculator-1.0.jar"]