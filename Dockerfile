FROM maven:3-jdk-8 as BUILD

COPY . /usr/src/app
RUN mvn --batch-mode -f /usr/src/app/pom.xml clean package

FROM openjdk:8-jre-slim
ENV PORT 9010
EXPOSE 9010
COPY --from=BUILD /usr/src/app/target /opt/target
WORKDIR /opt/target

CMD ["/bin/bash", "-c", "find -type f -name 'housekeepingbook*.jar' | xargs java -jar"]
