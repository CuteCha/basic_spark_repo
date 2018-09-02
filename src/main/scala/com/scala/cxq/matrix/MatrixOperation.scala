package com.scala.cxq.matrix

import org.apache.log4j.PropertyConfigurator
import org.nd4j.linalg.factory.Nd4j

/**
  * Created by cxq on 2018/8/31.
  */
object MatrixOperation {
  def main(args: Array[String]) {
    //    PropertyConfigurator.configure("./conf/log4j.properties")
    val arr = Nd4j.create(4)
    println(arr)
    val vec = Nd4j.create(Array(3d, 5d))
    println(vec)
    println(vec.mul(2))
    println(vec.mul(vec))
    println(vec.shape())
    println(vec.transpose())
    println(vec.add(vec))
    println(vec.muli(vec))
    val vec0 = Nd4j.zeros(10)
    println(vec0)
    val a=Nd4j.create(Array(Array(1d,2d, 3d), Array(4d, 5d, 6d)))
    val b=Nd4j.create(Array(Array(10d,20d), Array(30d, 40d), Array(50d, 60d)))
    println(a.mmul(b))
  }

}
