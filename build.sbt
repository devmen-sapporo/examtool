name := "examtool"
 
version := "1.0-SNAPSHOT"
 
libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  filters,
  "mysql" % "mysql-connector-java" % "5.1.25",
  "org.pac4j" % "play-pac4j_java" % "1.2.0-SNAPSHOT",
  "org.pac4j" % "pac4j-oauth" % "1.5.0-SNAPSHOT"
)
 
resolvers ++= Seq(
    "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/"
)
 
play.Project.playJavaSettings