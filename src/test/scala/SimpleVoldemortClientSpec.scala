package minimal.voldemort.client

import org.specs.Specification

class SimpleVoldemortClientSpec extends Specification {
  val client = new MockedSimpleVoldemortClient[String, String]("test", "tcp://somehost:6666")

  "SimpleVoldemortClient" should {
    "return None when the key specified has no value" in {
      client.get("key") mustEqual None
    }

    "put a value in the specified key" in {
      client.put("key", "value")
      client.get("key") mustEqual Some("value")
    }

    "put a versoned value in the specified key" in {
      client.put("key", Versioned("value"))
      client.get("key") mustEqual Some("value")
    }

    "return all the specified keys when calling get with an Iterator" in {
      client.put("key", "value")
      client.put("key2", "value2")
      // Support any iterable like List
      val allValues = client.get(List("key", "key2")) // Calls getAll
      allValues("key").getValue mustEqual "value"
      allValues("key2").getValue mustEqual "value2"
    }

    "return all the specified keys" in {
      client.put("key", "value")
      client.put("key2", "value2")
      // Support any iterable like List
      val allValues = client.getAll(List("key", "key2"))
      allValues("key").getValue mustEqual "value"
      allValues("key2").getValue mustEqual "value2"
      // Support other Iterables like Array
      val moreValues = client.getAll(List("key", "key2"))
      moreValues("key").getValue mustEqual "value"
      moreValues("key2").getValue mustEqual "value2"
    }

    "delete a specified key" in {
      client.put("key", "value")
      client.get("key") mustEqual Some("value")
      client.delete("key")
      client.get("key") mustEqual None
    }
  }
}
