FROM openjdk:8-jdk-alpine
ENV DATA_SOURCE='jdbc:mysql://127.0.0.1:3306'
ENV DATA_USERNAME='root'
ENV DATA_PASSWORD='pass'
ENV LOCAL_FILE_PATH='~/file/'
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod", "--spring.datasource.url=jdbc:mysql://${DATA_SOURCE}/ptCart?createDatabaseIfNotExist=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true", "--spring.datasource.username=${DATA_USERNAME}", "--spring.datasource.password=${DATA_PASSWORD}"]