import javax.servlet.ServletContext

import com.distancetracker.api.DeviceApi
import com.distancetracker.model.DeviceEntity
import com.distancetracker.persistence.InMemoryDataSource
import com.distancetracker.swagger.SwaggerIntegrationServlet
import org.scalatra.LifeCycle


class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext): Unit = {

    implicit val deviceDao = new InMemoryDataSource[DeviceEntity]

    context.mount(new DeviceApi, "/devices/*", "devices")

    //swagger
    context.mount(new SwaggerIntegrationServlet, "/api-docs")
  }

}
