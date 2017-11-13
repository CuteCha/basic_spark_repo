import scala.collection.mutable
import scala.io.Source
import java.net.URL


/**
  * Created by cxq on 2017/11/1.
  */


object getUrlTitle {
  def getTitle(url: String): String ={
    val text = Source.fromURL(url).getLines().mkString("\r\n")
    val startIndex = text.indexOf("<title>")+7
    val endIndex = text.indexOf("</title>")
    text.substring(startIndex,endIndex)
  }

  def getTitleWithTimeout(url: String, timeout: Int = 1000): String = {
    val conn = (new URL(url)).openConnection()
    conn.setConnectTimeout(timeout)
    conn.setReadTimeout(timeout)
    val stream = conn.getInputStream()
    val text = (scala.util.control.Exception.catching(classOf[Throwable]) opt Source.fromInputStream(stream).mkString) match {
      case Some(s: String) => s
      case _ => ""
    }
    stream.close()
    val startIndex = text.indexOf("<title>")+7
    val endIndex = text.indexOf("</title>")
    text.substring(startIndex,endIndex)
  }

  def showSome(x: Option[String]) = x match {
    case Some(s) => s
    case _ => ""
//    case None => "??"
  }

  def main(args: Array[String]) {
    val url = "http://blog.csdn.net/lein_wang/article/details/51689703"
    println(getTitle(url))
    println(getTitleWithTimeout(url))
//    val result = Source.fromURL(url)
//    val text = result.getLines().mkString("\r\n")
//    val startIndex = text.indexOf("<title>")+7
//    val endIndex = text.indexOf("</title>")
//    val title = text.substring(startIndex,endIndex)
//    val reg = """<title>(.*?)</title>""".r
//    val title0 = reg.findFirstMatchIn(text)
//    result.getLines().foreach( x=> println(x))
//    println(text)
//    println(title0.get)
//    println(title)

//    text.indexOf(title)
//    text.substring(7,39)
    //    println(result.mkString("\n").wait(400).toString)
    //    result.getLines().foreach(println())


    //    val queue = new mutable.Queue[String]()
    //    var depth = 0
    //    queue.enqueue("http://blog.csdn.net/w_j_w2010/article/details/49995177")
    //    while (queue.size > 0) {
    //      // 把队列中首个元素弹出
    //      val urlStr = queue.dequeue()
    //      val result = Source.fromURL(urlStr)
    //      val lines = result.getLines()
    //      val regex = """<title>(.*?)</title>""".r
    //      var string = ""
    //      for (s <- lines) {
    //        string += s
    //        string += "\r\n"
    //      }
    //      // 打印查看结果
    //      //      println(string)
    //
    //      val finalArray = regex.findAllIn(string)
    //      for (m <- finalArray)
    //        println(m)
    //    }
  }

}

