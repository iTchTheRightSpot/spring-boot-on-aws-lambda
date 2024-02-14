# About
Project explains the 2 ways of deploying to AWS Lambda via
zip file and a docker image. As far as uploading to lambda
function, because this project is to grasp deploying to lambda,
for a zipped file deployment, I packaged my application on my local
system but for deploying a docker image, I have a git actions
workflow to deploy ECR where I manually upload to lambda function.

As far as this project it is a simple RESTFUL Spring Boot application
complied to a native executable using GraalVM.

## Dependencies
1. Java 21
2. Spring Cloud Function
3. Spring Start Web
4. Spring Starter Test to validate http logic
5. Lombok

## Docker image
To build for a docker image, in your terminal `./build-native-ecr.sh`
this will produce a docker image.
![img.png](/img.png)

## Zip
To build a zip file for deployment, in your terminal run `./build-native-s3.sh`.
This will produce a zip `server-0.0.1-SNAPSHOT-native-zip.zip`.