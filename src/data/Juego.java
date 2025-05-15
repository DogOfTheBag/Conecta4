package data;

/**
 *
 * @author alber
 */
public class Juego {
    private int tablero[][];
    private int jugadorActual;
    private boolean turnoJugador1;
    private int columna;
//al crear el juego automaticamente creamos el tablero y asignamos los turnos iniciales
    public Juego() {
        tablero = new int [6][6];
        jugadorActual = 1;
        turnoJugador1 = true;
        
    }
    //FILAS SERIA LAS RAYAS EN HORIZONTAL DE ARRIBA A ABAJO, COLUMNAS LAS LINEAS VERTICALES DE IZQUIERDA A DERECHA
    //le damos un valor inicial de 0 a todas las casillas, ya veremos despues que 0 es vacio, 1 es J1 y 2 J2
    public void generarTablero(){
        for (int filas = 0; filas < 6; filas++) {
            for (int columnas = 0; columnas < 6; columnas++) {
                tablero[filas][columnas] = 0;
            }
        }
    }
    public boolean insertarFicha(int columna){
     //puesto que en el conecta 4 solo podemos poner la ficha en una columna, en la zona mas baja, solo necesitaremos la columna
        for (int fila = 5; fila >= 0; fila--) {
            if(tablero[fila][columna] == 0){
                tablero[fila][columna] = jugadorActual;
                return true;
            } //basicamente eso, seleccionas una fila, busca la columna más baja y coloca la ficha del jugador que sea ahora.
        }
        return false;
        //esta funcion comprueba que la columna indicada tiene un hueco para meter ficha
        //si tiene hueco la mete y retorna true para indicar que la metio, si no false.
    }
    //funcion general con unas cuantas subfunciones que comprueban la victoria, si alguna retorna true el jugador gana la partida
    public boolean hayGanador(){
    if(filaIgual() || columnaIgual() || diagonalAscIgual() || diagonalDescIgual())
        return true;
    return false;
    }
    /*Esta funcion usa un contador que, si llegamos a 4 fichas iguales, retorna un true
    empezamos contando las filas desde abajo y las columnas de izquierda a derecha
    si salta a una nueva fila se reinicia el contador a 0 para que asi no cuente 4 seguidas con salto de linea*/
    private boolean filaIgual() {
        int contadorFichasIguales;
        for (int filas = 5; filas >= 0; filas--) {
            contadorFichasIguales = 0;
            for (int columnas = 0; columnas < 6; columnas++) {
                if(tablero[filas][columnas] == this.jugadorActual)
                    contadorFichasIguales++;
                else
                    contadorFichasIguales = 0;
                if(contadorFichasIguales == 4)
                    return true;
            }
        }
        return false;
    }
    /*Practicamente igual que la otra pero a la inversa, va de abajo a arriba, izquierda a derecha
    y comprueba, si salta a otra columna el contador se pone en 0*/
    private boolean columnaIgual() {
        int contadorFichasIguales;
        for (int columnas = 0; columnas < 6; columnas++) {
            contadorFichasIguales = 0;
            for (int filas = 0; filas < 6; filas++) {
                if(tablero[filas][columnas] == this.jugadorActual)
                    contadorFichasIguales++;
                else
                    contadorFichasIguales = 0;
                if(contadorFichasIguales == 4)
                    return true;
            }
        }
        return false;
    }
    /*esta ha sido algo más complicada de hacer, basicamente coges una linea de abajo a arriba
    por eso las filas son negativas, y si la posicion en diagonal es igual pues te devuelve el true
    el problema de esta funcion es establecer los limites de comprobacion para que no se salga del array y te pare el programa
    para ello, cojo la ultima posicion a la que puedes llegar sin salirte del array (puesto que son 4 pues es el 3), ya que sabemos que
    a partir de ahi no va a haber más de 4 seguidos, no hay mas huecos*/
    private boolean diagonalAscIgual() {
        for (int filas = 5; filas >= 3; filas--) {
            for (int columnas = 0; columnas < 3; columnas++) {
                if (tablero[filas][columnas] == this.jugadorActual &&
                    tablero[filas - 1][columnas + 1] == this.jugadorActual &&
                    tablero[filas - 2][columnas + 2] == this.jugadorActual &&
                    tablero[filas - 3][columnas + 3] == this.jugadorActual)
                    return true;
            }
        }
        return false;
    }
    /*Igual que la anterior, pero ahora recorre las lineas de arriba a abajo, izquierda a derecha
    mismos requisitos que la anterior*/
    private boolean diagonalDescIgual() {
          for (int filas = 0; filas < 3; filas++) {
            for (int columnas = 5; columnas >= 3; columnas--) {
                if (tablero[filas][columnas] == this.jugadorActual &&
                    tablero[filas + 1][columnas - 1] == this.jugadorActual &&
                    tablero[filas + 2][columnas - 2] == this.jugadorActual &&
                    tablero[filas + 3][columnas - 3] == this.jugadorActual)
                    return true;
            }
        }
        return false;
    }
    //te comprueba si el tablero esta lleno para ver si hay empate
    public boolean tableroLleno(){
        for (int filas = 0; filas < 6; filas++) {
            for (int columnas = 0; columnas < 6; columnas++) {
                if(tablero[filas][columnas] == 0)
                    return false;
            }
        }
        return true;
    }
    //reinicia el tablero y el turno
    public void reiniciarJuego(){
        jugadorActual = 1;
        turnoJugador1 = true;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j] = 0;
                
            }
            
        }
    }
    
    public void cambiarTurno(){
        if(turnoJugador1){
            jugadorActual = 2;
            turnoJugador1 = !turnoJugador1;
        }
        else{
            jugadorActual = 1;
            turnoJugador1 = !turnoJugador1;
        }
        
    }

    public int[][] getTablero() {
        return tablero;
    }

    public int getJugadorActual() {
        return jugadorActual;
    }

    public boolean isTurnoJugador1() {
        return turnoJugador1;
    }
}
