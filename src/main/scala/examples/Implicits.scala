package examples

class Implicits {

}


object ImplicitsMain {
  def main(args: Array[String]): Unit = {
    val point1 = new GeoPoint
    point1.latitude = 99
    point1.longitude = 101 // prints the warning
    print(point1)
  }
}