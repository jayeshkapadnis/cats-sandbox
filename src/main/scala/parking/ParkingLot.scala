package parking

import parking.vehicle.{Receipt, Ticket, Vehicle}

class ParkingLot(val emptySpots: List[Spot], val parkedSpots: List[Spot]) {

  def park(vehicle: Vehicle): Either[String, (ParkingLot, Ticket)] = {
    emptySpots match {
      case Nil => Left("No empty spots left")
      case x :: xs if vehicle.canFit(x.size) =>
        val spot = ParkedSpot(x.id, x.size, vehicle)
        Right((new ParkingLot(xs, parkedSpots :+ spot), Ticket(spot = spot)))
      case x :: xs if xs.nonEmpty =>
        xs.find(s => vehicle.canFit(s.size))
          .map { s =>
            val spot = ParkedSpot(s.id, s.size, vehicle)
            Right((new ParkingLot(x +: (xs diff List(s)), parkedSpots :+ spot), Ticket(spot = spot)))
          }.getOrElse(Left(s"Parking spot not available for $vehicle"))
      case _ => Left(s"Can't park vehicle $vehicle")
    }
  }

  def unPark(ticket: Ticket): Either[String, (ParkingLot, Receipt)] = {
    parkedSpots match {
      case Nil => Left("There are no parked vehicles")
      case _ =>
        parkedSpots.find(_.id == ticket.spot.id)
          .map(s => Right((
            new ParkingLot(emptySpots :+ EmptySpot(s.id, s.size), parkedSpots diff List(s)),
            ticket.generateReceipt()
          ))).getOrElse(Left(s"Vehicle not fount for Ticket $ticket"))
    }
  }
}

object ParkingLot {
  def apply(smallSpots: Int, mediumSpots: Int, largeSpots: Int): ParkingLot = {
    def emptySpots(n: Int, offset: Int, spotSize: SpotSize): List[Spot] = List.tabulate(n)(i => EmptySpot(i + offset, spotSize))

    new ParkingLot(
      emptySpots(smallSpots, 0, Small) ++ emptySpots(mediumSpots, smallSpots, Medium) ++ emptySpots(largeSpots, mediumSpots, Large),
      Nil
    )
  }
}

sealed trait Spot {
  val id: Int
  val size: SpotSize
}

case class ParkedSpot(id: Int, size: SpotSize, vehicle: Vehicle) extends Spot

case class EmptySpot(id: Int, size: SpotSize) extends Spot

trait SpotSize {
  val basePrice: Double
}

case object Small extends SpotSize {
  override val basePrice: Double = 100
}

case object Medium extends SpotSize {
  override val basePrice: Double = 100
}

case object Large extends SpotSize {
  override val basePrice: Double = 100
}
