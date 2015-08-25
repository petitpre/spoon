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
test rm -rf generated

echo "create baseline"
# generate baseline
test mvn clean compile -Dmaven.test.skip=true >> log.txt
test mkdir generated
test cp -r target/classes generated;

processors=(
    "none"
    
    "fr.inria.diversify.InvertIfProcessor"
    "fr.inria.diversify.VariableDeclaration"
    "fr.inria.diversify.FantomProcessor"

    "cohen.process.EmptyMethod"
    "cohen.process.EquivalenceInstr"
#    "cohen.process.IfEquivalence"
#    "cohen.process.MethodTampon"
#    "cohen.process.ReplaceVarDeclarationsProcessor"
    "cohen.process.ReverseIf"
    "cohen.process.ThreadCreationProcessor"
)

for processor in "${processors[@]}"
do
    echo $processor
    # generate artifact to compare
    test mvn clean compile -Dmaven.test.skip=true -Pdiversify -Dprocessor=$processor >> log.txt

    # compute similarity
    test java -jar diversify/Diffcompule-1.0-SNAPSHOT-jar-with-dependencies.jar generated/classes  target/classes
done
