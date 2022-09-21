package examples

class Implicits {

}


object ImplicitsMain {

  def main(args: Array[String]): Unit = {

    val message = "Hello "

    implicit val name = "World!"
    
    def displayName(implicit name : String) = {
      message + name
    }

    // Implicit parameter will be passed here
    val result = displayName

    // Implicit parameters will not be passed
    val result2 = displayName("Jose Beas!")

    println("With Implicit parameters:")
    println(result)
    println("Without Implicit parameters:")
    println(result2)
  }
}