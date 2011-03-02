package minimal.voldemort.client

import voldemort.client.{SocketStoreClientFactory, ClientConfig}

/**
 * A configurable client factory.
 *
 * @param storeUrl Voldemort store urls (e.g tcp://localhost:6666)
 * @param configurator configuration customization function
 */
case class SimpleVoldemortConfig(storeUrls: Seq[String], configurator: Option[ClientConfig => Unit]) {

  private val clientConfig: ClientConfig = new ClientConfig().setBootstrapUrls(storeUrls: _*)

  configurator.foreach(_.apply(clientConfig))

  private val clientFactory: SocketStoreClientFactory = new SocketStoreClientFactory(clientConfig)

  /**
   * Gets a store client for the specified store name.
   *
   * @param storeName the store name
   */
  def getStoreClient[K, V](storeName: String) = clientFactory.getStoreClient[K, V](storeName)
}

object SimpleVoldemortConfig {

  def apply(storeUrls: Seq[String])(configurator: (ClientConfig => Unit) = {x => }): SimpleVoldemortConfig =
    SimpleVoldemortConfig(storeUrls, Some(configurator))

}