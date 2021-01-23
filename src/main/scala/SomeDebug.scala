import com.scala.cxq.common.ParamParse
import com.scala.cxq.utils.CustomIterator
import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.mutable.ListBuffer

/**
 * Created by cxq on 2018/10/17.
 */
object SomeDebug {
  def main(args: Array[String]) {
    app()
  }

  def testMapPartions(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)
    val formatPara = ParamParse.ParseParameters(args)
    val outputPath = formatPara("outputPath")
    //    val input = sc.parallelize(List(1, 2, 3, 4, 5, 6), 2)
    //    val result = input.mapPartitions(partition => Iterator(sumOfEveryPartition(partition)))
    //    result.repartition(1).saveAsTextFile(outputPath)

    val input = sc.parallelize(1 to 20, 3)
    //    val mapResult = input.map(x => (x, x * 2))
    //    mapResult.repartition(1).saveAsTextFile(outputPath)

    //    val result = input.mapPartitions(partion => partion.map(x => (x, x * 2)))
    val result = input.mapPartitions(v => new CustomIterator(v))
    result.repartition(1).saveAsTextFile(outputPath)

    sc.stop()

  }

  def sumOfEveryPartition(input: Iterator[Int]): Int = input.sum


  def testTryCatch(): Unit = {
    try {
      val f = 3 / 0
      println(f)
    } catch {
      case e: Exception => {
        println("Exception")
      }
    } finally {
      println("finish")
    }
  }

  def isContain(sess: List[(String, String)]): Boolean = {
    val len = sess.length - 1
    for (idx <- sess.indices) {
      println(idx)
      if (idx < len && sess(idx)._2.equals("0") && sess(idx + 1)._2.equals("1")) {
        true
      }
    }

    false
  }

  def isContain2(sess: List[(String, String)]): Boolean = {
    val L = sess.length
    val len = L - 1
    var ret = false
    var idx = 0
    while (idx < len) {
      println(idx)
      if (idx < len && sess(idx)._2.equals("0") && sess(idx + 1)._2.equals("1")) {
        ret = true
        idx = L
      }
      idx = idx + 1
    }

    ret
  }

  def getPvSess(sess: List[(String, String)]): ListBuffer[(String, String)] = {
    var ret = ListBuffer[(String, String)]()
    val L = sess.length
    val len = L - 1
    var idx = 0
    while (idx < len) {
      println(idx)
      if (idx < len && sess(idx)._2.equals("0") && sess(idx + 1)._2.equals("1")) {
        ret += sess(idx)
      }
      idx = idx + 1
    }

    ret
  }

  def app(): Unit = {
    val res = getPvSess(List(("a", "0"), ("b", "1"), ("c", "0")))
    println(res)
    println(res.toList)
  }
}
