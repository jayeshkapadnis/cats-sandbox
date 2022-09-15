package parking

import cats.Show

package object usecase {

  implicit val parkingShow: Show[ParkingLot] =
    (t: ParkingLot) => s"${t.emptySpots} - ${t.parkedSpots}"
}
