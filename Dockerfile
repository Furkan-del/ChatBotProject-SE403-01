FROM adoptopenjdk/openjdk16
EXPOSE 8080
ARG JAR_FILE=target/ChatBot-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} ChatbotApplication.jar
ENTRYPOINT ["java","-jar","/ChatbotApplication.jar"]