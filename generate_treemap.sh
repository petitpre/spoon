#!/bin/sh

# Generate cobertura report
mvn clean cobertura:cobertura
 
# treemap generator
java -cp target/test-classes TreeMap target/site/cobertura/coverage.xml  treemap/data.js

open treemap/index.html