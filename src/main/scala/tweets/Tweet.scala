package tweets

/**
  * Created by josgar on 06/12/2016.
  */
case class Author(name: String)

case class Hashtag(name: String){
  require(name.startsWith("#"), "Hashtag must starts with #")
}

case class Tweet(author: Author, body: String) {

  def hashTags(): Set[Hashtag] = {
    body.split(" ").collect { case h if h.startsWith("#") => Hashtag(h) }.toSet
  }
}
