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

  def longitude: Float = _longitude
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
    point1.longitude = 101 // prints the warning
    print(point1)
  }
}

