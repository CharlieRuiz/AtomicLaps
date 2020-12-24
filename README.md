# AtomicLaps
Zombies v Staff

Escogí Java para la realización de este problema porque siempre tuve en la mente hacer uso de gráficos y ya había utilizado java para esto.

Empecé creando el mapa, delimitando que iba y no ha ser una pared, después generé los puntos en los cuales los humanos iban a aparecer en el mapa, de la misma manera se delimitó el área en el que los zombies empezarían.

Para el movimiento de los humanos opté por darle prioridad a el desplazamiento hacia abajo y a la derecha, con el problema de que los trabajadores del cuadrante de arriba a la derecha se quedarán atorados, para eso cuando los humanos cuando llegan a cierta x, y toma prioridad el moverse a la izquierda.

Con los zombies fue más fácil ya que ellos se mueven de manera random, para hacerlo un poco más interesante y que se pueda dar el caso de que pueda existir un contagio decidí cancelar el movimiento hacia arriba de los zombies. Cuando un zombie tiene un humano en una casilla adyacente el zombie contagia al humano.
