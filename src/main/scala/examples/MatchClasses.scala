package examples

class Test {

  def getValue(x: Any) : Unit = x match {
    case "Value1"=> Function1(3)
    case "Value2"=> Function2(3)
    case _ => println("This is an invalid value")
  }

  def Function1(number: Int) {
    for(value <- 0 to number){
      println("number " + value)
    }
  }

  def Function2(number: Int) {
    println("number " + number)
  }

}

object MatchPatternsSamples {

  def main(args: Array[String]): Unit = {
    val test: Test = new Test
    test.getValue("nonHandled")
    test.getValue("Value1")
    test.getValue("Value2")
  }
}