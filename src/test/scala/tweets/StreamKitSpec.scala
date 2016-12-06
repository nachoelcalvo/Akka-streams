package tweets

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, OverflowStrategy}
import akka.stream.scaladsl.{Flow, Keep, Sink, Source}
import akka.testkit.{ImplicitSender, TestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, MustMatchers}
import akka.pattern.pipe

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by josgar on 06/12/2016.
  */
class StreamActorSpec extends TestKit(ActorSystem("test-system"))
  with FlatSpecLike
  with ImplicitSender
  with BeforeAndAfterAll
  with MustMatchers {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  implicit val flowMaterializer = ActorMaterializer()

  "With Stream Test Kit" should "use a TestSink to test a source" in {

  }

  it should "use a TestSource to test a sink" in {

  }

}
