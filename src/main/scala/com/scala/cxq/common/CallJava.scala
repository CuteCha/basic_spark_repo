package com.scala.cxq.common

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.slf4j.LoggerFactory

/**
  * Created by cxq on 2019/1/4.
  */
object CallJava {
  val LOGGER = LoggerFactory.getLogger(CallJava.getClass)

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)

    val formatPara = ParamParse.ParseParameters(args)
    val inputDataPath = formatPara("inpuDataPath")
    val outputPath = formatPara("outputPath")

    LOGGER.info("input: %s, output: %s".format(inputDataPath, outputPath))
    println("input: %s, output: %s".format(inputDataPath, outputPath))

    //    val rawData: RDD[String] = sc.textFile(inputDataPath)
  }
}
