package examples

class GeoPoint {

  private var _latitude : Float = 0
  private var _longitude : Float = 0

  private val bound = 90

  def latitude: Float = _latitude

  def latitude_=(newValue: Float): Unit = {
    if (newValue < bound)
      _latitude = newValue
    else
      printWarning()
  }

  // defines a function that returns _longitude value, getter
  def longitude: Float = _longitude

  // defines a function that sets a new value to _longitude, setter
  def longitude_=(newValue: Float): Unit = {
    if (newValue < bound)
      _longitude = newValue
    else
      printWarning()
  }

  private def printWarning(): Unit =
    println("WARNING: Out of bounds")

  override def toString() : String = {
    return "[Longitude : " + longitude +
      ", Latitude = " + latitude+"]";
  }
}

object GeoPoint {

  def main(args: Array[String]): Unit = {
    val point1 = new GeoPoint
    point1.latitude = 99
    point1.longitude = 101 // calls the setter and prints the warning if met condition
    println(point1)
    println(point1.longitude) //calls getter
  }
}

