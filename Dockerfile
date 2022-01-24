FROM openjdk:8

WORKDIR /workspace/

ADD ./target/pigeon.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "$@"]
