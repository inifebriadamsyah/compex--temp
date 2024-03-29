package compex;

import java.io.FileWriter;
import java.util.Arrays;

public class Scheduler {

    private int size;
    private int[] timeslot;
    private boolean adaSolusi;
    private int[][] schedule;
    private String solusi = "";

    public Scheduler() {
        this.size = 0;
    }

    public Scheduler(int size) {
        this.size = size;
    }

    public int[][] getSchedule() {
        return this.schedule;
    }

    public String getSolusi() {
        return this.solusi;
    }

    public int[] getTimeslot() {
        return this.timeslot;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean getAdaSolusi() {
        return this.adaSolusi;
    }

    private void setAdaSolusi(boolean status) {
        this.adaSolusi = status;
    }

    private boolean checkTimeslot(int course, int[][] matrix, int[] timeslot, int t) {
        for (int i = 0; i < size; i++) {
            if (matrix[course][i] != 0 && t == timeslot[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkRandomTimeslot(int randomCourse, int randomTimeslot, int[][] matrix, int[][] jadwal) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[randomCourse][i] != 0 && jadwal[i][1] == randomTimeslot) {
                return false;
            }
        }
        return true;
    }

    private boolean isTersedia(int[][] matrix, int jumlah_timeslot, int[] timeslot, int course) {
        if (course == size) {
            return true;
        }

        for (int i = 1; i <= jumlah_timeslot; i++) {
            if (checkTimeslot(course, matrix, timeslot, i)) {
                timeslot[course] = i;

                if (isTersedia(matrix, jumlah_timeslot, timeslot, course + 1)) {
                    return true;
                }

                timeslot[course] = 0;
            }
        }

        return false;
    }

    public void timesloting(int[][] matrix, int jumlah_timeslot) {
        timeslot = new int[size];
        for (int i = 0; i < size; i++) {
            timeslot[i] = 0;
        }

        if (isTersedia(matrix, jumlah_timeslot, timeslot, 0)) {
            setAdaSolusi(true);
        } else {
            setAdaSolusi(false);
        }
    }

    public void printSchedule() {
        if (!adaSolusi) {
            System.out.println("Tidak ada solusi");
        } else {
            System.out.println("Jumlah Timeslot : " + Arrays.stream(timeslot).max().getAsInt());
            for (int i = 0; i < size; i++) {
                System.out.println(+(i + 1) + " " + timeslot[i]);

            }
            System.out.println();
        }
    }

    public void printSchedule(int[][] degree) {
        if (!adaSolusi) {
            System.out.println("Tidak ada solusi");
        } else {
            schedule = new int[size][2];
            for (int i = 0; i < size; i++) {
//                System.out.println(+ (degree[i][0]) +" " + (timeslot[i])); 
                solusi += degree[i][0] + " " + timeslot[i] + "\n";
                schedule[i][0] = degree[i][0];
                schedule[i][1] = timeslot[i];
            }
//            System.out.println();
//            System.out.println("Jumlah Timeslot minimum : " + Arrays.stream(timeslot).max().getAsInt());
        }
    }

    public void exportSchedule(String filename) {
        try {
            FileWriter write_solution = new FileWriter("E:/Frequently Used/Edukasi/Kuliah/Semester 7/Optimasi Kombinatorik dan Heuristik/Toronto/ExamTimetableEvaluation/" + filename + ".sol");
            write_solution.write(this.getSolusi());
            write_solution.close();
        } catch (Exception e) {
            System.out.println(e);
        }
//            System.out.println("File "+filename+" berhasil disimpan bos");    
    }

}
