import javax.servlet.ServletContext

import com.distancetracker.api.{DeviceApi, GPSApi}
import com.distancetracker.swagger.SwaggerIntegrationServlet
import com.distancetracker.util.DaoFactory
import com.mongodb.casbah.MongoClient
import org.scalatra.LifeCycle


class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext): Unit = {

    implicit val deviceDS = DaoFactory.getDeviceDao
    implicit val gpsDataDS = DaoFactory.getGpsDao

    context.mount(new DeviceApi, "/devices/*", "devices")
    context.mount(new GPSApi, "/coordinates/*", "coordinates")

    //swagger
    context.mount(new SwaggerIntegrationServlet, "/api-docs")
  }

  override def destroy(context: ServletContext): Unit = {

  }

}
