name := "examtool"
 
version := "1.0-SNAPSHOT"
 
libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  filters,
  "postgresql" % "postgresql" % "9.3-1100-jdbc41",
  "org.pac4j" % "play-pac4j_java" % "1.2.0",
  "org.pac4j" % "pac4j-http" % "1.5.0",
  "org.pac4j" % "pac4j-cas" % "1.5.0",
  "org.pac4j" % "pac4j-openid" % "1.5.0",
  "org.pac4j" % "pac4j-oauth" % "1.5.0",
  "org.pac4j" % "pac4j-saml" % "1.5.0"
)
 
resolvers ++= Seq(
    "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/"
)
 
play.Project.playJavaSettings
