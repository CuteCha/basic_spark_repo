package com.scala.cxq

import scala.io.Source

/**
  * Created by cxq on 2018/8/20.
  */
object ReadFileLine {
  def main(args: Array[String]): Unit = {
    val fileName = "./output/tmp/data_00.txt"
    val ret = readFileLine(fileName)
    println(ret)
  }

  def readFileLine(filePath: String) = {
    var s = 0L
    for (line <- Source.fromFile(filePath).getLines()) {
      s += line.split("\t").map(x => x.toLong).sum
    }
    s
  }

}
