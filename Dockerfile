FROM eclipse-temurin:17-jdk AS build

WORKDIR /src
COPY . /src
RUN ./gradlew build

FROM eclipse-temurin:17-jre
EXPOSE 8080
COPY  --from=build /src/build/libs/*SNAPSHOT.jar golang.jar

ENTRYPOINT ["java", "-jar", "golang.jar"]