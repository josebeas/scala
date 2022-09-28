package examples

import scala.util.Random

case class Numero(value: Int)

object Matching {

  def getSize(x: Any) = x match {
    case string: String => string.length
    case map: Map[_, _] => map.size
    case seq: Seq[Int] => seq.size
    case integer: Int => integer
    case _ => -1 //not defined or default value
  }

  def isPrime(n: Int): Boolean = {
    ! ((2 until n-1) exists (n % _ == 0))
    //or (2 to num) forall (x => num % x != 0)
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


    //use of yield
    // * For each iteration of your for loop, yield generates a value which is remembered by the for loop
    // * When your for loop finishes running, it returns a collection of all these yielded values.
    // * The type of the collection that is returned is the same type that you were iterating over.
    val a = Seq(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    var allMembers = for (e <- a) yield e //use of yield like a return also using a guard to filter out members
    var greaterThan2 = for (e <- a if e > 2) yield e //use of yield like a return also using a guard to filter out members
    println(allMembers)
    println(greaterThan2)

    // yield es memory efficient than using a list
    var mixed = for {
      e <- a
      if(isPrime(e) && e > 0)
    } yield e
    println(mixed)
  }
}
