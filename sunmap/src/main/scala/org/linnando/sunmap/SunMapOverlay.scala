package org.linnando.sunmap

import google.maps
import google.maps.canvaslayer._
import org.scalajs.dom

import scala.scalajs.js

class SunMapOverlay(map: maps.Map) {
  val rectLatLng = new maps.LatLng(40, -95)
  val rectWidth = 6.5
  private val options = CanvasLayerOptions(
    map = map,
    resizeHandler = resize,
    updateHandler = update
  )
  val canvasLayer = new CanvasLayer(options)
  val context: dom.CanvasRenderingContext2D = canvasLayer.canvas.getContext("2d") match {
    case c: dom.CanvasRenderingContext2D => c
    case _ => throw new Error
  }

  def resize(): Unit = {}

  def update(): Unit = {
    val canvasWidth = canvasLayer.canvas.width
    val canvasHeight = canvasLayer.canvas.height
    context.clearRect(0, 0, canvasWidth, canvasHeight)
    context.setTransform(1, 0, 0, 1, 0, 0)
    val scale = math.pow(2, map.getZoom())
    context.scale(scale, scale)
    val mapProjection = map.getProjection()
    val offset = mapProjection.fromLatLngToPoint(canvasLayer.getTopLeft())
    context.translate(-offset.x, -offset.y)
    drawNightShadow(canvasWidth, canvasHeight, mapProjection)
  }

  private def drawNightShadow(canvasWidth: Int, canvasHeight: Int, mapProjection: maps.Projection) = {
    val time = js.Date.now()
    //val time = new js.Date(2017,5,22,14,0).getTime()
    val calculator = SunCalculator(time)
    context.fillStyle = "rgba(0, 0, 0, 0.5)"
    (0 until canvasWidth) foreach { x =>
      (0 until canvasHeight) foreach { y =>
        val latLng = mapProjection.fromPointToLatLng(new maps.Point(x, y))
        if (!calculator.isDaylight(latLng.lat(), latLng.lng()))
          context.fillRect(x, y, 1, 1)
      }
    }
  }
}
