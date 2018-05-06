#### Below is docker build results.

gsskhan@gsskhan-Inspiron-3542:/media/gsskhan/SHAHBAZ/workspace_eclipse/sample$ docker -v
Docker version 18.03.0-ce, build 0520e24

gsskhan@gsskhan-Inspiron-3542:/media/gsskhan/SHAHBAZ/workspace_eclipse/sample$ docker build -f Dockerfile -t sample-java .
Sending build context to Docker daemon  36.35kB
Step 1/3 : FROM openjdk:8
8: Pulling from library/openjdk
c73ab1c6897b: Pull complete 
1ab373b3deae: Pull complete 
b542772b4177: Pull complete 
57c8de432dbe: Pull complete 
da44f64ae999: Pull complete 
0bbc7b377a91: Pull complete 
1b6c70b3786f: Pull complete 
d9bbcf733166: Pull complete 
b1d3e8de8ec6: Pull complete 
Digest: sha256:9df7c8e6c89d17abbc1791509e71a65368719b0da730d9cc87940a9b1b6dd531
Status: Downloaded newer image for openjdk:8
 ---> 891c9734d5ab
Step 2/3 : ADD target/sample.jar sample.jar
 ---> a01083e010fe
Step 3/3 : ENTRYPOINT ["java" "-cp" "sample.jar" "com.sample.HelloWorld"]
 ---> Running in 07aca3e50600
Removing intermediate container 07aca3e50600
 ---> c63a94b066bc
Successfully built c63a94b066bc
Successfully tagged sample-java:latest

gsskhan@gsskhan-Inspiron-3542:/media/gsskhan/SHAHBAZ/workspace_eclipse/sample$


#### Below is docker run results.

gsskhan@gsskhan-Inspiron-3542:/media/gsskhan/SHAHBAZ/workspace_eclipse/sample$ docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
sample-java         latest              94951383a8b0        2 seconds ago       726MB
openjdk             8                   891c9734d5ab        2 weeks ago         726MB
hello-world         latest              f2a91732366c        4 months ago        1.85kB

gsskhan@gsskhan-Inspiron-3542:/media/gsskhan/SHAHBAZ/workspace_eclipse/sample$ docker run sample-java
Hello Docker!!!


## Below is docker push results ########

gsskhan@gsskhan-Inspiron-3542:/media/gsskhan/SHAHBAZ/workspace_eclipse/sample$ docker login
Login with your Docker ID to push and pull images from Docker Hub. If you don't have a Docker ID, head over to https://hub.docker.com to create one.
Username: gsskhan
Password: 
Login Succeeded

gsskhan@gsskhan-Inspiron-3542:/media/gsskhan/SHAHBAZ/workspace_eclipse/sample$ docker tag sample-java:latest gsskhan/docker-repo:latest

gsskhan@gsskhan-Inspiron-3542:/media/gsskhan/SHAHBAZ/workspace_eclipse/sample$ docker images
REPOSITORY            TAG                 IMAGE ID            CREATED             SIZE
gsskhan/docker-repo   latest              94951383a8b0        2 minutes ago       726MB
sample-java           latest              94951383a8b0        2 minutes ago       726MB
openjdk               8                   891c9734d5ab        2 weeks ago         726MB
hello-world           latest              f2a91732366c        4 months ago        1.85kB

gsskhan@gsskhan-Inspiron-3542:/media/gsskhan/SHAHBAZ/workspace_eclipse/sample$ docker push gsskhan/docker-repo:latest
The push refers to repository [docker.io/gsskhan/docker-repo]
b12488f66cb5: Pushed 
46da922b5615: Mounted from library/openjdk 
21087b7b28f7: Mounted from library/openjdk 
7e912d203101: Mounted from library/openjdk 
638babc3b650: Mounted from library/openjdk 
0ef6a87794b5: Mounted from library/openjdk 
20c527f217db: Mounted from library/openjdk 
61c06e07759a: Mounted from library/openjdk 
bcbe43405751: Mounted from library/openjdk 
e1df5dc88d2c: Mounted from library/openjdk 
latest: digest: sha256:21519e18df816422a23081b9f00eac84037579507d62c1008ae9e06f7f7e4473 size: 2419

gsskhan@gsskhan-Inspiron-3542:/media/gsskhan/SHAHBAZ/workspace_eclipse/sample$ 


