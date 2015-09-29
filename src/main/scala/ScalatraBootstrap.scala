import javax.servlet.ServletContext

import com.distancetracker.api.DeviceApi
import com.distancetracker.dao.DeviceDao
import com.distancetracker.persistence.InMemoryDataSource
import com.distancetracker.swagger.{SwaggerIntegrationServlet}
import org.scalatra.LifeCycle


/**
 * Created by claudio.david on 11/09/2015.
 */
class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext): Unit = {

    implicit val deviceDao = new DeviceDao(new InMemoryDataSource)

    context.mount(new DeviceApi, "/devices/*", "devices")

    //swagger
    context.mount(new SwaggerIntegrationServlet, "/api-docs")
  }

}
