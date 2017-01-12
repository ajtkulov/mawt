package mawt

import mawt.MinimumAverageWaitingTime.{handle, read}

/**
  * Order
  *
  * @param time start order time
  * @param last order duration
  */
case class Order(time: Long, last: Long) extends Ordered[Order] {
  override def compare(that: Order): Int = -(last compare that.last)
}

/**
  * MinimumAverageWaitingTime solution
  */
object MinimumAverageWaitingTime {
  def read(fileName: String): Seq[Order] = {
    val lines = scala.io.Source.fromFile(fileName).getLines().toList
    val size = lines.head.toInt
    lines.slice(1, size + 1).map(line => {
      val split = line.split(" ")

      Order(split(0).toInt, split(1).toInt)
    })
  }

  def handle(values: Seq[Order]): Long = {
    require(values.nonEmpty)

    val queue = new scala.collection.mutable.PriorityQueue[Order]()

    val sortedByStartTime: List[(Long, Seq[Order])] = values.groupBy(_.time).toList.sortBy(x => x._1)

    queue.enqueue(sortedByStartTime.head._2: _*)
    var res = 0L
    var curTime = sortedByStartTime.head._1

    def dequeue(): Unit = {
      val order = queue.dequeue()
      curTime += order.last
      res += curTime - order.time
    }

    for (item <- sortedByStartTime.drop(1)) {
      val (startSession, set): (Long, Seq[Order]) = item

      while (queue.nonEmpty && curTime < startSession) {
        dequeue()
      }

      queue.enqueue(set: _*)
    }

    while (queue.nonEmpty) {
      dequeue()
    }

    res / values.size
  }
}

object Main extends App {
  override def main(args: Array[String]): Unit = {
    println(handle(read("input.txt")))
  }
}
