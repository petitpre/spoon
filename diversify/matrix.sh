#!/bin/sh

for i in `seq 1 30`; do
    for j in `seq 1 30`;  do
        result=$(java -cp Diffcompule-1.0-SNAPSHOT-jar-with-dependencies.jar Main $1/record_$i.jfr $1/record_$j.jfr)
        printf "$result;" >> $1/data.csv
    done
    printf "\n" >> $1/data.csv
done
