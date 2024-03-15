FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD ./target/hack-grupo-01-0.0.1-SNAPSHOT.jar hack-grupo-01.jar
ENTRYPOINT ["java","-jar","/hack-grupo-01.jar"]
