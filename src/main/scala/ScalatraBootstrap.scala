import javax.servlet.ServletContext

import com.distancetracker.api.{DeviceApi, GPSApi}
import com.distancetracker.dao.InMemoryGenericDao
import com.distancetracker.persistence.{DeviceEntity, GpsDataEntity}
import com.distancetracker.swagger.SwaggerIntegrationServlet
import org.scalatra.LifeCycle


class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext): Unit = {

    implicit val deviceDS = new InMemoryGenericDao[DeviceEntity]
    implicit val gpsDataDS = new InMemoryGenericDao[GpsDataEntity]

    context.mount(new DeviceApi, "/devices/*", "devices")
    context.mount(new GPSApi, "/coordinates/*", "coordinates")

    //swagger
    context.mount(new SwaggerIntegrationServlet, "/api-docs")
  }

}
