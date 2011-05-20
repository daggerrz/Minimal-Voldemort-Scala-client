package minimal.voldemort.client

import voldemort.client.StoreClient

/**
 * An extremely simple wrapper for the Voldemort distributed key value store
 *
 * @author Alejandro Crosa <alejandro.crosa@gmail.com>
 *
 * @param storeName String the store we are going to use on the voldemort nodes (make sure your config file defines this)
 * @param storeUrl  String the url of the voldemort host ex. tcp://localhost:6666
 */
class SimpleVoldemortClient[K, V](val storeName: String, config: SimpleVoldemortConfig) extends VoldemortClientOperations[K, V] {
  val client: StoreClient[K, V] = config.getStoreClient(storeName)
}

object SimpleVoldemortClient {

  /**
   * Create a new client.
   *
   * @param storeName String the store we are going to use on the voldemort nodes (make sure your config file defines this)
   * @param storeUrl  String the url of the voldemort host ex. tcp://localhost:6666
   */
  def apply[K, V](storeName: String, storeUrl: String): SimpleVoldemortClient[K, V] = apply(storeName, List(storeUrl))

  /**
   * Create a new client.
   *
   * @param storeName String the store we are going to use on the voldemort nodes (make sure your config file defines this)
   * @param storeUrls  String the urls of the voldemort hosts ex. List(tcp://localhost:6666, tcp://localhost:6668)
   */
  def apply[K, V](storeName: String, storeUrls: Seq[String]): SimpleVoldemortClient[K, V] = apply(storeName, SimpleVoldemortConfig(storeUrls, None))

  /**
   * Create a new client using an existing configuration. Should be used if several stores are used
   * in order to avoid excessive client connections and failure detection.
   */
  def apply[K, V](storeName: String, config: SimpleVoldemortConfig) : SimpleVoldemortClient[K, V] = new SimpleVoldemortClient[K, V](storeName, config)
}
