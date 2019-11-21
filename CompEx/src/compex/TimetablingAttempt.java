package compex;

import compex.Optimizer;

/**
 *
 * @author Muris Timetabling
 */
public class TimetablingAttempt {

    static final String DIREKTORI = "E:/Frequently Used/Edukasi/Kuliah/Semester 7/Optimasi Kombinatorik dan Heuristik/Toronto/";

    public static void main(String[] args) {
        // Direktori file
        String stud_set = DIREKTORI + "yor-f-83.stu";
        String crs_set = DIREKTORI + "yor-f-83.crs";

        String test_stu = DIREKTORI + "test.stu";
        String test_crs = DIREKTORI + "test.crs";

        long startTime = System.nanoTime();
        Optimizer.hillClimbing(stud_set, crs_set, 100, 100000);
        long endTime = System.nanoTime();
        System.out.println("Runtime: " + (double) (endTime - startTime) / 1000000000);
        int[][] jadwal = Optimizer.getJadwal();
        for (int i = 0; i < jadwal.length; i++) {
            System.out.println(jadwal[i][0] + " " + jadwal[i][1]);
        }
    }
}
