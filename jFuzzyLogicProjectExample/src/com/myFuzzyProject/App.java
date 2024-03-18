package com.myFuzzyProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener {

    JLabel lblTitulo = new JLabel("Sistema difuzo");
    JLabel lblSubTitulo = new JLabel("recomendador de peliculas");
    JLabel lblAno = new JLabel("Año:");
    JLabel lblGenero = new JLabel("Genero:");
    JLabel lblCalificacion = new JLabel("Calificacion:");
    JLabel lblResultado = new JLabel("Resultado:");
    JTextArea txtAno = new JTextArea();
    JTextArea txtGenero = new JTextArea();
    JTextArea txtCalificacion = new JTextArea();
    JTextArea txtResultado = new JTextArea();
    JButton btnPrediccion = new JButton("Prediccion");
    JButton btnGrafica = new JButton("Grafica");
    JButton btnHistorial = new JButton("Historial");

    public App() {
        setTitle("App");
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        initComponents();
        btnPrediccion.addActionListener(this);
        btnGrafica.addActionListener(this);
        btnHistorial.addActionListener(this);
    }

    private void initComponents() {
        Font font = new Font("Arial", Font.BOLD, 16); // Font más grande

        lblTitulo.setBounds(320, 30, 150, 20);
        lblTitulo.setFont(font); // Aplicar el nuevo font
        lblSubTitulo.setBounds(260, 60, 250, 20);
        lblSubTitulo.setFont(font); // Aplicar el nuevo font
        lblCalificacion.setBounds(150, 130, 100, 20);
        lblCalificacion.setFont(font); // Aplicar el nuevo font
        txtCalificacion.setBounds(150, 170, 150, 20);
        txtCalificacion.setFont(font); // Aplicar el nuevo font
        lblGenero.setBounds(150, 200, 100, 20);
        lblGenero.setFont(font); // Aplicar el nuevo font
        txtGenero.setBounds(150, 240, 150, 20);
        txtGenero.setFont(font); // Aplicar el nuevo font
        lblAno.setBounds(150, 270, 100, 20);
        lblAno.setFont(font); // Aplicar el nuevo font
        txtAno.setBounds(150, 310, 150, 20);
        txtAno.setFont(font); // Aplicar el nuevo font
        btnPrediccion.setBounds(450, 210, 150, 30);
        btnPrediccion.setFont(font); // Aplicar el nuevo font
        btnGrafica.setBounds(450, 260, 150, 30);
        btnGrafica.setFont(font); // Aplicar el nuevo font
        btnHistorial.setBounds(450, 310, 150, 30);
        btnHistorial.setFont(font); // Aplicar el nuevo font
        lblResultado.setBounds(450, 130, 100, 20);
        lblResultado.setFont(font); // Aplicar el nuevo font
        txtResultado.setBounds(450, 170, 150, 20);
        txtResultado.setFont(font); // Aplicar el nuevo font

        add(lblTitulo);
        add(lblSubTitulo);
        add(lblCalificacion);
        add(txtCalificacion);
        add(lblGenero);
        add(txtGenero);
        add(lblAno);
        add(txtAno);
        add(btnPrediccion);
        add(btnGrafica);
        add(btnHistorial);
        add(lblResultado);
        add(txtResultado);
    }

    public static void main(String[] args) {
        new App();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPrediccion) {
            txtResultado
                    .setText(Prediccion.recomendar(txtCalificacion.getText(), txtGenero.getText(), txtAno.getText()));

        }
        if (e.getSource() == btnGrafica) {
            /*
             * Prediccion.grafica(txtCalificacion.getText(), txtGenero.getText(),
             * txtAno.getText(),
             * txtResultado.getText());
             */
            Prediccion.grafica(txtCalificacion.getText(), txtGenero.getText(), txtAno.getText(),
                    txtResultado.getText());
        }
        if (e.getSource() == btnHistorial) {
            new frmHistorial();
        }
    }
}