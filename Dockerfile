FROM openjdk:20
LABEL authors="beastwall"
COPY target/portfolio.war portfolio.war
EXPOSE 1998:1998
ENTRYPOINT ["java", "-jar","portfolio.war"]