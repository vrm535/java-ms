FROM docker.artifactory.sherwin.com/eclipse-temurin:17-jdk-alpine

COPY ./certificates/* /opt
RUN echo yes | keytool -importcert -alias swrootcert -keystore "$JAVA_HOME/lib/security/cacerts" -storepass changeit -file ./opt/sw-root.pem
RUN echo yes | keytool -importcert -alias swintcert -keystore "$JAVA_HOME/lib/security/cacerts" -storepass changeit -file ./opt/sw-int-ca.pem

WORKDIR /opt

ADD entrypoint.sh /
ENV PORT 8080
EXPOSE 8080

RUN chmod +x /entrypoint.sh
COPY target/*.jar /opt/app.jar

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v2.0.0/opentelemetry-javaagent.jar /opt/opentelemetry-javaagent.jar
ENV JAVA_TOOL_OPTIONS "-javaagent:/opt/opentelemetry-javaagent.jar"

ENTRYPOINT ["/bin/sh", "-c" , "/entrypoint.sh"]
