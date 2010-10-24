package minimal.voldemort.client

import voldemort.versioning._

/**
 * Operations we support on a Voldemort store
 *
 * Stoled this shamelessly from scalmbert: http://github.com/afeinberg/scalmert
 */
object Versioned {
  def apply[V](value: V): Versioned[V] = new Versioned(value)
  def apply[V](value: V, version: Version): Versioned[V] = new Versioned(value, version)
}
