/**
  * Created by cxq on 2018/10/17.
  */
object SomeDebug {
  def main(args: Array[String]) {
    try {
      val f = 3 / 0
      println(f)
    } catch {
      case e: Exception => {
        println("Exception")
      }
    }finally {
      println("finish")
    }
  }
}
