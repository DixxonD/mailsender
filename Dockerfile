FROM maven:adoptopenjdk as maven
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn dependency:go-offline -B
RUN mvn package

HEALTHCHECK NONE

FROM adoptopenjdk/openjdk11 as openjdk11

RUN groupadd -r mailsender && useradd -r -m -d /home/mailsender -g mailsender mailsender
USER mailsender:mailsender

WORKDIR /app
COPY --from=maven target/*.jar ./app.jar

CMD ["java", "-jar", "./app.jar"]