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
  ).aggregate(
    layeredApplication,
    layeredDomain,
    layeredInfrastructure
  ).dependsOn(
    layeredApplication,
    layeredDomain,
    layeredInfrastructure
  )

  lazy val layeredApplication = play.Project(
    name = "layered-application",
    path= file("modules/layered-application"),
    settings = Project.defaultSettings ++ com.typesafe.sbtidea.SbtIdeaPlugin.ideaSettings
  ).aggregate(
    layeredDomain,
    layeredInfrastructure
  ).dependsOn(
    layeredDomain,
    layeredInfrastructure
  )

  lazy val layeredDomain = Project(
    id = "layered-domain",
    base = file("modules/layered-domain"),
    settings = Project.defaultSettings ++ com.typesafe.sbtidea.SbtIdeaPlugin.ideaSettings
  ).aggregate(
    layeredInfrastructure
  ).dependsOn(
    layeredInfrastructure
  )

  lazy val layeredInfrastructure = Project(
    id = "layered-infrastructure",
    base = file("modules/layered-infrastructure"),
    settings = Project.defaultSettings ++ com.typesafe.sbtidea.SbtIdeaPlugin.ideaSettings
  )
}
