import com.scala.cxq.common.ParamParse
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by cxq on 2018/10/17.
  */
object SomeDebug {
  def main(args: Array[String]) {
    testMapPartions(args)
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

    val input = sc.parallelize(1 to 9, 3)
    //    val mapResult = input.map(x => (x, x * 2))
    //    mapResult.repartition(1).saveAsTextFile(outputPath)

    val result = input.mapPartitions(partion => partion.map(x => (x, x * 2)))
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
}
