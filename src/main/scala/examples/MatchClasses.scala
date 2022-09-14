package examples

class Test {

  def getValue(x: Any) : Unit = x match {
    case "Value1"=> Function1(3)
    case "Value2"=> Function2(3)
    case _ => println("This is an invalid value")
  }

  def Function1(number: Int) {
    //for(i )
  }

  def Function2(number: Int) {
    //Do Something
  }

}

object MatchPatternsSamples {

  def main(args: Array[String]): Unit = {


  }
}