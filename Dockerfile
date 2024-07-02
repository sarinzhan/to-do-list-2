FROM amazoncorretto:17


COPY target/To-Do-list-1.jar To-Do-list.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/To-Do-list.jar"]