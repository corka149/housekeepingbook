FROM openjdk:9-jre-slim 
ENV PORT 9010
EXPOSE 9010
COPY . /opt
WORKDIR /opt/target

CMD ["/bin/bash", "-c", "find -type f -name 'housekeepingbook*.jar' | xargs java -jar"]
