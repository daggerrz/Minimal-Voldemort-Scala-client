package minimal.voldemort.client

abstract class AbstractVoldemortClient[K, V] extends VoldemortClientSocket[K, V] with VoldemortClientOperations[K, V] {
  val storeName: String
  val storeUrls: Seq[String]
}

/**
 * An extremely simple wrapper for the Voldemort distributed key value store
 *
 * @author Alejandro Crosa <alejandro.crosa@gmail.com>
 *
 * @param storeName String the store we are going to use on the voldemort nodes (make sure your config file defines this)
 * @param storeUrl  String the url of the voldemort host ex. tcp://localhost:6666
 */
class SimpleVoldemortClient[K, V](val storeName: String, val storeUrls: Seq[String]) extends AbstractVoldemortClient[K, V]

object SimpleVoldemortClient {
	
	/**
	 * Create a new client. 
	 *
	 * @param storeName String the store we are going to use on the voldemort nodes (make sure your config file defines this)
	 * @param storeUrl  String the url of the voldemort host ex. tcp://localhost:6666
	 */
	def apply[K, V](storeName: String, storeUrl: String) = new SimpleVoldemortClient[K, V](storeName, List(storeUrl))

	/**
	 * Create a new client. 
	 *
	 * @param storeName String the store we are going to use on the voldemort nodes (make sure your config file defines this)
	 * @param storeUrls  String the urls of the voldemort hosts ex. List(tcp://localhost:6666, tcp://localhost:6668)
	 */
	def apply[K, V](storeName: String, storeUrls: Seq[String]) = new SimpleVoldemortClient[K, V](storeName, storeUrls)
}
