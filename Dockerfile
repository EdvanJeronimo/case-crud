FROM openjdk:11
LABEL org.opencontainers.image.authors="Edvan Jeronimo"
EXPOSE 8080
ADD target/case-crud-backend.jar case-crud-backend.jar
ENV AWS_SERVICE_ENDPOINT="dynamodb.us-east-1.amazonaws.com"
ENV AWS_REGION="us-east-1"
ENV AWS_KEY="AKIA5ITUU4LML6OAOGGS"
ENV AWS_SECRET="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"
ENTRYPOINT ["java", "-jar","/case-crud-backend.jar"]