lazy val commonSettings = Seq(
  scalaVersion := "2.11.6"
)

// excludeFilter in Compile in unmanagedSources := "ClassMain.scala" || "WindowGame.scala"

excludeFilter in Compile in unmanagedSources := "*.scala"
