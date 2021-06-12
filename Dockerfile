FROM openjdk:11
LABEL org.opencontainers.image.authors="Edvan Jeronimo"
EXPOSE 8092
ADD target/case-crud-backend.jar case-crud-backend.jar
ENV AWS_SERVICE_ENDPOINT="dynamodb.us-east-1.amazonaws.com"
ENV AWS_REGION="us-east-1"
ENV AWS_KEY=${AWS_KEY}
ENV AWS_SECRET=${AWS_SECRET}
ENTRYPOINT ["java", "-jar","/case-crud-backend.jar"]