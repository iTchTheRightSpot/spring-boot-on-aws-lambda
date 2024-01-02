# graalvm image as per docs https://github.com/graalvm/graalvm-demos/blob/master/spring-native-image/Dockerfiles/Dockerfile
# using GraalVM-ce for JDK 17
# FROM container-registry.oracle.com/graalvm/native-image:21-ol8 AS builder
FROM ghcr.io/graalvm/graalvm-ce:ol9-java17-22 AS builder

# set the working directory to /home/app
WORKDIR /build

# copy the source code into the image for building
COPY . /build

# build
RUN ./mvnw --no-transfer-progress native:compile -Pnative -DskipTests

# as per aws example https://aws.amazon.com/blogs/compute/introducing-the-amazon-linux-2023-runtime-for-aws-lambda/
FROM public.ecr.aws/lambda/provided:al2023

# copy byte code into server directory
COPY --from=builder /build/target/server ./server

# lambda runs byte code by just running directory
ENTRYPOINT ["./server"]
