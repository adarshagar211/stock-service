FROM maven:3-jdk-11
WORKDIR /stocks-api
COPY . .
RUN mvn clean package
CMD mvn spring-boot:run