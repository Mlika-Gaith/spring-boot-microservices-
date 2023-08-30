## BUILDER STAGE
# Base Image, stage name : builder (for ref later)
FROM eclipse-temurin:17.0.4.1_1-jre as builder
# Set the working directory to a dir named "extracted"
WORKDIR extracted
# Copy the jar files from the app's target dir
# into the container and renaims it to app.jar
ADD target/*.jar app.jar
# Extract layers of the jar file
RUN java -Djarmode=layertools -jar app.jar extract

# RUNTIME STAGE
# Base image
FROM eclipse-temurin:17.0.4.1_1-jre
# Set the working directory to a dir named "application"
WORKDIR application
# copy the content of the dependencies dir of the build satge
# into current dir
COPY --from=builder extracted/dependencies/ ./
# copy the content of the spring-boot-loader dir of the build satge
# into current dir
COPY --from=builder extracted/spring-boot-loader/ ./
# copy the content of snapshot-dependencies dir of the build satge
# into current dir
COPY --from=builder extracted/snapshot-dependencies/ ./
# copy the content of the application dir of the build satge
# into current dir
COPY --from=builder extracted/application/ ./
# Expose port 8080 of the container
EXPOSE 8080
# Lunch spring boot application
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]
