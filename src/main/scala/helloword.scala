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
    for (s <- a(0))
      println(s)
    println(a(0))
  }

}
