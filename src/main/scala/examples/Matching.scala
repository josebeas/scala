package examples

import scala.util.Random

object Matching {

  def getSize(x: Any) = x match {
    case string: String => string.length
    case map: Map[_, _] => map.size
    case seq: Seq[Int] => seq.size
    case integer: Int => integer
    case _ => -1 //not defined or default value
  }

  def main(args: Array[String]): Unit = {
    val x: Int = Random.nextInt(10)

    x match {
      case 0 => println("zero")
      case 1 => println("one")
      case 2 => println("two")
      case _ => println("many")
    }

    var other: Any = ""
    println(getSize(other))
    //since it was defined as Any, we can change type on the fly
    other = Seq(1, 2, 3)
    println(getSize(other))
  }
}
