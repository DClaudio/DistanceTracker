import javax.servlet.ServletContext

import com.test.api.TestApi
import org.scalatra.LifeCycle
import org.scalatra._


/**
 * Created by claudio.david on 11/09/2015.
 */
class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext): Unit = {

    context.mount(new TestApi(), "/testapi")

  }

}
