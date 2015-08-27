#!/bin/sh

function test {
    "$@"
    local status=$?
    if [ $status -ne 0 ]; then
        echo "error with $1" >&2
        exit;
    fi
    return $status
}

test cd ..


echo "create baseline"
# generate baseline
test mvn assembly:assembly -Dmaven.test.skip=true >> log.txt
echo "run 1"
test java  -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:FlightRecorderOptions=defaultrecording=true,disk=true,maxage=5m,dumponexit=true,dumponexitpath=baseline.jfr -cp target/spoon-core-4.3.0-SNAPSHOT-jar-with-dependencies.jar spoon.Launcher -i src/main/java/
echo "run 2"
test java  -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:FlightRecorderOptions=defaultrecording=true,disk=true,maxage=5m,dumponexit=true,dumponexitpath=baseline2.jfr -cp target/spoon-core-4.3.0-SNAPSHOT-jar-with-dependencies.jar spoon.Launcher -i src/main/java/

echo "compare :"
test java -cp diversify/Diffcompule-1.0-SNAPSHOT-jar-with-dependencies.jar Main baseline.jfr baseline2.jfr

echo "build diversified"
test mvn assembly:assembly -Pdiversify-all -Dmaven.test.skip=true >> log.txt
echo "run 1"
test java  -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:FlightRecorderOptions=defaultrecording=true,disk=true,maxage=5m,dumponexit=true,dumponexitpath=diversify.jfr -cp target/spoon-core-4.3.0-SNAPSHOT-jar-with-dependencies.jar spoon.Launcher -i src/main/java/
echo "compare :"
test java -cp diversify/Diffcompule-1.0-SNAPSHOT-jar-with-dependencies.jar Main baseline.jfr diversify.jfr