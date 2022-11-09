package o1.adventure

import scala.collection.mutable.Map

/** The class `Area` represents locations in a text adventure game world. A game world
  * consists of areas. In general, an "area" can be pretty much anything: a room, a building,
  * an acre of forest, or something completely different. What different areas have in
  * common is that players can be located in them and that they can have exits leading to
  * other, neighboring areas. An area also has a name and a description.
  * @param name         the name of the area
  * @param description  a basic description of the area (typically not including information about items) */
class Area(var name: String, var description: String, val card : Option[Item], var fruit : Option[Item], var open : Option[Boolean]) {

  private val neighbors = Map[String, Area]()
  private val items = Map[String, Item]()


  /** Returns the area that can be reached from this area by moving in the given direction. The result
    * is returned in an `Option`; `None` is returned if there is no exit in the given direction. */
  def neighbor(direction: String) = this.neighbors.get(direction)

  def state = {
    this.open match {
      case Some(value) => if (value) "Open" else "Closed"
      case None =>
    }
  }


  /** Adds an exit from this area to the given area. The neighboring area is reached by moving in
    * the specified direction from this area. */
  def setNeighbor(direction: String, neighbor: Area) = {
    this.neighbors += direction -> neighbor
  }


  /** Adds exits from this area to the given areas. Calling this method is equivalent to calling
    * the `setNeighbor` method on each of the given direction--area pairs.
    * @param exits  contains pairs consisting of a direction and the neighboring area in that direction
    * @see [[setNeighbor]] */
  def setNeighbors(exits: Vector[(String, Area)]) = {
    this.neighbors ++= exits
  }


  /** Returns a multi-line description of the area as a player sees it. This includes a basic
    * description of the area as well as information about exits and items. The return
    * value has the form "DESCRIPTION\n\nExits available: DIRECTIONS SEPARATED BY SPACES".
    * The directions are listed in an arbitrary order. */
  def fullDescription = {
    val itemFound = if (!this.items.isEmpty) "\n\nYou see here: " + this.items.keys.mkString(" ") else ""
    val exitList = "\n\nExits available: " + this.neighbors.keys.mkString(" ")
    val available = {
      if (this.state == "Closed") "\n\nThere is a hole here that seems to fit a card."
      else if (this.state == "Open") "\n\nThe fruit here is already taken."
      else ""
    }
    if (this.name == "Diyu") {
      if (this.state != "Open") "Trade offer: \nIf you give me the Fruit of Gods, I will let you through.\nIf you give me the unwanted, it will become a part of this realm."
      else this.description + itemFound + exitList
    } else if (this.name == "Eden Garden") {
      if (this.state != "Open") "You need to collect all the fruits first." + exitList
      else this.description + itemFound + exitList
    } else this.description + available + itemFound + exitList
  }

  def consume() = {
    this.open = Some(true)
  }

  def addItem(item: Item) = {
    this.items += item.name -> item
  }

  def contains(itemName : String) = this.items.keys.toVector.contains(itemName)

  def removeItem(itemName : String) : Option[Item] = {
    if (this.contains(itemName)) {
      this.items.remove(itemName)
    }
    else None
  }

  def pass() = {
    if (this.name == "Eden Garden") this.fruit = None
  }


  /** Returns a single-line description of the area for debugging purposes. */
  override def toString = this.name + ": " + this.description.replaceAll("\n", " ").take(150)



}
