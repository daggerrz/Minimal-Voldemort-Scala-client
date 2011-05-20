package minimal.voldemort.admin
import scala.collection.JavaConverters._

/**
 *
 */
trait ClusterOperations {
  protected val cluster : voldemort.cluster.Cluster

  def nodes = cluster.getNodes.asScala.map(Node.apply)

  /**
   * Gets the first available node for a partition of a store.
   */
  def findNode(partitionId: Int, storeName: String) = nodes.find(_.partitionIds.contains(partitionId))
}