FROM openjdk:11
LABEL maintainer="aniruddh-pyati"
ADD target/springmvcboot-0.0.1-SNAPSHOT.jar blog-app.jar
ENTRYPOINT [ "java","-jar","blog-app.jar" ]
