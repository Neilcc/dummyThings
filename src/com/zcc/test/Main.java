package com.zcc.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static final String path = "./zcctest.txt";
    public static final int[] NUM_MODS = new int[]{
            100, 50, 30, 70, 20, 10, 80, 7, 33
    };

    public static final List<Integer> answers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // write your code here
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        Random random = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            count++;
            int a = genNum(random);
            String con = getConnector(random);
            int b = genNum(random);
            if (con.equals("+")) {
                answers.add(a + b);
            } else if (con.equals("-")) {
                answers.add(a - b);
            } else {
                answers.add(a * b);
            }
            sb.delete(0, sb.length());
            sb.append(a).append(" ").append(con).append(" ").append(b).append(" = ").append("\t\t");
            if (count == 4) {
                sb.append("\n");
                count = 0;
            }
            fileWriter.write(sb.toString());
            fileWriter.flush();

        }
        fileWriter.write("\n answers: \n");
        count = 0;
        for (Integer i : answers) {
            fileWriter.write(String.valueOf(i));
            fileWriter.write("\t");
            count++;
            if (count == 4) {
                fileWriter.write("\n");
                count = 0;
            }

        }
        fileWriter.flush();
        fileWriter.close();
    }


    private static int genNum(Random random) {
        int index1 = Math.abs(random.nextInt()) % NUM_MODS.length;
        int base1 = NUM_MODS[index1];
        int num1 = Math.abs(random.nextInt()) % base1;
        return num1;
    }

    public static String getConnector(Random random) {
        int val = random.nextInt();
        if (val % 3 == 0) {
            return "+";
        } else if (val % 3 == 1) {
            return "-";
        } else {
            return "*";
        }
    }
}
