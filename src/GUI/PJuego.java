package GUI;

import data.Juego;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author alber
 */
public class PJuego extends javax.swing.JPanel {
    Juego juego;
    public int numJugador;
    public boolean turno1 = true;
    public JLabel JugadorActual;
    private JPanel PanelConecta4;
    private JButton botonVolver;
    JPanel casillas[][];
    VPal v;
    public PJuego(VPal v, Juego juego) {
        this.juego = juego;
        numJugador = 1;
        this.v = v;
        initComponents();
    }
    
//***************************************INIT COMPONENTS EMPIEZA AQUI**************************************************
    private void initComponents() {
        //********************************DECLARACION DE VARIABLES**********************************************
        PanelConecta4 = new JPanel();
        casillas = new JPanel[6][6];
        PanelConecta4 = new javax.swing.JPanel();
        botonVolver = new javax.swing.JButton();
        botonVolver.setFont(new Font("Arial", Font.BOLD , 42));
        JugadorActual = new javax.swing.JLabel("JUGADOR ACTUAL: Jugador " + numJugador);
        JugadorActual.setFont(new Font("Arial", Font.BOLD , 22));
        JugadorActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //********************************FIN DECLARACION DE VARIABLES**********************************************
        
        //***METEMOS LAYOUTS AL PANEL GENERAL Y AL PANEL DE LAS CASILLAS*****************************************
        setLayout(new java.awt.BorderLayout());
        PanelConecta4.setLayout(new java.awt.GridLayout(6, 6));
        //hacemos todo el bucle de juego en la funcion generarTablero
        juego.generarTablero();
        //añadimos el panel del juego a la parte de arriba del panel
        add(PanelConecta4, java.awt.BorderLayout.CENTER);
        
        //luego montamos el boton de volver con su accion correspondiente y lo añadimos abajo
        botonVolver.setText("Volver");
        botonVolver.addActionListener((java.awt.event.ActionEvent evt) -> {
            botonVolverActionPerformed();
        });
        add(botonVolver, java.awt.BorderLayout.SOUTH);
        add(JugadorActual, java.awt.BorderLayout.NORTH);
        
        generarJuego();
    }
   //***************************************INIT COMPONENTS ACABA AQUI**************************************************
    
    private void botonVolverActionPerformed() {                                            
        //reiniciamos el juego aqui por si dejamos una partida a medio empezar que la proxima vez que abramos este limpio de nuevo
        juego.reiniciarJuego();
        v.setContentPane(v.ppal);
        //IMPORTANTE EL REVALIDATE QUE SI NO NO SE CAMBIA EL PANEL ***************************************************
        v.revalidate();
        this.actualizarTablero();
        this.actualizarLabelTurno();
        // si sale se reinicia tambien el turno al inicial
        turno1 = true;
    }
    
    /**
     * Metodo grande que se encarga del ciclo de juego en el modo gráfico
     */
    public void generarJuego(){
        //primero generamos el tablero visualmente con paneles para que quede algo más bonito
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                casillas[i][j] = new JPanel();
                casillas[i][j].setBorder(new LineBorder(Color.BLACK, 3));
                this.PanelConecta4.add(casillas[i][j]);
                /*le tenemos que dar un valor a la columna en este caso para que luego el evento no haya perdido el valor
                de la columna a la que se refiere*/
                final int columna = j;
                /*En este evento podemos meter una ficha. Despues de meter una ficha,
                siempre comprueba si hay un ganador o empate, y si no cambia de turno*/
                casillas[i][j].addMouseListener(new java.awt.event.MouseAdapter(){
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e){
                        if(juego.insertarFicha(columna)){
                            actualizarTablero();
                            
                            if(juego.hayGanador()){
                                if(juego.isTurnoJugador1()){
                                    JOptionPane.showMessageDialog(null,"GANADOR EL JUGADOR 1 EEEEEE");
                                    juego.reiniciarJuego();
                                    actualizarTablero();
                                    actualizarLabelTurno();
                                }
                                else{
                                    JOptionPane.showMessageDialog(null,"GANADOR EL JUGADOR 2 EEEEEE"); 
                                    juego.reiniciarJuego();
                                    actualizarTablero();
                                    actualizarLabelTurno();
                                    
                                }
                            }
                            else if(juego.tableroLleno()){
                                JOptionPane.showMessageDialog(null, "EMPATEEEEEEEEEEEEE");
                                juego.reiniciarJuego();
                                actualizarTablero();
                                actualizarLabelTurno(); 
                            }
                            else{
                                juego.cambiarTurno();
                                actualizarLabelTurno();
                            }
                            
                        }
                    }
                });
            }
        }
    }
    /*hacemos este método porque si no cuando metemos una ficha no se actualizaría.
    y guardamos los datos del tablero en otro array aquí, para tener como una especie de reflejo del tablero
    que luego podremos utilizar para pintar las casillas del color que queramos mediante un switch*/
    public void actualizarTablero(){
        int [][] tablero = juego.getTablero();
        for (int filas = 0; filas < tablero.length; filas++) {
            for (int columnas = 0; columnas < tablero.length; columnas++) {
                //guardamos el numero de la casilla en una variable para el switch
                int valor = tablero[filas][columnas];
                switch(valor){
                    case 0 -> casillas[filas][columnas].setBackground(Color.white);
                    case 1 -> casillas[filas][columnas].setBackground(Color.red);
                    case 2 -> casillas[filas][columnas].setBackground(Color.blue);
                }
            }
        }
    }
    //esto lo hacemos para cambiar la etiqueta de arriba del turno
    public void actualizarLabelTurno(){
        if(juego.isTurnoJugador1())
            JugadorActual.setText("JUGADOR ACTUAL: Jugador " + 1);
        else
            JugadorActual.setText("JUGADOR ACTUAL: Jugador " + 2);
        revalidate();
    }
}








//*******************EL LIMBO DE LO QUE NO VALE**********************


    /*
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel3EnRaya = new javax.swing.JPanel();
        casilla1 = new javax.swing.JButton();
        casilla2 = new javax.swing.JButton();
        casilla3 = new javax.swing.JButton();
        casilla4 = new javax.swing.JButton();
        casilla5 = new javax.swing.JButton();
        casilla6 = new javax.swing.JButton();
        casilla7 = new javax.swing.JButton();
        casilla8 = new javax.swing.JButton();
        casilla9 = new javax.swing.JButton();
        botonVolver = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout(2, 1, 0, 18));

        Panel3EnRaya.setLayout(new java.awt.GridLayout(3, 3));

        casilla1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                casilla1ActionPerformed(evt);
            }
        });
        Panel3EnRaya.add(casilla1);
        Panel3EnRaya.add(casilla2);
        Panel3EnRaya.add(casilla3);
        Panel3EnRaya.add(casilla4);
        Panel3EnRaya.add(casilla5);
        Panel3EnRaya.add(casilla6);
        Panel3EnRaya.add(casilla7);
        Panel3EnRaya.add(casilla8);
        Panel3EnRaya.add(casilla9);

        add(Panel3EnRaya);

        botonVolver.setText("Volver");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });
        add(botonVolver);
    }// </editor-fold>//GEN-END:initComponents

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        v.setContentPane(v.ppal);
        //IMPORTANTE EL REVALIDATE QUE SI NO NO SE CAMBIA EL PANEL ***************************************************
        v.revalidate();
    }//GEN-LAST:event_botonVolverActionPerformed

    private void casilla1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_casilla1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_casilla1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel3EnRaya;
    private javax.swing.JButton botonVolver;
    private javax.swing.JButton casilla1;
    private javax.swing.JButton casilla2;
    private javax.swing.JButton casilla3;
    private javax.swing.JButton casilla4;
    private javax.swing.JButton casilla5;
    private javax.swing.JButton casilla6;
    private javax.swing.JButton casilla7;
    private javax.swing.JButton casilla8;
    private javax.swing.JButton casilla9;
    // End of variables declaration//GEN-END:variables
*/


