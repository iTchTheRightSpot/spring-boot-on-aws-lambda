#bin/sh

#docker build -t server-graalvm21:native-web .
#
#docker run -it -v `pwd`:`pwd` -w `pwd` -v ~/.m2:/root/.m2 server-graalvm21:native-web ./mvnw clean -Pnative package -DskipTests

# remove older versions
mvn clean

# build the native image via Docker
docker build -t server-graalvm21:native-web . --progress=plain

# extract the resulting native zip
docker run --rm --entrypoint cat server-graalvm21:native-web target/server-0.0.1-SNAPSHOT-native-zip.zip > server-0.0.1-SNAPSHOT-native-zip.zip