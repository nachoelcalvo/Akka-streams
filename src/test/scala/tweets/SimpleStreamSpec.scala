package tweets

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.testkit.ImplicitSender
import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, MustMatchers}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by josgar on 06/12/2016.
 */
class SimpleStreamSpec extends TestKit(ActorSystem("test-system"))
                        with FlatSpecLike
                        with ImplicitSender
                        with BeforeAndAfterAll
                        with MustMatchers {

    implicit val flowMaterializer = ActorMaterializer()

    override protected def afterAll = {
        TestKit.shutdownActorSystem(system)
    }

    "Sink" should "return the correct results" in {

      val source = Source[Int](1 to 4)

      val sink = Sink.fold[Int, Int](0)( _ + _ )

      val result = source.runWith(sink)

      Await.result(result, 100.milliseconds) must equal(10)
    }

    "Source" should "Content correct elements" in {

      val source = Source[Int](1 to 10)

      val result = source.grouped(2).runWith(Sink.head)

      Await.result(result, 100.milliseconds) must equal(1 to 2)

    }

    "Simple Flow" should "do rigth transformation" in {

      val flow = Flow[Int].takeWhile(_ < 5)

      val source = Source[Int](1 to 10)

      val result = source.via(flow).runWith(Sink.fold(Seq.empty[Int])(_ :+ _))

      Await.result(result, 100.milliseconds) must equal( 1 to 4)
    }
}
