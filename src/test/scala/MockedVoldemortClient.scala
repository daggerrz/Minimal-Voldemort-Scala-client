package minimal.voldemort.client

import voldemort.serialization.StringSerializer
import voldemort.client.MockStoreClientFactory

class MockedSimpleVoldemortClient[K, V](val storeName: String, val storeUrl: String) extends VoldemortClientOperations[K, V] with MockedVoldemortClientSocket[K, V]
