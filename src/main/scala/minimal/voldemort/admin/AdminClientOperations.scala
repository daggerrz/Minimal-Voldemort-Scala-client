package minimal.voldemort.admin

import voldemort.client.protocol.admin.{AdminClient => JAdminClient}
import scala.collection.JavaConverters._
import voldemort.versioning.Versioned

trait AdminClientOperations {
  val client: JAdminClient
  val cluster: ClusterOperations

  import AdminClientOperations._


  def fetchKeys(node: Int, storeName: String, partitions: List[Int],
                skipRecords: Long = 0,
                onlyMasterEntries: Boolean = true
                 ): Iterator[voldemort.utils.ByteArray] = {
    client.fetchKeys(node, storeName, partitions, null, onlyMasterEntries, skipRecords)
      .asScala
  }

  def fetchEntries(node: Int, storeName: String, partitions: List[Int],
                skipRecords: Long = 0,
                onlyMasterEntries: Boolean = true
                  ) : Iterator[(voldemort.utils.ByteArray, Versioned[Array[Byte]])] = {
    client.fetchEntries(node, storeName, partitions, null, onlyMasterEntries, skipRecords)
      .asScala.map(pair => (pair.getFirst, pair.getSecond))
  }

}

object AdminClientOperations {
  implicit def intListToIntegerList(from: List[Int]) : java.util.List[java.lang.Integer] = from.map(new java.lang.Integer(_)).asJava
}