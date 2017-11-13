#!/usr/bin/env bash

SparkBin=/Users/cxq/spark-1.6.3-bin-hadoop2.6/bin
JAR_PATH=./build/libs/lucene_baike-1.0.jar
#jar_path=./build/libs/lucene_baike-1.0.jar
#class_name=HelloWorld
#param="-----------------------php"
#java -classpath ${jar_path} ${class_name} ${param}
#
#scala -classpath ${jar_path} helloword

inpuDataPath=tempData
outputPath=output
#feactureClassCalcu=B1,B2

rm -rf ${outputPath}

${SparkBin}/spark-submit \
  --master local[*] \
  --class genTrainData \
  --driver-memory 1G \
  --executor-memory 1G \
  --executor-cores 2 \
  ${JAR_PATH} \
  inpuDataPath=${inpuDataPath} \
  outputPath=${outputPath}