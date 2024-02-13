#bin/sh

# Build the native image via Docker
docker build -t server-graalvm21:native-web .

# Extract the resulting zip file
docker run --rm --entrypoint cat server-graalvm21:native-web server-0.0.1-SNAPSHOT-native-zip.zip > server-0.0.1-SNAPSHOT-native-zip.zip