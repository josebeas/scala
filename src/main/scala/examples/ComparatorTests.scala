package examples

import examples.comparators.Comparator


object ComparatorTest {
  def max[A](x: A, y: A)(implicit comparator: Comparator[A]): A =
    if (comparator.compare(x, y) >= 0) x
    else y


  def main(args: Array[String]): Unit = {

    //import examples.comparators.SpecialComparators.BooleanComparator

    println(max(10, 20))
    println(max("hello", "world"))
    // this wont compile since no boolean comparator defined and implicitly imported
    //println(max(false, true))
  }
}