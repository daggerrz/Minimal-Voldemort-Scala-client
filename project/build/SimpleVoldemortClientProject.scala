import sbt._

class SimpleVoldemortClientProject(info: ProjectInfo) extends DefaultProject(info)
{
  override def useDefaultConfigurations = true

  // scalaTest
  val specs = "org.scala-tools.testing" %% "specs" % "1.6.5" % "test"
  val scalaTest = "org.scalatest" % "scalatest" % "1.2" % "test"
  val mockito = "org.mockito" % "mockito-core" % "1.8.0"
  val junit = "junit" % "junit" % "4.5"

  // Voldemort dependencies
  val scalajCollections = "org.scalaj" %% "scalaj-collection" % "1.0" 
  val googleCollections = "com.google.collections" % "google-collections" % "1.0"
  val commonsLang = "commons-lang" % "commons-lang" % "2.4"
  val jdom = "jdom" % "jdom" % "1.1"

  val log4j = "log4j" % "log4j" % "1.2.14"

  override def compileOptions = super.compileOptions ++ Seq(Unchecked, CompileOption("-print"))
}
