import javax.servlet.ServletContext

import com.distancetracker.api.DeviceApi
import com.distancetracker.dao.DeviceDao
import com.distancetracker.persistence.InMemoryDataSource
import com.distancetracker.swagger.{ResourcesApp, DistanceTrackerSwagger}
import org.scalatra.LifeCycle
import org.scalatra._


/**
 * Created by claudio.david on 11/09/2015.
 */
class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext): Unit = {

    val deviceDao = new DeviceDao(InMemoryDataSource)

    context.mount(new DeviceApi(), "/devices/*", "devices")

    //swagger
    context.mount(new ResourcesApp(), "/api-docs")
  }

}
