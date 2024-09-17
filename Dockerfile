FROM openjdk:20
LABEL authors="beastwall"
COPY target/portfolio.war portfolio.war
EXPOSE 2024:2024
ENTRYPOINT ["java", "-jar","portfolio.war"]