from openjdk:11
ADD /build/libs/messenger.jar backend.jar
ENTRYPOINT ["java" , "-jar", "backend.jar"]