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

function compare {
    result=$( test java -jar diversify/Diffcompule-1.0-SNAPSHOT-jar-with-dependencies.jar $1 $2 )
    printf "$result\n"
}

test cd ..
test rm -rf generated

# generate baseline
test mvn clean compile -Dmaven.test.skip=true >> log.txt
test mkdir generated
test cp -r target/classes generated;


# compare reference build with another empty one
printf "none;"
test mvn clean compile -Dmaven.test.skip=true >> log.txt
compare generated/classes target/classes


processors=(
    # only apply spoon, without mutation processor
    "spoon"
    
    "fr.inria.diversify.EncryptLiteralProcessor"
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
    printf "$processor;"
    # generate artifact to compare
    test mvn clean compile -Dmaven.test.skip=true -Pdiversify -Dprocessor=$processor >> log.txt

    # compute similarity
    compare generated/classes target/classes
done
