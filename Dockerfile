
FROM adoptopenjdk:16-jre-hotspot as builder
WORKDIR application
COPY target/guests-SNAPSHOT.jar guests.jar
RUN java -Djarmode=layertools -jar guests.jar extract

FROM adoptopenjdk:16-jre-hotspot
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", \
  "org.springframework.boot.loader.JarLauncher"]

