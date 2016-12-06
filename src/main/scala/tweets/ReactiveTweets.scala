package tweets

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}
import twitter4j.Status

/**
  * Created by josgar on 06/12/2016.
  */
object ReactiveTweets extends App {

  implicit val actorSystem = ActorSystem()
  import actorSystem.dispatcher
  implicit val flowMaterializer = ActorMaterializer()

  val source = Source(() => TwitterClient.retrieveTweets("#akka"))

  val normalize = Flow[Status].map(t => Tweet(Author(t.getUser.getName), t.getText))

  val sink = Sink.foreach(println)

  source.via(normalize).runWith(sink).andThen{
    case _ => actorSystem.shutdown()
              actorSystem.awaitTermination()
  }
}
