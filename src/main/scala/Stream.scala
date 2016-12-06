import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}

/**
  * Created by josgar on 06/12/2016.
  */
object Stream extends App {

  implicit val actorSystem = ActorSystem()
  import actorSystem.dispatcher
  implicit val flowMaterializer = ActorMaterializer()


    //Source
  val source = Source(1 to 100)

    //Flow
  val flow = Flow[Int].map(_ * 2)

    //Sink
  val sink = Sink.foreach(println)

  source.via(flow).runWith(sink).andThen {

    case _ => actorSystem.shutdown()
      actorSystem.awaitTermination()
  }
}
