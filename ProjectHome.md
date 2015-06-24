a small 2d game made in java, with scalability in mind, in terms that the game can be transformed in something completely new with little effort.

The game is currently split up in 3 basic engines:
  * Graphics engine: will render any drawable object that was added to the pool
  * Collisions engine: will calculate collisions with other objects in the pool
  * Input engine: will pass input commands from the keyboard/mouse to the controllable object inserted in the engine.

Any class can implement the 3 different interfaces (Drawable,Collisionable, and Controllable) so that they can be inserted into the different engines. All of this interfaces are perishables, meaning that they can expire and be removed from the different engines when the object dies.

Future engines to be added:
  * Network engine: UDP based network engine to join or host a session
  * Sound engine: Dynamic sound engine to manipulate sounds (volume, position, pitch) in realtime
