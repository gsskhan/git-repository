FROM openjdk:8
RUN apt-get clean
ADD target/sample.jar sample.jar
ENTRYPOINT ["java","-cp","sample.jar","com.sample.HelloWorld"]
RUN ls -lart /
RUN hostname
RUN java -version