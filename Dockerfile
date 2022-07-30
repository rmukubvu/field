FROM adoptopenjdk/openjdk11:alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
VOLUME /tmp
MAINTAINER thamserios.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} field-watch-services.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/field-watch-services.jar"]
