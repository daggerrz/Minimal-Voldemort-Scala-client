package minimal.voldemort.admin

import scala.collection.JavaConversions._

/**
 * A node in the cluster.
 */
case class Node(id: Int, host: String, httpPort: Int, socketPort: Int, adminPort: Int, partitionIds: List[Int])

private [admin] object Node {
  def apply(from: voldemort.cluster.Node) =
    new Node(from.getId, from.getHost, from.getHttpPort, from.getSocketPort, from.getAdminPort, from.getPartitionIds.toList.map(_.intValue))
}