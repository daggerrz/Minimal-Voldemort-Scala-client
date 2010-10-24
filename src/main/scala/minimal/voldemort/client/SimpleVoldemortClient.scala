package minimal.voldemort.client

abstract class AbstractVoldemortClient[K, V] extends VoldemortClientSocket[K, V] with VoldemortClientOperations[K, V] {
  val storeName: String
  val storeUrl: String
}

/**
 * An extremely simple wrapper for the Voldemort distributed key value store
 *
 * @author Alejandro Crosa <alejandro.crosa@gmail.com>
 *
 * @param storeName String the store we are going to use on the voldemort nodes (make sure your config file defines this)
 * @param storeUrl  String the url of the voldemort host ex. tcp://localhost:6666
 */
class SimpleVoldemortClient[K, V](val storeName: String, val storeUrl: String) extends AbstractVoldemortClient[K, V]
