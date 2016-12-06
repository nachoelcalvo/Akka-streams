package tweets

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, OverflowStrategy}
import akka.stream.scaladsl.{Flow, Keep, Sink, Source}
import akka.testkit.{ImplicitSender, TestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, MustMatchers}
import akka.pattern.pipe
import akka.stream.testkit.scaladsl.{TestSink, TestSource}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by josgar on 06/12/2016.
  */
class StreamKitSpec extends TestKit(ActorSystem("test-system"))
  with FlatSpecLike
  with ImplicitSender
  with BeforeAndAfterAll
  with MustMatchers {

  import system.dispatcher

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  implicit val flowMaterializer = ActorMaterializer()

  "With Stream Test Kit" should "use a TestSink to test a source" in {
    val sourceUnderTest = Source[Int](1 to 4).filter(_< 3).map(_ * 2)

    sourceUnderTest.runWith(TestSink.probe[Int]())
      .request(2)
      .expectNext(2, 4)
      .expectComplete()
  }

  it should "use a TestSource to test a sink" in {

    val sinkUnderTest = Sink.cancelled

    TestSource.probe[Int]()
        .toMat(sinkUnderTest)(Keep.left)
        .run()
        .expectCancellation()
  }

}
