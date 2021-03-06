FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/micronaut-demo*.jar micronaut-demo.jar
CMD java ${JAVA_OPTS} -jar micronaut-demo.jar