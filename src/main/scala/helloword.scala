/**
  * Created by cxq on 2017/5/31.
  */
object helloword {
  def main(args: Array[String]): Unit = {
    println("hello world!~~scala~~")

    val a = new Array[Array[String]](3)
    a.update(0, Array("Runoob", "Baidu", "Google"))
    a.update(1, Array("A", "B", "C"))
    a.update(2, Array("D", "E", "F"))
    for (s <- a(0)) {
      println(s)
    }
    println(a(0))

    val x = "32021132,85394549,1521957253,241,se43fa200bb56&-36,"
    val y = x.split(",", 6)
    for (yy <- y) {
      println(yy)
    }
    println(y.length)

    val z = y.map(f => ("x", f))
    for (i <- 0 to (z.length - 1)) {
      println(i + ":" + z(i)._1 + "\t" + z(i)._2)
    }

    val sites = Map("runoob" -> "www.runoob.com", "google" -> "www.google.com")
    println(sites.get("runoob"))
    println(show(sites.get("runoob")))
    println(sites.get("r"))
    println(show(sites.get("r")))

    testFlatMap()
    testMap()


  }

  def testFlatMap(): Unit ={
    val li = List(1,2,3)
    val res = li.flatMap(x => x match {
      case 3 => List('a','b')
      case _ => List(x*2)
    })
    println(res)
  }

  def testMap(): Unit = {
    val li = List(1,2,3)
    val res = li.map(x => x match {
      case 3 => List('a','b')
      case _ => x*2
    })
    println(res)
  }

  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }

}
