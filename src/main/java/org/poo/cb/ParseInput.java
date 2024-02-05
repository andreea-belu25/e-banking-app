package org.poo.cb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseInput {
    public ParseInput() {
    }
    public void addElement(ArrayList<ArrayList<String>> raggedMatrix, int numberOfRow, String string) {
        while (raggedMatrix.size() <= numberOfRow)
            raggedMatrix.add(new ArrayList<>());

        ArrayList<String> row = raggedMatrix.get(numberOfRow);
        row.add(string);
    }
    public ArrayList<ArrayList<String>> read (String path) {
        String filePath = "../../../../resources/test9_bonus/commands.txt";
        File fileToRead = new File(filePath);

        ArrayList<ArrayList<String>> raggedMatrix = new ArrayList<>();
        try {
            Scanner input = new Scanner(fileToRead);
            Integer indexInMatrix = 0;
            while (input.hasNextLine()) {
                String data = input.nextLine();
                if (data.isEmpty())
                    break;
                addElement (raggedMatrix, indexInMatrix, data);
                indexInMatrix++;
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        return raggedMatrix;
    }
}
