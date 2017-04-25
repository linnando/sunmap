package google.maps.canvaslayer

import google.maps

import scala.scalajs.js

@js.native
trait CanvasLayerOptions extends js.Object {
  var animate: Boolean = js.native
  var map: maps.Map = js.native
  var paneName: String = js.native
  var resizeHandler: () => Unit = js.native
  var resolutionScale: Double = js.native
  var updateHandler: () => Unit = js.native
}

object CanvasLayerOptions {
  def apply(animate: Option[Boolean] = None,
            map: maps.Map,
            paneName: Option[String] = None,
            resizeHandler: () => Unit,
            resolutionScale: Option[Double] = None,
            updateHandler: () => Unit): CanvasLayerOptions = {
    val options = js.Dynamic.literal(
      map = map,
      resizeHandler = resizeHandler,
      updateHandler = updateHandler
    ).asInstanceOf[CanvasLayerOptions]
    animate match { case Some(x) => options.animate = x; case None => }
    paneName match { case Some(x) => options.paneName = x; case None => }
    resolutionScale match { case Some(x) => options.resolutionScale = x; case None => }
    options
  }
}
