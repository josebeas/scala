package examples

object Strings {
  def main(args: Array[String]): Unit = {
    //s string interpolation
    val stringInterpolation = "Scala"
    val sIntegerInterpolation = 2.11
    println(s"Welcome to $stringInterpolation version $sIntegerInterpolation")
    //Output: Welcome to Scala

    //f String Interpolation
    val height = 1.78
    val name = "Jose"
    println(f"My name is $name%s and I am $height%2.2f meters tall")
    //Output : My name is Jose and I am 1.78 meters tall

    //raw String Interpolation
    //prints raw string, this means does not escape any character
    println(raw"Welcome to Scala \nMy name is Harsh")
  }
}
