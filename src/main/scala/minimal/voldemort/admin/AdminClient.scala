package minimal.voldemort.admin

import voldemort.cluster.{Cluster => JCluster}

class AdminClient(config: AdminClientConfig) extends AdminClientOperations {
  val client = config.createAdminClient()
  val cluster = new Cluster(client.getAdminClientCluster)

  class Cluster(protected val cluster: JCluster) extends ClusterOperations
}

object AdminClient {
  def apply(bootstrapUrl: String) = new AdminClient(AdminClientConfig(bootstrapUrl, None))
}