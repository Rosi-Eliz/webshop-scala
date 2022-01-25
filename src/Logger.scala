trait Logger {
  def logAction(actionName: String, name: String): Unit = {
    println(s"$actionName $name");
  }
}
