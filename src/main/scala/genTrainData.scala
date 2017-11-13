import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
  * Created by cxq on 2017/11/6.
  */
object genTrainData {
  def main(args: Array[String]) = {
    val conf = new SparkConf()
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)

    val formatPara = ParseParameters(args)
    val inputDataPath = formatPara("inpuDataPath")
    val outputPath = formatPara("outputPath")

    val rawData: RDD[String] = sc.textFile(inputDataPath)

    val qtGroupIdx = rawData.map(f => {
      val s = f.split(",")
      (s.head, s.last)
    }).reduceByKey((x, y) => x + "\t" + y).zipWithIndex()
      .map(f => (f._2.toInt, f._1._1 + "\u0001" + f._1._2))
      .persist(StorageLevel.DISK_ONLY)

    val maxId = qtGroupIdx.count().toInt

    val tmpRDD = qtGroupIdx.flatMap(f => {
      val data = genDiffNum(4, f._1, maxId)
      data.map(x => (x, f))
    }).leftOuterJoin(qtGroupIdx)
      .map(f => (f._2._1._2, f._2._2.mkString(",")))
      .reduceByKey((x, y) => x + "\u0002" + y)

    val trainData = tmpRDD.flatMap(f => {
      val s = f._1.split("\u0001")
      val q = s.head
      s.last.split("\t").map(x => (q + "\t" + x, f._2))
    }).map(f => {
      val t = f._2.split("\u0002").map(x => {
        val u = x.split("\u0001").last.split("\t")
        val i = Random.nextInt(u.length)
        u(i)
      })
      f._1 + "\t" + t.mkString("\t")
    })

    trainData.saveAsTextFile(outputPath)

    sc.stop()

  }


  def ParseParameters(params: Array[String]): mutable.HashMap[String, String] = {
    val inputParameter = new mutable.HashMap[String, String]

    params.par.foreach(each => {
      val part = each.split("=", 2)
      if (part.length == 2) {
        inputParameter(part(0)) = part(1)
      }
    })

    inputParameter

  }

  def genDiffNum(num: Int = 4, k: Int, maxRan: Int) = {
    val data: ArrayBuffer[Int] = ArrayBuffer.empty[Int]
    while (data.length < num) {
      val i = Random.nextInt(maxRan)
      if (i != k && !data.exists(s => (s == i))) {
        data += i
      }
    }
    data
  }
}
