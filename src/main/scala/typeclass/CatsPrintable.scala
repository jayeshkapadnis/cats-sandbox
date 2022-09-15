package typeclass

import cats.Show
import cats.instances.int._

object CatsPrintableInterfaceObject {
  val intPrintable: Show[Int] = Show.apply[Int]

  val personPrintable: Show[Person] =
    new Show[Person] {
      override def show(t: Person): String = s"${t.name} - ${t.birthYear}"
    }
}

object CatsPrintableInterfaceSyntax {

}