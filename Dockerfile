FROM openjdk:11
WORKDIR /usr/application
COPY ./target/gittest-0.1.jar /usr/application
ENTRYPOINT java -cp gittest-0.1.jar gittest.jenkins.App