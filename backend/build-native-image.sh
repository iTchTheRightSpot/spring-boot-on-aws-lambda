#bin/sh

# remove older versions
rm lambda.zip

# build the native image via Docker
docker build -t server-side-lambda . --progress=plain

# extract the resulting native image
docker run --rm --entrypoint cat server-side-lambda lambda.zip > lambda.zip