import com.distancetracker.api.{DeviceApi, GPSApi}
import com.mongodb.casbah.Imports._
import com.example.app._
import com.swagger.ResourcesApp
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {

    // connecting with default settings localhost on port 27017
    val mongoClient = MongoClient("localhost", 27017)
    val mongoCollection = mongoClient("distance_tracker")("users")

    // pass a reference to the Mongo collection into your servlet when you mount it at application start:
    context.mount(new MongoController(mongoCollection), "/*")

    context.mount(new DeviceApi, "/Device/*")
    context.mount(new GPSApi, "/GPS/*")

    context.mount(new ResourcesApp, "/api-docs")

  }
}
