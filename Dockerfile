FROM openjdk:11
WORKDIR /usr/application
COPY ./target/gittest-0.1.jar /usr/application
EXPOSE 5000
ENTRYPOINT java -cp gittest-0.1.jar gittest.jenkins.HTTP2