package Server;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.lang.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.io.*;
import java.util.Random;

import java.net.*;



class MyCanvas extends JComponent {
    double sqrWdth, sqrHght;
    Color blankColor = new Color(0,0,0);
    Color caseColor = new Color(100, 100, 100);
    Color revealedCash = new Color(200, 50, 100);
    Color availableCash = new Color(100, 200, 50);
    Color background = new Color(50, 50, 200);
    Color deal = new Color(120, 100, 50);

    Game game;

    public MyCanvas(Game set) {
        game = set;
        sqrWdth = 75.0;
        sqrHght = 75.0;
    }

    public void updatePosition() {

    }

    public void paint(Graphics g) {
        for (int x = 0; x < game.width; x++) {
            for (int y = 0; y < game.height; y++) {
                if (game.stageSet[x][y] == 0) {
                    g.setColor(blankColor);
                    g.fillRect((int) (x * sqrWdth), (int) (y * sqrHght), (int) sqrWdth, (int) sqrHght);
                } else if (game.stageSet[x][y] == 2) {
                    g.setColor(revealedCash);
                    g.fillRect((int) (x * sqrWdth), (int) (y * sqrHght), (int) sqrWdth, (int) sqrHght);
                } else if (game.stageSet[x][y] == 3) {
                    g.setColor(availableCash);
                    g.fillRect((int) (x * sqrWdth), (int) (y * sqrHght), (int) sqrWdth, (int) sqrHght);
                } else if (game.stageSet[x][y] == 4) {
                    g.setColor(deal);
                    g.fillRect((int) (x * sqrWdth), (int) (y * sqrHght), (int) sqrWdth, (int) sqrHght);
                } else {
                    g.setColor(caseColor);
                    g.fillRect((int) (x * sqrWdth), (int) (y * sqrHght), (int) sqrWdth, (int) sqrHght);
                }
            }
        }
        for (int[] key : game.textStart.keySet()) {
            String value = game.textStart.get(key);
            g.setColor(new Color(255, 255, 255));
            g.drawString(value, (int) (key[1]*sqrWdth) + 20, (int) (key[0]*sqrHght*1.25));
        }
    }
}

public class DealOrNoDealGame extends JFrame {
    public static final int NO_DEAL = 0;
    public static final int DEAL = 1;

    Color backgroundColor = new Color(230,230,230);
    static MyCanvas canvas;
    Game game;
    ServerSocket serverSocket;
    Socket clientSocket;
    PrintWriter sout;
    BufferedReader sin;

    public DealOrNoDealGame(String fnombre) {
        initCaseValues();

        game = new Game(fnombre);
        int width = 500;
        int height = 500;

        int bar = 20;
        setSize(width,height+bar);
        getContentPane().setBackground(backgroundColor);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, width, height+bar);
        canvas = new MyCanvas(game);
        getContentPane().add(canvas);

        setVisible(true);
        setTitle("Deal Or No Deal Agent");

        getConnection(3333, fnombre);
        survive();
    }

    private void getConnection(int port, String fnombre) {
        System.out.println("Set up the connection:" + port);

        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            sout = new PrintWriter(clientSocket.getOutputStream(), true);
            sin = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("Connection established.");
            sout.println(fnombre);
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }

    void initCaseValues() {

    }

    void makeDeal() {

    }

    void chooseCase() {

    }

    String getSonarReadings() {
        return "";
    }

    void survive() {

    }


    public static void main(String[] args) {
        DealOrNoDealGame game = new DealOrNoDealGame(args[0]);
    }
}

