package parking.vehicle

import parking.{ParkedSpot, Spot}

import scala.util.Random

case class Ticket(id: Int = Random.nextInt(10), spot: Spot) {
  def generateReceipt(): Receipt = {
    val vehicle = spot.asInstanceOf[ParkedSpot].vehicle
    Receipt(id, vehicle, spot, spot.size.basePrice * vehicle.multiplier())
  }
}

case class Receipt(id: Int, vehicle: Vehicle, spot: Spot, fare: Double)
