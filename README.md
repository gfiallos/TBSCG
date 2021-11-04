# TBSCG
NotesAPP

As solution to the coding challenge, I have created a simple web application that allows users to create notes, and attach tags to them. In the OpenAPI you have 2 method groups 1 dedicated to manage tags and another to manage notes. 

I have used Java, Spring Boot and PostgreSQL to solve the problem.

## Tools

| Tool | Use |
|------|-----|
|Image repository |Hub.docker.com|
|Sources Repository |GitHub|
|Java|Programming Language|
|SpringBoot|Programming Framework|
|Java Project Manager |Apache Maven|
|OpenAPI|REST Documentation|
|Container Orquestration|Docker Swarm / Docker|
|Database|PostgreSQL|
|Deployment Descriptor|Docker Compose|

## Content

|Path|Description|
|----|-----------|
|notesapp|Maven Project|
|notesapp/src/deployment|Deployment Descriptor|

## Build process

1. Clone the repository `https://github.com/gfiallos/TBSCG.git` or unzip the archive `TBSCG.zip`
2. In the folder notesapp, execute the following command:
`mvn clean install -Dmaven.test.skip -Ddocker.image.prefix=gfiallos/ spring-boot:build-image`, this step should be included in the automation script. 
* Consider that `gfiallos/` should be replaced by your ECS Repository (XXXXXX.dkr.ecr.us-east-1.amazonaws.com/).
3. Sing in into your repository (hub.docker.com) and execute the following command: `docker push gfiallos/notesapp:1.0`

## Run process

Here we have several different ways to run the application.

### Docker Swarm
1. In the folder notesapp/src/deployment, the file docker-compose.yaml should be modified to include the image repository (by default /gfiallos).
2. For use DOCKER Swarm, In the folder notesapp/src/deployment, execute the following command: `./deploy.sh`

### EC2
1. Prepare a EC2 server with the following configuration:
* Instance Type: m4.large.
* Operating System: Amazon Linux 2.
* Use the file `ec2-userdata.sh` as userdata.
* Open the port number 8881.

### Cloudformation.
1. Config a AWS Client.
2. Go to the content directory generated from unzip the archive `TBSCG.zip`
3. Prepare a Stack with the following command: `aws cloudformation create-stack --stack-name --region us-east-1 notesapp --template-body file://./aws.yml`

## Testing
1. Get the Remote IP from the EC2 server.
2. Open the port number 8881. `http://{ip}:8881`
3. There is the OpenAPI for the REST API to test the application.
4. To generate a Rest Client use the following url: `http://52.207.251.159:8881/api-docs`

## Configuration
1. In the file docker-compose.yml, the following configuration should be changed:
   **SPRING_DATASOURCE_URL: jdbc:postgresql://dbserver/notesappdb** If you want to use other PostgreSQL database.