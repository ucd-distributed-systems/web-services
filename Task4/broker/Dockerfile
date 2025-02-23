FROM eclipse-temurin:21-jdk-alpine
COPY target/broker-0.0.1-shaded.jar /broker.jar
COPY config.yaml /config.yaml

CMD ["java", "-jar", "/broker.jar", "http://localhost:9003/quotations?wsdl"]