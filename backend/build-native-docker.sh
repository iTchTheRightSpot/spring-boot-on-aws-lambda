#bin/sh

# build the native image to deploy to ecr
docker build -t server-graalvm21:native-web -f Dockerfile.ecr . --progress=plain