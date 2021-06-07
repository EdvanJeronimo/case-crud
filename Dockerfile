FROM openjdk:11
LABEL org.opencontainers.image.authors="Edvan Jeronimo"
EXPOSE 8888
ADD target/case-crud-backend.jar case-crud-backend.jar
ENV aws.service-endpoint="dynamodb.us-east-1.amazonaws.com"
ENV aws.region="us-east-1"
ENV aws.access.key="AKIA5ITUU4LMDDQGLU5Q"
ENV aws.access.pwd="2vEnchge/YU9fhHfRITBeZ8iUCDDQF5RVwrgiEwy"
ENTRYPOINT ["java", "-jar","/case-crud-backend.jar"]