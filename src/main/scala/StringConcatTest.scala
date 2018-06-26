/**
  * Created by cxq on 2018/6/25.
  */
object StringConcatTest {

  def timer[A](codeBlock: => A): Unit = {
    val startTime = System.nanoTime()
    for (i <- 1 to 10000) {
      val result = codeBlock
    }
    val timeDiff = System.nanoTime() - startTime
    println(s"Time: ${timeDiff / 10000}")
  }

  def main(args: Array[String]): Unit = {
    val s1 = "hello"
    val s2 = "world"

    timer {
      s1 + " " + s2
    }
    timer {
      s1 ++ " " ++ s2
    }
    timer {
      s"${s1} ${s2}"
    }
    timer{
      s1.concat(" ").concat(s2)
    }
    println(s1.concat(s2))
  }

}
