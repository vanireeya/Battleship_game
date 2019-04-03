##

# Battleship Game


**Submitted by : Reeya Vani**



Battleship is a war-themed board game for two players in which the opponents try to guess the location of the other&#39;s various ships.

**Understanding the game / How to play**

This game has to be played by at least two players (human vs human or human vs computer). The goal of this game is to sink all of your components ships. Each player has a grid of equal size where he/she can place his/her ships. The game will begin after both the players have placed their ships on the grid.

As the game begins, both the players take turns firing shots by calling out grid coordinates. The result of the attack is one of the following:

Hit, Miss, Already taken, Sunk, Win.

The first player to sink all of his/her opponent&#39;s ship wins the game.

**Steps to run the project**

(1) Install Java on your system - [link](https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_Howto.html)

(2) Unzip the given zip file

(3) Find the jar file inside the folder named Battleship.jar

(4) Open the command prompt or terminal in the location where this jar file is placed.

(5)  Run the command java -jar battleship.jar

**Proposed Approach**

Initially, the player is asked to choose one of the two modes **Human v/s Human** or **Human v/s Computer**. After that the user is asked to enter the number of rows and columns to decide the size of the grid. Further, the size of the grid decides the number of the ships. There can be three types : _small_ (covers one block), _medium_ (covers two blocks) and _big_ (covers three blocks).

For the sake of simplicity, I have set that there will always be 1 big ship, 2 medium ship in the game and the remaining all will be the small ships. Also, the blocks covered by all the ships together cannot exceed the half the number of total blocks in the grid. Next, the user is also asked to set the alignment of the big and medium ship, whether he/she want to place the ship _vertically_ or _horizontally_.  If the selected mode is Human v/s Computer, then the placement of the ship on computer&#39;s part is done randomly.

The actual game begins once both the players have placed their ships on their respective grids. Player 1 gets the first chance to attack the opponent. Each player has to first enter the X-coordinate and then the Y-coordinate that the player wants to target on the opponent&#39;s grid. After attacking, the player can see the outcome whether it was a hit or a miss. If it was a hit, that coordinate will be replaced with a &#39;X&#39; marker. Also, if the ship is sunk, a message is displayed that the particular ship was sunk.

This way both the players can view their opponents&#39; gird which will contain markers for all the blocks they hit. In this manner, the game goes on. If any player becomes successful in sinking all of the opponent&#39;s ships, the game ends there and that player wins the game. A message will be displayed at the end to convey which player won the game.

For playing this game again, run the same jar file and the game will start from beginning.

**Future Scope**

The code is done in a dynamic way to easily support future modifications and changes to code. For now, the number of big ships and medium ships is set to a static value, but this can be easily changed to support taking input from the user to set the numbers of those ships. Moreover, this is a two player game right now. However, more players can be added by simply taking user input to decide the number of players to be involved in this game.

**Working Demo**

Find the link to the working demo here:

[Demo](https://youtu.be/DqEhidxlLBg)