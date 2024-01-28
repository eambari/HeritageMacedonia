FROM openjdk:8-jdk
ARG JAR_FILE=target/*.jar
COPY target/MkHeritage-0.0.1.jar mkheritage-back.jar

ENTRYPOINT ["java", "-jar", "/mkheritage-back.jar"]
