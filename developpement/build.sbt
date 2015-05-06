lazy val commonSettings = Seq(
  scalaVersion := "2.11.6"
  )
  
lazy val root = (project in file(".")).
  settings(commonSettings: _*)
