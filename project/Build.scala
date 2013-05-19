import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "layered"
  val appVersion      = "1.0-SNAPSHOT"

  override def settings = super.settings ++ com.typesafe.sbtidea.SbtIdeaPlugin.ideaSettings

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  ).dependsOn(
    layeredInfrastructure,
    layeredDomain
  )

  lazy val layeredDomain = Project(
    id = "layered-domain",
    base = file("modules/layered-domain"),
    settings = Project.defaultSettings ++ com.typesafe.sbtidea.SbtIdeaPlugin.ideaSettings
  ).dependsOn(
    layeredInfrastructure
  )

  lazy val layeredInfrastructure = Project(
    id = "layered-infrastructure",
    base = file("modules/layered-infrastructure"),
    settings = Project.defaultSettings ++ com.typesafe.sbtidea.SbtIdeaPlugin.ideaSettings
  )
}
