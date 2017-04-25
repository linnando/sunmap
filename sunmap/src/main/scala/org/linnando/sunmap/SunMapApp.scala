package org.linnando.sunmap

import google.maps
import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js

object SunMapApp extends js.JSApp {
  override def main(): Unit = {
    val initialize = () => {
      val opts = maps.MapOptions(
        center = new maps.LatLng(0.0, 0.0),
        zoom = 1,
        panControl = false,
        streetViewControl = false,
        mapTypeControl = false)
      val container = dom.document.getElementById("map") match {
        case c: html.Div => c
        case _ => throw new Error
      }
      val map = new maps.Map(container, opts)
      new SunMapOverlay(map)
    }
    maps.event.addDomListener(dom.window, "load", initialize)
  }
}
