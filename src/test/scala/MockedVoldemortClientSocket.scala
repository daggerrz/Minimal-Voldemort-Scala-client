package minimal.voldemort.client

import voldemort.serialization.StringSerializer
import voldemort.client.MockStoreClientFactory

trait MockedVoldemortClientSocket[K, V] {

  val storeUrl: String
  val storeName: String

  val serializer = new StringSerializer
  val factory = new MockStoreClientFactory(serializer, serializer, serializer)
  val client = factory.getStoreClient[K, V]("test")
}
