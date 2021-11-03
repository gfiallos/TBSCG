# TBSCG
NotesAPP

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
3. In the folder notesapp/src/deployment, the file docker-compose.yaml should be modified to include the image repository (by default /gfiallos).
4. For use DOCKER Swarm, In the folder notesapp/src/deployment, execute the following command: `./deploy.sh`
