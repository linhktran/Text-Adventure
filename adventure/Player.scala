package o1.adventure

import scala.collection.mutable.Map
import scala.io.StdIn.readLine


/** A `Player` object represents a player character controlled by the real-life user of the program.
  *
  * A player object's state is mutable: the player's location and possessions can change, for instance.
  *
  * @param startingArea  the initial location of the player */
class Player(startingArea: Area) {

  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  private val items = Map[String, Item]()


  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven


  /** Returns the current location of the player. */
  def location = this.currentLocation


  /** Attempts to move the player in the given direction. This is successful if there
    * is an exit from the player's current location towards the direction name. Returns
    * a description of the result: "You go DIRECTION." or "You can't go DIRECTION." */
  def go(direction: String) = {
    val destination = this.location.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    if (destination.isDefined) "You go " + direction + "." else "You can't go " + direction + "."
  }


  /** Causes the player to rest for a short while (this has no substantial effect in game terms).
    * Returns a description of what happened. */
  def rest() = {
    "You rest for a while. Better get a move on, though."
  }


  /** Signals that the player wants to quit the game. Returns a description of what happened within
    * the game as a result (which is the empty string, in this case). */
  def quit() = {
    this.quitCommandGiven = true
    ""
  }

  def get(itemName : String) : String = {
    def removed = this.currentLocation.removeItem(itemName)
    if (this.currentLocation.contains(itemName)) {
      val item = removed.get
      this.items += item.name -> item
      "You pick up " + itemName + "."
    } else "There is no " + itemName + " here to pick up."
  }

  def drop(itemName : String) : String = {
    if (this.items.keys.toVector.contains(itemName)) {
      this.currentLocation.addItem(this.items.remove(itemName).get)
      this.items.remove(itemName)
      "You drop " + itemName + "."
    } else "You don't have that!"
  }

  def examine(itemName : String) : String = {
    if (this.items.keys.toVector.contains(itemName)) {
    s"You look closely at $itemName.\n${this.items(itemName).description}"
    } else "If you want to examine something, you need to pick it up first."
  }

  def use(itemName : String) = {
    if (this.items.keys.toVector.contains(itemName)) {
      val usable = Vector("The Devil", "The Emperor", "The Fool", "The Hermit", "The Lovers")
      if (usable.contains(itemName)) {
        val suitable = this.currentLocation.card
        if (suitable.isDefined && suitable.get.name == itemName) {
          this.items.remove(itemName) match {
            case Some(value) => {
              this.items += this.currentLocation.fruit.get.name -> this.currentLocation.fruit.get
              this.currentLocation.consume()
              s"You are now granted the ${this.currentLocation.fruit.get.name}."
            }
            case None => "This is not allowed to use here."
          }
        } else "You are unable to use that here."
      } else "You cannot use that."
    } else "If you want to use something, you need to pick it up first."
  }

  def trade(itemName : String) = {
    if (this.currentLocation.fullDescription.contains("Trade")) {
      this.items.remove(itemName) match {
        case Some(item) => {
          if (item == this.currentLocation.fruit.get) {
            this.currentLocation.consume()
            "The trade was successful."
          } else "The trade was unsuccessful."
        }
        case None => "You dare come here empty-handed?"
      }
    } else "There is no trade offer available."
  }

    def eat(itemName : String) : String = {
    val edible = Vector("Apple", "Coconut", "Fig", "Mango", "Pomegranate")
    if (edible.contains(itemName)) {
      this.items.remove(itemName) match {
        case Some(fruit) => "The fruit tastes yummy."
        case None        => "You do not have that fruit!"
      }
    } else "You cannot eat that!"
  }

  def choose() = {
    if (this.currentLocation.name == "Eden Garden") {
      val answer = readLine("So what is your choice? ")
      if (this.items.keys.toVector.contains(answer)) {
        if (answer == this.currentLocation.fruit.get.name) {
          this.currentLocation.pass()
          "Ah... Our long lost treasure."
        } else "Your journey has not ended yet, our hero."
      } else "You need to possess the fruit before you can choose."
    } else "There is nothing that you can choose."
  }

  def has(itemName : String) : Boolean = this.items.keys.toVector.contains(itemName)

  def inventory : String = {
    if (this.items != Map()) {
      var state = "You are carrying:"
      for (each <- this.items.keys.toVector) {
        state += "\n" + s"$each"
      }
      state
    } else "You are empty-handed."
  }

  def help : String = {
      "* \"go\" + direction   : direct the character to the position." +
    "\n* \"rest\"             : make the character rest." +
    "\n* \"eat\" + fruit      : the character would eat the fruit if he has it." +
    "\n* \"quit\"             : the character quits and the game ends." +
    "\n* \"inventory\"        : open the inventory of the character." +
    "\n* \"get\" + object     : pick up the object and put it into the inventory." +
    "\n* \"use\" + object     : use the object where possible." +
    "\n* \"trade\" + object   : trade the object where possible." +
    "\n* \"examine\" + object : examine the object to see its decription." +
    "\n* \"drop\" + object    : drop the object if it is currently in the character's inventory." +
    "\n* \"choose\"           : choose something when the time has come." +
    "\n* There are 2 kinds of objects available: Card and Fruit, and the objects must be typed correctly."
  }


  /** Returns a brief description of the player's state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name


}


