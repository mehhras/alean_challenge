FROM gradle:8.5.0-jdk21 AS build
LABEL maintainer="yasinorouskhani@gmail.com"

WORKDIR /app

COPY --chown=gradle:gradle build.gradle .
COPY --chown=gradle:gradle settings.gradle .
COPY --chown=gradle:gradle src src

RUN gradle clean
RUN gradle build  --info --stacktrace

FROM openjdk:23-jdk-slim
EXPOSE 8081

COPY --from=build /app/build/libs /app/libs
WORKDIR /app/libs

ENTRYPOINT java -XshowSettings:system -XX:MaxRAMPercentage=85 \
                -jar pokemon-1.0-SNAPSHOT.jar