class Database extends Warenkorb{
  private var items: Array[StoreItem] = Array()
  override def delete(id: Int): Array[StoreItem] = {
    var item = items.find(item => item.id == id)
    if(item.isDefined)
    {
      var itemObject = item.get
      itemObject.logAction(itemObject.name, "gelöscht")
      items = items.filter(item => item.id != id)
    } else {
      println(id.toString + " not found")
    }
    items
  }

  override def search(name: String): Array[StoreItem] = {
    var matchingItems = items.filter(item => item.name.equals(name))
    if(matchingItems.isEmpty)
    {
      println(name + " nicht gefunden")
    } else {
      for (item <- matchingItems) {
        item.logAction(item.name, "gefunden")
      }
    }
    matchingItems
  }

  override def store(item: StoreItem): Array[StoreItem] = {
    items = items :+ item
    item.logAction(item.name, "gespeichert")
    items
  }

  override def sumUp(): Int = {
    items.map(item => item.value).sum
  }

  //Additional functionality:

  def higherOrderPrintFunction(items: StoreItem, printFunction: (String, String) => Unit): Unit = {
    printFunction(items.name, items.value.toString)
  }

  def higherThan(value: Int): Array[StoreItem] = {
    var matchingItems = items.filter(item => item.value > value)
    matchingItems.sortBy(item => item.value)(Ordering[Int].reverse)
    for(item <- matchingItems)
      {
        higherOrderPrintFunction(item, item.logAction)
      }
    matchingItems
  }

  def getStoreItems: Array[StoreItem] = {
    items
  }

  def filterByName(name: String, items: Array[StoreItem]): Array[StoreItem] = {
    var filteredItems = items.filter(item => item.name.equals(name))
    filteredItems.sortBy(item => item.value)
    for(item <- filteredItems)
      {
        higherOrderPrintFunction(item, item.logAction)
      }
    filteredItems
  }

}
