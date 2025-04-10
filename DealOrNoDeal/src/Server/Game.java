package Server;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.lang.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.math.*;
import java.text.*;

public class Game {
    int width = 14;
    int height = 11;
    int[][] stageSet;
    Map<int[], String> textStart = new HashMap<>();
    Map<Integer, int[]> caseValues = new HashMap<>();
    Map<Integer, Double> cashValues = new HashMap<>();

    Game(String setFile) {
        initializeCaseValues();
        try {
            FileReader fileReader = new FileReader("Sets/" + setFile);
            System.out.println();
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            stageSet = new int[width][height];
            for (int x = 0; x < height; x++) {
                String line = bufferedReader.readLine();
                for (int y = 0; y < width; y++) {
                    if (line.startsWith("0"))
                        stageSet[y][x] = 0;
                    else if (line.startsWith("2 "))
                        stageSet[y][x] = 2;
                    else if (line.startsWith("3 "))
                        stageSet[y][x] = 3;
                    else if (line.startsWith("4 "))
                        stageSet[y][x] = 4;
                    else
                        stageSet[y][x] = 1;
                    line = getValueAfterSpace(line, 0);
                    if (line.isEmpty()) {
                        break;
                    }
                }
            }
            String line = bufferedReader.readLine();
            String[] parts = line.split(" ", 2);
            String firstWord = parts[0];
            for (int z = 1; z <= 26; z++) {
                cashValues.put(Integer.valueOf(z), Double.valueOf(firstWord));
                if (parts.length < 2) {
                    break;
                }
                parts = parts[1].split(" ", 2);
                firstWord = parts[0];
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void initializeCaseValues() {
        caseValues.put(Integer.valueOf(1), new int[]{1, 1});
        caseValues.put(Integer.valueOf(2), new int[]{1, 2});
        caseValues.put(Integer.valueOf(3), new int[]{1, 3});
        caseValues.put(Integer.valueOf(4), new int[]{1, 4});
        caseValues.put(Integer.valueOf(5), new int[]{1, 5});
        caseValues.put(Integer.valueOf(6), new int[]{1, 6});
        caseValues.put(Integer.valueOf(7), new int[]{2, 1});
        caseValues.put(Integer.valueOf(8), new int[]{2, 2});
        caseValues.put(Integer.valueOf(9), new int[]{2, 3});
        caseValues.put(Integer.valueOf(10), new int[]{2, 4});
        caseValues.put(Integer.valueOf(11), new int[]{2, 5});
        caseValues.put(Integer.valueOf(12), new int[]{2, 6});
        caseValues.put(Integer.valueOf(13), new int[]{3, 2});
        caseValues.put(Integer.valueOf(14), new int[]{3, 3});
        caseValues.put(Integer.valueOf(15), new int[]{3, 4});
        caseValues.put(Integer.valueOf(16), new int[]{3, 5});
        caseValues.put(Integer.valueOf(17), new int[]{4, 2});
        caseValues.put(Integer.valueOf(18), new int[]{4, 3});
        caseValues.put(Integer.valueOf(19), new int[]{4, 4});
        caseValues.put(Integer.valueOf(20), new int[]{4, 5});
        caseValues.put(Integer.valueOf(21), new int[]{4, 2});
        caseValues.put(Integer.valueOf(22), new int[]{4, 3});
        caseValues.put(Integer.valueOf(23), new int[]{4, 4});
        caseValues.put(Integer.valueOf(24), new int[]{4, 5});
        caseValues.put(Integer.valueOf(25), new int[]{5, 3});
        caseValues.put(Integer.valueOf(26), new int[]{5, 4});

        for (int i = 1; i <= 26; i++) {
            textStart.put(caseValues.get(Integer.valueOf(i)), String.valueOf(i));
        }
        textStart.put(new int[]{1, 9}, "$0.01");
        textStart.put(new int[]{2, 9}, "$1");
        textStart.put(new int[]{3, 9}, "$5");
        textStart.put(new int[]{4, 9}, "$10");
        textStart.put(new int[]{5, 9}, "$25");
        textStart.put(new int[]{6, 9}, "$50");

        textStart.put(new int[]{1, 10}, "$75");
        textStart.put(new int[]{2, 10}, "$100");
        textStart.put(new int[]{3, 10}, "$200");
        textStart.put(new int[]{4, 10}, "$300");
        textStart.put(new int[]{5, 10}, "$400");
        textStart.put(new int[]{6, 10}, "$500");

        textStart.put(new int[]{1, 11}, "$750");
        textStart.put(new int[]{2, 11}, "$1,000");
        textStart.put(new int[]{3, 11}, "$5,000");
        textStart.put(new int[]{4, 11}, "$10,000");
        textStart.put(new int[]{5, 11}, "$25,000");
        textStart.put(new int[]{6, 11}, "$50,000");

        textStart.put(new int[]{1, 12}, "$75,000");
        textStart.put(new int[]{2, 12}, "$100,000");
        textStart.put(new int[]{3, 12}, "$200,000");
        textStart.put(new int[]{4, 12}, "$300,000");
        textStart.put(new int[]{5, 12}, "$400,000");
        textStart.put(new int[]{6, 12}, "$500,000");

        textStart.put(new int[]{7, 10}, "$750,000");
        textStart.put(new int[]{7, 11}, "$1,000,000");
    }

    public static String getValueAfterSpace(String text, int index) {

        int spacePos = text.indexOf(" ", index);
        if (spacePos == -1) {
            return ""; // No space found after the index
        }
        return text.substring(spacePos + 1);
    }
}


