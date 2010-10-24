package minimal.voldemort.client

import voldemort.client.StoreClient
import voldemort.cluster.Node
import voldemort.versioning._
import scala.collection.Map
import scalaj.collection.Imports._ // Java builtin Voldemort client expects Java collections

/**
 * Operations we support on a Voldemort store
 *
 * @author Alejandro Crosa <alejandro.crosa@gmail.com>
 */
trait VoldemortClientOperations[K, V] {
  val client: StoreClient[K, V]

  /**
   * get value for the specified key
   * use pattern matching afterwards
   *
   * @param key K
   * @return Option[V]
   */
  def get(key: K): Option[V] = {
    client.getValue(key.asInstanceOf[K]) match {
      case value: V => Some(value)
      case _ => None
    }
  }

  /**
   * get all the values for the specified collection of keys
   * use pattern matching afterwards
   *
   * @param keys Iterable[K] a list of keys to fetch from the nodes
   * @return Map[K, Option[V]] a map representation of the keys and their version
   */
  def get(keys: Iterable[K]): Map[K, Versioned[V]] = client.getAll(keys.asJava).asScala

  /**
   * put a value on the specified key
   *
   * @param key K
   * @param value V
   * @return Unit
   */
  def put(key: K, value: V): Unit = client.put(key.asInstanceOf[K], value)

  /**
   * put a versioned value on the specified key
   *
   * @param key K
   * @param versioned Versioned[V] a versioned value
   * @return Unit
   */
  def put(key: K, versioned: Versioned[V]): Unit = client.put(key.asInstanceOf[K], versioned)

  /**
   * delete a value on the specified key
   *
   * @param key K
   * @return Boolean
   */
  def delete(key: K): Boolean = client.delete(key)

  /**
   * delete a value on the specified key
   *
   * @param key Version[K]
   * @return Boolean
   */
  def delete(key: K, version: Version): Boolean = client.delete(key, version)

  /**
   * tells what's he responsible host for the given key
   *
   * @param key K
   * @return Seq[Node]
   */
  def getResponsibleNodes(key: K): Seq[Node] = client.getResponsibleNodes(key).asScala
}
