package compex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Random;

public class ConflictMatrix {

    int[][] conflict_matrix;
    int student_length = 0;

    public ConflictMatrix(String dir, int size) {
        conflict_matrix = new int[size][size];
        try {
            FileReader filereader = new FileReader(dir);
            BufferedReader bufferedreader = new BufferedReader(filereader);

            creatingMatrix(bufferedreader);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getJumlahStudent() {
        return this.student_length;
    }

    public int[][] getMatrix() {
        return conflict_matrix;
    }

    public void creatingMatrix(BufferedReader br) {
        String courseLine = null;
        try {
            while ((courseLine = br.readLine()) != null) {
                student_length++;
                String[] arr = courseLine.split(" ");
                if (arr.length > 1) {
                    for (int i = 0; i < arr.length - 1; i++) {
                        for (int j = i + 1; j < arr.length; j++) {
                            int index1 = Integer.parseInt(arr[i]);
                            int index2 = Integer.parseInt(arr[j]);

                            this.conflict_matrix[index1 - 1][index2 - 1]++;
                            this.conflict_matrix[index2 - 1][index1 - 1]++;
                        }
                    }
                }
            }

        } catch (Exception e) {   }
    }

    public static void sortDegree(int arr[][], int col) {
        Comparator<int[]> byDegree = Comparator.comparing(row -> row[1]);
        Comparator<int[]> byCourse = Comparator.comparing(row -> row[0]);

        Arrays.sort(arr, Collections.reverseOrder(byDegree.thenComparing(byCourse.reversed())));
    }

    public int[][] getDegree() {
        int[][] temp = Arrays.copyOf(getMatrix(), getMatrix().length);
        int[][] courseDegree = new int[temp.length][2];

        for (int i = 0; i < temp.length; i++) {
            int count = 0;
            for (int j = 0; j < temp.length; j++) {
                if (temp[i][j] != 0) {
                    count++;
                }
            }
            courseDegree[i][0] = i + 1;
            courseDegree[i][1] = count;
        }
        sortDegree(courseDegree, 1);

        return courseDegree;
    }

    public int[][] getLargestDegree() {
        int[][] temp = Arrays.copyOf(getMatrix(), getMatrix().length);
        int[][] courseDegree = this.getDegree();
        int[][] largestDegree = new int[temp.length][temp.length];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                largestDegree[i][j] = temp[courseDegree[i][0] - 1][courseDegree[j][0] - 1];
            }
        }
        return largestDegree;
    }

    public int[][] getLargestDegree(int[][] arr) {
        int[][] temp = arr;
        int[][] courseDegree = this.getDegree();
        int[][] largestDegree = new int[temp.length][temp.length];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                largestDegree[i][j] = temp[courseDegree[i][0] - 1][courseDegree[j][0] - 1];
            }
        }
        return largestDegree;
    }

    public void printMatrix() {
        for (int i = 0; i < conflict_matrix.length; i++) {
            for (int j = 0; j < conflict_matrix.length; j++) {
                System.out.print(conflict_matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public int[][] getRandomIndex(int size) {
        ArrayList<Integer> course = new ArrayList<Integer>();
        int[][] randomIndex = new int[size][1];

        for (int i = 1; i <= size; i++) {
            course.add(i);
        }

        for (int i = 0; i < randomIndex.length; i++) {
            int randomNumber = getRandomNumber(0, course.size());
            randomIndex[i][0] = course.get(randomNumber);
            course.remove(randomNumber);
        }
        return randomIndex;
    }

    public int[][] getRandomMatrix() {
        int[][] temp = getMatrix();
        int[][] randomIndex = this.getRandomIndex(temp.length);
        int[][] randomMatrix = new int[temp.length][temp.length];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                randomMatrix[i][j] = temp[randomIndex[i][0] - 1][randomIndex[j][0] - 1];
            }
        }
        return randomMatrix;
    }
}