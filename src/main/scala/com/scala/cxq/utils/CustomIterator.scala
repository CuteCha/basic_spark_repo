package com.scala.cxq.utils

/**
  * Created by cxq on 2019/1/25.
  */
class CustomIterator(iter: Iterator[Int]) extends Iterator[Int] {
  def hasNext: Boolean = {
    iter.hasNext
  }

  def next: Int = {

    val cur = iter.next

    cur * 3
  }
}
