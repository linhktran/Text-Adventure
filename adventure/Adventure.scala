package o1.adventure



/** The class `Adventure` represents text adventure games. An adventure consists of a player and
  * a number of areas that make up the game world. It provides methods for playing the game one
  * turn at a time and for checking the state of the game.
  *
  * N.B. This version of the class has a lot of "hard-coded" information which pertain to a very
  * specific adventure game that involves a small trip through a twisted forest. All newly created
  * instances of class `Adventure` are identical to each other. To create other kinds of adventure
  * games, you will need to modify or replace the source code of this class. */
class Adventure {

  /** The title of the adventure game. */
  val title = "The Wheel of Fortune"

  private val apple       = new Item("Apple",       "The Great Sin."                                                              )
  private val coconut     = new Item("Coconut",     "Even darkness cannot besmear its whiteness. "                                )
  private val fig         = new Item("Fig",         "Our King's protecion."                                                       )
  private val mango       = new Item("Mango",       "It is said to bring prosperity and happiness."                               )
  private val pomegranate = new Item("Pomegranate", "The only legend I have ever loved is \nthe story of a daughter lost in hell.")


  private val fool    = new Item("The Fool",    "An innocent soul who is always in the palm of Fate's hand."                                   )
  private val emperor = new Item("The Emperor", "His highness possesses everything but empathy."                                               )
  private val lovers  = new Item("The Lovers",  "My love for you stands the test of time, \nUntil all the light is but dime."            )
  private val hermit  = new Item("The Hermit",  "The lone ranger searching for his truest self."                                               )
  private val devil   = new Item("The Devil",   "This card has every evil power inside it.\nBut at the same time, it is the symbol of freedom.")


  private val JCdes = "I cannot tell what you and other men" +
                      "\nThink of this life; but, for my single self," +
                      "\nI had as lief not be as live to be" +
                      "\nIn awe of such a thing as I myself."
  private val H4des = "I will redeem all this on Percy's head," +
                      "\nAnd, in the closing of some glorious day," +
                      "\nBe bold to tell you that I am your son," +
                      "\nWhen I will wear a garment all of blood" +
                      "\nAnd stain my favors in a bloody mask," +
                      "\nWhich, washed away, shall scour my shame with it."
  private val Hdes  = "Doubt thou the stars are fire," +
                      "\nDoubt that the sun doth move" +
                      "\nDoubt that to be a liar" +
                      "\nBut never doubt I love."
  private val Odes  =  "I hold my peace, sir? no;" +
                      "\nNo, I will speak as liberal as the north," +
                      "\nLet heaven and men and devils, let them all," +
                      "\nAll, all, cry shame against me, yet I'll speak."
  private val Mdes  = "Out, out, brief candle! Life is but a walking shadow," +
                      "\na poor player that struts and frets his hour upon the stage" +
                      "\nand then is heard no more." +
                      "\nIt is a tale told by an idiot," +
                      "\nfull of sound and fury," +
                      "\nSignifying nothing."
  private val EGdes = "A paradise was lost that day" +
                      "\nThe stolen must be returned in its place."


  private val Start        = new Area("Elysium",       "The final resting place of the souls of the heroic and the virtuous",              None,          None,              None       )
  private val JuliusCeasar = new Area("Julius Ceasar", JCdes,                                                                              Some(hermit),  Some(fig),         Some(false))
  private val Alfheim      = new Area("Alfheim",       "Land of elves, where birds would sing and flowers would bloom",                    None,          None,              None       )
  private val Atlantis     = new Area("Atlantis",      "The legendary lost continent, six feet under of the world",                        None,          None,              None       )
  private val HenryIV      = new Area("Henry IV",      H4des,                                                                              Some(emperor), Some(pomegranate), Some(false))
  private val Limbo        = new Area("Limbo",         "The border place between heaven and hell",                                         None,          None,              None       )
  private val Hamlet       = new Area("Hamlet",        Hdes,                                                                               Some(lovers),  Some(apple),       Some(false))
  private val Camelot      = new Area("Camelot",       "Where our King once reigned, where the Sun never sets",                            None,          None,              None       )
  private val Diyu         = new Area("Diyu",          "The realm of the dead",                                                            None,          Some(pomegranate), Some(false))
  private val Niflheim     = new Area("Niflheim",      "Where the coldness is in its prime, \nWhere the only warmth is the blood of thou", None,          None,              None       )
  private val Mahoroba     = new Area("Mahoroba",      "A land full of bliss and peace",                                                   None,          None,              None       )
  private val Othello      = new Area("Othello",       Odes,                                                                               Some(devil),   Some(coconut),     Some(false))
  private val Avalon       = new Area("Avalon",        "The paradise where our King had his eternal sleep",                                None,          None,              None       )
  private val Macbeth      = new Area("Macbeth",       Mdes,                                                                               Some(fool),    Some(mango),       Some(false))
  private val EdenGarden   = new Area("Eden Garden",   EGdes,                                                                              None,          Some(apple),       Some(false))


         Start.setNeighbors(Vector(                                                                          "west" -> JuliusCeasar                              ))
  JuliusCeasar.setNeighbors(Vector("north" -> Atlantis,     "east" -> Start,        "south" -> Alfheim,      "west" -> Limbo                                     ))
       Alfheim.setNeighbors(Vector("north" -> JuliusCeasar,                         "south" -> Niflheim                                                          ))
      Atlantis.setNeighbors(Vector("north" -> Atlantis,     "east" -> HenryIV,      "south" -> JuliusCeasar, "west" -> Mahoroba                                  ))
       HenryIV.setNeighbors(Vector(                                                                          "west" -> Atlantis                                  ))
         Limbo.setNeighbors(Vector(                         "east" -> JuliusCeasar, "south" -> Hamlet,       "west" -> Niflheim, "up" -> Camelot, "down" -> Diyu ))
        Hamlet.setNeighbors(Vector("north" -> Limbo                                                                                                              ))
       Camelot.setNeighbors(Vector(                                                                                                               "down" -> Limbo))
          Diyu.setNeighbors(Vector(                                                                                              "up" -> Limbo                   ))
      Niflheim.setNeighbors(Vector("north" -> Mahoroba,     "east" -> Limbo,        "south" -> Alfheim,      "west" -> Macbeth                                   ))
      Mahoroba.setNeighbors(Vector("north" -> Mahoroba,     "east" -> Atlantis,     "south" -> Niflheim,     "west" -> Othello                                   ))
       Othello.setNeighbors(Vector(                         "east" -> Mahoroba,     "south" -> Avalon                                                            ))
        Avalon.setNeighbors(Vector("north" -> Othello,                              "south" -> Macbeth,      "west" -> EdenGarden                                ))
       Macbeth.setNeighbors(Vector("north" -> Avalon,       "east" -> Niflheim                                                                                   ))
    EdenGarden.setNeighbors(Vector(                         "east" -> Avalon                                                                                     ))


  Atlantis.addItem(lovers)
   Alfheim.addItem(fool)
   Camelot.addItem(emperor)
      Diyu.addItem(devil)
  Niflheim.addItem(hermit)

  /** The character that the player controls in the game. */
  val player = new Player(Start)

  /** The number of turns that have passed since the start of the game. */
  var turnCount = 0
  /** The maximum number of turns that this adventure game allows before time runs out. */
  val timeLimit = 99


  def special() = {
    val gates = Vector(JuliusCeasar, Hamlet, HenryIV, Macbeth, Othello)
    if (gates.forall(_.state == "Open")) {
    EdenGarden.consume()
    }
  }


  /** Determines if the adventure is complete, that is, if the player has won. */
  def isComplete = {
    this.EdenGarden.fruit.isEmpty
  }

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver = this.isComplete || this.player.hasQuit || this.turnCount == this.timeLimit

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage = "What are you waiting, the chosen one?" +
                       "\nWe have been here, at the end of this world, longing for you" +
                       "\nSince the dawn of this civilization."


  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage = {
    if (this.isComplete)
      "Finally... It is what it is."
    else if (this.turnCount == this.timeLimit)
      "Seems like you are not our destined hero."
    else  // game over due to player quitting
      "You have fallen into eternal darkness."
  }


  /** Plays a turn by executing the given in-game command, such as "go west". Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String) = {
    val action = new Action(command)
    val outcomeReport = action.execute(this.player)
    if (outcomeReport.isDefined) {
      this.special()
      this.turnCount += 1
    }
    outcomeReport.getOrElse("Unknown command: \"" + command + "\".")
  }


}

