package typeclass

trait Json

case class JObject(value: Map[String, Json]) extends Json

case class JNumber(value: Int) extends Json

case class JString(value: String) extends Json

trait JsonWriter[A] {
  def write(value: A): Json
}

case class Person(name: String, birthYear: Int)

object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] = (value: String) => JString(value)
  implicit val numberWriter: JsonWriter[Int] = (value: Int) => JNumber(value)
  implicit val personWriter: JsonWriter[Person] = (value: Person) =>
    JObject(
      Map(
        "name" -> JString(value.name),
        "birthYear" -> JNumber(value.birthYear)
      )
    )
}

// Interface Object
object Json {
  def toJson[A](value: A)(implicit writer: JsonWriter[A]): Json = writer.write(value)
}

// Interface Syntax
object JsonWithOps {
  implicit class JsonWriterOps[A](value: A) {
    def toJson()(implicit writer: JsonWriter[A]): Json = writer.write(value)
  }
}

object JsonWithImplicitly {
  def implicitly[A](implicit value: A): A = value
}
