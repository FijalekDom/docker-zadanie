FROM java:8
COPY ./Program.java ./
COPY ./mysql-connector-java-8.0.13.jar /mysql-connector-java-8.0.13.jar
RUN javac Program.java
CMD ["java","-classpath","mysql-connector-java-8.0.13.jar:.","Program"]
