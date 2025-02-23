FROM eclipse-temurin:21-jdk-alpine
COPY target/broker-0.0.1-shaded.jar /broker.jar
CMD ["java", "-jar", "/broker.jar", "http://afq-service:9001/quotations?wsdl", "http://dgq-service:9002/quotations?wsdl", "http://gaq-service:9003/quotations?wsdl"]