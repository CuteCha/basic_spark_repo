import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by cxq on 2018/4/10.
  */
object wuzong {
  def main(args: Array[String]) {
    val conf = new SparkConf()
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)

    val rdd = sc.textFile("info.txt").map { x =>
      val fields = x.split("\t")
      val imei = fields(0)
      val phTrace = fields(1).split("|")
      val position = phTrace(1)
      val trace = phTrace(2)
      val reTr = reviseTrace(trace)
      (imei, position, reTr)
    }

    val rdd2 = rdd.flatMap(x => x._3.map(y => (x._1, x._2, y)))

    rdd2.saveAsTextFile("output")

    sc.stop()
//    val x="000000639158864\ta09347919b4b|0#0#0|32021132,85394549,1521957253,241,se43fa200bb56&-36,#0,0,4,241,se43fa200bb56&-35,#0,0,5,241,se43fa200bb56&-36,#-422,-972,1473,150,,#-740,-532,4518,150,s586ab1be8db0&-55,#-720,-639,8560,150,,#-720,-639,8561,150,,#-1346,221,10703,150,cb83a0856af40&Tenda_56AF40&-44,#-1568,448,10943,75,,#-1360,253,15727,150,sb0958e41aeba&-63,#-1190,116,19746,150,ce43fa200999e&CMCC_00999C&-67,#-1018,15,20144,150,sb83a0856af40&-57&b0958e41af92&-57,#|\t460030877263903||\t542400||nnn0003n"
//    val x= "32021132,85394549,1521957253,241,se43fa200bb56&-36,#0,0,4,241,se43fa200bb56&-35,#0,0,5,241,se43fa200bb56&-36,#-422,-972,1473,150,,#-740,-532,4518,150,s586ab1be8db0&-55,#-720,-639,8560,150,,#-720,-639,8561,150,,#-1346,221,10703,150,cb83a0856af40&Tenda_56AF40&-44,#-1568,448,10943,75,,#-1360,253,15727,150,sb0958e41aeba&-63,#-1190,116,19746,150,ce43fa200999e&CMCC_00999C&-67,#-1018,15,20144,150,sb83a0856af40&-57&b0958e41af92&-57,#"
//
//    val y=reviseTrace(x)
//    println(y.length)
//    for(e<-y){println(e)}

  }

  def reviseTrace(trace: String): Array[String] = {
    val sigTrs = trace.split("#")
    println(sigTrs.length)
    val sigTr0 = sigTrs(0).split(",", 6)
    val lng = sigTr0(0).toInt
    val lat = sigTr0(1).toInt
    val t = sigTr0(2).toInt
    sigTrs.update(0, sigTr0.mkString("\t"))
    for (i <- 1 until sigTrs.length) {
      var sigTrTmp = sigTrs(i).split(",", 6)
      var lngTmp = sigTrTmp(0).toInt + lng
      var latTmp = sigTrTmp(1).toInt + lat
      var tTmp = sigTrTmp(2).toInt + t
      sigTrTmp.update(0, lngTmp.toString)
      sigTrTmp.update(1, latTmp.toString)
      sigTrTmp.update(2, tTmp.toString)
      sigTrs.update(i, sigTrTmp.mkString("\t"))
    }
    sigTrs
  }

}
