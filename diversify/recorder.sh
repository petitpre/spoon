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

function record {
    i=$((i+1))
    test mvn clean assembly:assembly -Dmaven.test.skip=true $@ >> log.txt
    echo "run $i"
    test rm -rf spooned
    test java  -XX:+UnlockCommercialFeatures -XX:+FlightRecorder \
            -XX:FlightRecorderOptions=defaultrecording=true,disk=true,maxage=5m,dumponexit=true,dumponexitpath=$targetdir/record_$i.jfr \
            -cp target/spoon-core-4.3.0-DIVERSIFY-jar-with-dependencies.jar spoon.Launcher -i src/main/java/
}

test cd ..

targetdir="records_$(date +"%s")"
i=0

# clean output directory
rm -rf $targetdir
test mkdir $targetdir
echo "print to $targetdir"

# build 10 normal records
echo "baseline"
for j in `seq 1 10`
do
    record;
done

# build 5*2 record with spoon processor
processors=(
    "spoon"
    "fr.inria.diversify.EncryptLiteralProcessor"
    "fr.inria.diversify.InvertIfProcessor"
    "fr.inria.diversify.VariableDeclaration"
    "fr.inria.diversify.FantomProcessor"
    )
for processor in "${processors[@]}"
do
    echo "record with processor $processor"
    for j in `seq 1 2`;
    do
        record -Pdiversify -Dprocessor=$processor
    done
done

echo "record with all processors"
for j in `seq 1 10`
   do
        record -Pdiversify-all
   done