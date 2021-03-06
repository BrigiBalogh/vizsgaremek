FROM adoptopenjdk:16-jre-hotspot as builder
WORKDIR application
COPY target/guests-0.0.1-SNAPSHOT.jar guests.jar
RUN java -Djarmode=layertools -jar guests.jar extract

FROM adoptopenjdk:16-jre-hotspot
WORKDIR application
#RUN apt update \
    #&& apt-get install wget \
   # && apt-get install -y netcat \
   # && wget https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh \
   # && chmod +x ./wait-for-it.sh
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]



