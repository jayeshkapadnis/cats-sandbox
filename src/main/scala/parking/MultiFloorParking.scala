package parking

import parking.vehicle.{Ticket, Vehicle}

class MultiFloorParking(parkingLots: Map[Int, ParkingLot]) {

  def park(vehicle: Vehicle): Either[String, (MultiFloorParking, Ticket)] = {
    parkingLots.collectFirst(p => {
      p._2.park(vehicle) match {
        case Right(value) =>
          Right(
            (new MultiFloorParking(parkingLots.updated(p._1, value._1)), value._2))
      }
    }).getOrElse(Left("Not Parked"))
  }

}

object MultiFloorParking {

}



