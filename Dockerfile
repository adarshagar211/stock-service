FROM maven:3-jdk-11
WORKDIR /stocks-api
COPY . .
RUN mvn clean package
EXPOSE 8080
CMD mvn spring-boot:run