#!/usr/bin/env bash

SparkBin=/Users/cxq/spark-1.6.3-bin-hadoop2.6/bin
JAR_PATH=build/libs/basic_spark_repo-1.0.jar
#jar_path=./build/libs/spark_project-1.0.jar
#class_name=HelloWorld
#param="-----------------------php"
#java -classpath ${jar_path} ${class_name} ${param}
#
#scala -classpath ${jar_path} helloword
#jar -tf spark_project-1.0.jar | grep EsCon

#inpuDataPath=tempData
outputPath=output
#feactureClassCalcu=B1,B2

rm -rf ${outputPath}

${SparkBin}/spark-submit \
  --master local[*] \
  --class SomeDebug \
  --driver-memory 1G \
  --executor-memory 1G \
  --executor-cores 2 \
  ${JAR_PATH} \
  outputPath=${outputPath}


#spark-submit \
#    --cluster c3prc-hadoop-spark2.1 \
#    --conf spark.yarn.job.owners=luotao \
#    --class com.xiaomi.chatbot.data.process.baiduzhidao.DocSimProcessV4 \
#    --master yarn-cluster \
#    --queue production.cloud_group.qabot \
#    --driver-memory 12g \
#    --executor-cores 2 \
#    --executor-memory 12g \
#    --conf "spark.executor.extraJavaOptions=-XX:MaxDirectMemorySize=2048m -XX:MaxPermSize=256m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./spark-executor-heapdump.hprof -XX:OnOutOfMemoryError='./oom_script.sh %p'" --conf spark.driver.maxResultSize=6g \
#    --conf spark.dynamicAllocation.enabled=true \
#    --conf spark.dynamicAllocation.initialExecutors=1 \
#    --conf spark.dynamicAllocation.maxExecutors=300  \
#    --conf spark.default.parallelism=500 \
#    ./data-process-1.0-SNAPSHOT.jar \
#    --sim_input /user/s_qabot/crawler_data/baidu_zhidao/extracted/20180310 \
#    --tfidf_input /user/s_qabot/crawler_data/baidu_zhidao/tfidf_vector/20180320 \
#    --output /user/s_qabot/crawler_data/baidu_zhidao/simQ_modify/20180320 \
#    --debug_output  /user/s_qabot/crawler_data/baidu_zhidao/simQ_debug/20180320
#    #--executor-cores 2 --num-executors 10 --conf spark.dynamicAllocation.enabled=false