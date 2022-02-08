FROM openjdk:17
COPY ./build/libs/wallet-0.0.1-SNAPSHOT.jar wallet-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","wallet-0.0.1-SNAPSHOT.jar"]