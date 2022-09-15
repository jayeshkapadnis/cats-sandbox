package parking.vehicle

import parking.{Large, Medium, Small, SpotSize}

sealed trait Vehicle {
  def registrationNumber(): String

  def multiplier(): Int

  def canFit(spotSize: SpotSize): Boolean
}

case class Car(registrationNumber: String, multiplier: Int) extends Vehicle {
  val size: Medium.type = Medium

  override def canFit(spotSize: SpotSize): Boolean = if (spotSize == Medium) true else false
}

case class Truck(registrationNumber: String, multiplier: Int) extends Vehicle {
  val size: Large.type = Large

  override def canFit(spotSize: SpotSize): Boolean = if (spotSize == Large) true else false
}

case class Bike(registrationNumber: String, multiplier: Int) extends Vehicle {
  val size: Small.type = Small

  override def canFit(spotSize: SpotSize): Boolean = if (spotSize == Small) true else false
}
