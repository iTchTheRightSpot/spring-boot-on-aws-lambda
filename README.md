# Deploying Spring Boot Application as Native Executable on AWS Lambda.

## Overview
This project demonstrates deploying RESTful Spring Boot 3.2.2 application on
AWS Lambda using two methods: deploying as a zip file and deploying as a docker
image. It is based on the AWS sample
[repository](https://github.com/aws/serverless-java-container/tree/main/samples/springboot3/pet-store-native).

For both types of deployment I manually uploaded. The zip file deployment,
the application is packaged locally. For Docker image deployment, a
GitHub Actions workflow is used to deploy to Amazon ECR (Elastic Container Registry).

The application is compiled to a native executable using GraalVM.

## Dependencies
1. Java 21 (runtime)
2. Lombok

## Docker Image Deployment
To build the Docker image, execute `./build-native-ecr.sh` in your terminal.
This script generates the docker image `server-graalvm21` tagged as `native-web`.
You can view details about the image by running docker image ls in your CLI.

## Zip File Deployment
To build a zip file for deployment, run `./build-native-zip.sh` in your terminal.
This script produces a zip file named `server-0.0.1-SNAPSHOT-native-zip.zip` in
the root of project.
