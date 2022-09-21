FROM gradle:jdk17 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle build

FROM openjdk:17-jdk
COPY --from=gradleimage /home/gradle/source/build/libs/mymovies.jar /app/
WORKDIR /app
ENTRYPOINT ["java", "-jar", "mymovies.jar"]