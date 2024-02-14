# Deploying a native executable Spring Boot application using GraalVM to AWS Lambda.

## Overview
This project demonstrates deploying RESTful Spring Boot 3.2.2 application on
AWS Lambda using two methods: deploying as a zip file and deploying as a docker
image. It is based on the AWS sample
[repository](https://github.com/aws/serverless-java-container/tree/main/samples/springboot3/pet-store-native).

Note for both ways of deployment, I manually uploaded the source code to
seperate lambda functions. For the zip file deployment, the application is packaged locally
using the script `./build-native-zip.sh` and for docker image deployment, GitHub Actions
workflow is used to deploy to Amazon ECR (Elastic Container Registry).

## Dependencies
1. Java 21 (runtime)
2. Lombok

## Prerequisite
If you would like to build and upload to lambda from your local system, Docker
needs to be installed.

## Docker Image Deployment
To deploy a docker image, execute `cd ./backend` and `./build-native-ecr.sh` in your terminal.
This script generates the docker image name `server-graalvm21` tagged as `native-web`.
You can view details about the image by running `docker image ls` in your CLI.

## Zip File Deployment
To deploy a zip file, execute `cd ./backend` and `./build-native-zip.sh` in your terminal.
This script produces a zip file named `server-0.0.1-SNAPSHOT-native-zip.zip` in the root of
project.
