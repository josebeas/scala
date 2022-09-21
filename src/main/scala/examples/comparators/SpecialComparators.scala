package examples.comparators

object SpecialComparators {
  implicit object BooleanComparator extends Comparator[Boolean] {
    def compare(x: Boolean, y: Boolean): Int = {
      if (x == y)
        0
      else
        1
    }
  }
}


