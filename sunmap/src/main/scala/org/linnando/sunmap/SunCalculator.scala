package org.linnando.sunmap

import math._

case class SunCalculator(greenwichSolarTime: Double, sinDeclination: Double, cosDeclination: Double) {
  import SunCalculator.SIN_ALTITUDE

  def isDaylight(latitude: Double, longitude: Double): Boolean = {
    val hourAngle = greenwichSolarTime + longitude.toRadians
    val sinLatitude = sin(latitude.toRadians)
    val cosLatitude = sqrt(1 - sinLatitude * sinLatitude)
    cos(hourAngle) > (SIN_ALTITUDE - sinLatitude * sinDeclination) / cosLatitude / cosDeclination
  }
}

object SunCalculator {
  private val EPOCH = 946728000000L
  private val MILLISECONDS_IN_DAY = 86400000L
  private val EPOCH_LONGITUDE = 280.460.toRadians
  private val DAY_LONGITUDE_SHIFT = 0.9856474.toRadians
  private val EPOCH_ANOMALY = 357.528.toRadians
  private val DAY_ANOMALY_SHIFT = 0.9856003.toRadians
  private val LONGITUDE_FIRST_COEFFICIENT = 1.915.toRadians
  private val LONGITUDE_SECOND_COEFFICIENT = 0.020.toRadians
  private val EQUATION_OF_TIME_ANOMALY_COEFFICIENT = 0.0053
  private val EQUATION_OF_TIME_LONGITUDE_COEFFICIENT = 0.0069
  private val ECLIPTIC_OBLIQUITY = 23.44.toRadians
  private val SIN_ALTITUDE = sin(-0.83.toRadians)

  def apply(time: Double): SunCalculator = {
    val daysSinceEpoch = (time - EPOCH) / MILLISECONDS_IN_DAY
    val meanLongitude = (EPOCH_LONGITUDE + DAY_LONGITUDE_SHIFT * daysSinceEpoch) % (2 * Pi)
    val meanAnomaly = (EPOCH_ANOMALY + DAY_ANOMALY_SHIFT * daysSinceEpoch) % (2 * Pi)
    val eclipticLongitude = meanLongitude +
      LONGITUDE_FIRST_COEFFICIENT * sin(meanAnomaly) +
      LONGITUDE_SECOND_COEFFICIENT * sin(2 * meanAnomaly)
    val greenwichTransit = EPOCH + MILLISECONDS_IN_DAY * (
      daysSinceEpoch.round +
      EQUATION_OF_TIME_ANOMALY_COEFFICIENT * sin(meanAnomaly) -
      EQUATION_OF_TIME_LONGITUDE_COEFFICIENT * sin(2 * eclipticLongitude)
    )
    val sinDeclination = sin(ECLIPTIC_OBLIQUITY) * sin(eclipticLongitude)
    SunCalculator(
      greenwichSolarTime = (time - greenwichTransit) * 2 * Pi / MILLISECONDS_IN_DAY,
      sinDeclination = sinDeclination,
      cosDeclination = sqrt(1 - sinDeclination * sinDeclination)
    )
  }
}
