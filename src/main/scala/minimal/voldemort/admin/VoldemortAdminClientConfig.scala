package minimal.voldemort.admin

import voldemort.client.{SocketStoreClientFactory, ClientConfig}
import voldemort.client.protocol.admin.AdminClientConfig

case class VoldemortAdminClientConfig(storeUrls: Seq[String], configurator: Option[VoldemortAdminClientConfig => Unit]) {

  private val clientConfig: VoldemortAdminClientConfig = new VoldemortAdminClientConfig(setBootstrapUrls(storeUrls: _*)

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