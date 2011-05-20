package minimal.voldemort.admin

import voldemort.client.protocol.admin.{AdminClient => JAdminClient, AdminClientConfig => JAdminClientConfig}

case class AdminClientConfig(bootstrapUrl: String, configurator: Option[JAdminClientConfig => Unit]) {

  private val clientConfig = new JAdminClientConfig()

  configurator.foreach(_.apply(clientConfig))

  /**
   * Creates a new admin client for the current configuration.
   */
  def createAdminClient() = new JAdminClient(bootstrapUrl, clientConfig)
}

object AdminClientConfig {

  def apply(bootstrapUrl: String)(configurator: (JAdminClientConfig => Unit) = {x => }) =
    new AdminClientConfig(bootstrapUrl, Some(configurator))

}