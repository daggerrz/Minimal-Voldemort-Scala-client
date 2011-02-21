package minimal.voldemort.client

import voldemort.client.ClientConfig
import voldemort.client.SocketStoreClientFactory
import voldemort.client.StoreClient

/**
 * Voldemort Java client trait wrapper
 *
 * @author Alejandro Crosa <alejandro.crosa@gmail.com>
 */
trait VoldemortClientSocket[K, V] {

  val storeUrls: Seq[String]
  val storeName: String

  val clientConfig: ClientConfig = new ClientConfig().setBootstrapUrls(storeUrls: _*)
  clientConfig.setFailureDetectorBannagePeriod(1000) // In the case of errors reconnect after 1 second
  val clientFactory: SocketStoreClientFactory = new SocketStoreClientFactory(clientConfig)
  val client: StoreClient[K, V] = clientFactory.getStoreClient(storeName)
}
