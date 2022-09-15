package typeclass

object Main extends App {

  import CatsPrintableInterfaceObject._
  import JsonWriterInstances._

  val person = Person("Ananya", 2017)

  private val json: Json = Json.toJson(person)
  println(json)

  println(personPrintable.show(person))
}
