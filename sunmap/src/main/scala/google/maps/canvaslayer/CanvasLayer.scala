package google.maps.canvaslayer

import google.maps
import org.scalajs.dom.html

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
@JSName("CanvasLayer")
class CanvasLayer protected() extends maps.OverlayView {
  def this(opt_options: CanvasLayerOptions) = this()
  var canvas: html.Canvas = js.native
  def getPaneName(): String = js.native
  def getTopLeft(): maps.LatLng = js.native
  def isAnimated(): Boolean = js.native
  def scheduleUpdate(): Unit = js.native
  def setAnimate(animate: Boolean): Unit = js.native
  def setOptions(options: CanvasLayerOptions): Unit = js.native
  def setPaneName(paneName: String): Unit = js.native
  def setResizeHandler(opt_resizeHandler: () => Unit): Unit = js.native
  def setUpdateHandler(opt_updateHandler: () => Unit): Unit = js.native
}
