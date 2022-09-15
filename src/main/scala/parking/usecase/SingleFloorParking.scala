package parking.usecase

import cats.data.StateT
import parking.ParkingLot
import parking.usecase.SingleFloorParkingWithError.F
import parking.vehicle.{Car, Receipt, Ticket, Vehicle}

object SingleFloorParkingWithError {

  type F[A] = Either[String, A]
  type ParkingStateActionMonad[A] = StateT[F, ParkingLot, A]

  private def park(vehicle: Vehicle): ParkingStateActionMonad[Ticket] = StateT {
    _.park(vehicle)
  }

  private def unPark(ticket: Ticket): ParkingStateActionMonad[Receipt] = StateT {
    _.unPark(ticket)
  }

  def interpreter() = {
    for {
      s <- park(Car("", 2))
      s1 <- park(Car("", 3))
      s2 <- park(Car("", 4))
      t <- unPark(s)
      _ <- unPark(s1)
      _ <- unPark(s2)
    } yield t
  }
}


object Parking2 extends App {

  private val either: F[(ParkingLot, Receipt)] = SingleFloorParkingWithError.interpreter().run(ParkingLot(1, 3, 3))

  either match {
    case Left(exc) => throw new RuntimeException(exc)
    case Right((lot, ticket)) =>
      println(parkingShow.show(lot))
      println(ticket)
  }
}
