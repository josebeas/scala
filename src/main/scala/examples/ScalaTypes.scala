package examples

object ScalaTypes {
  def main(args: Array[String]): Unit = {

    // lazy evaluation
    // this name block is not going to be executed till we read the name val
    // this could introduce deadlocks ir nested classes or members

    // also if this name member is static and we try to read it from many places
    // we need to wait till each place get its val initialized
    lazy val name = {
      println("Initialized")
      "Jose Beas"
    }
    //println(name)
    println("Data types")
    // Any --> AnyRef -> Object -> Classes (List, Option, OwnClasses) -> Null
    // Any --> AnyVal -> Lang Types (Int, Double, Float, Char, Short, Long, Byte, Unit, Boolean) -> Nothing

    // Unit ~ void

    // Any is the top class on scala, every value or reference in scala inherits from Any
    // so having a list of Any
    // in fact means you can store any type of objects
    val list: List[Any] = List(
      "a string", // a string
      732,  // an integer
      'c',  // a character
      true, // a boolean value
      () => "an anonymous function returning a string", //anonymous functions
       square(4) //declared functions
    )
    // iterating between every item from list using collections method foreach
    // and print it using println method
    list.foreach(element => println(element))

    println("Casting")
    //  Byte > Short > Int > Long > Float > Double
    //  Int > Char
    val x: Long = 987654321 // 987654321
    println(x)
    val y: Float = x  // 9.8765434E8 (note that some precision is lost in this case)
    println(y)
    val face: Char = '☺' // Unicode Decimal Code &#9786;
    println(face)
    val number: Int = face  // 9786
    println(number)

    println("Data type inference")
    val num = 123
    val string = "Scala"
    // scala is smart enough to determine any data type
    println(num.getClass)
    println(string.getClass)
    // in case any scenario requires specific data type
    // or can be misunderstood or mixed you can set data type using `: DataType`
    val anotherString: String = "anotherString"
    println(anotherString.getClass)
  }

  def square(num :Double ) :Double = {
    num * num
  }
}
