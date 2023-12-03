import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String grade = br.readLine();

        if(grade.equals("F")) {
            System.out.println("0.0");
            return;
        }

        double first = 0.0;

        if(grade.charAt(0)=='A'){
            first = 4.0;
        }else if(grade.charAt(0)=='B'){
            first = 3.0;
        }else if(grade.charAt(0)=='C'){
            first = 2.0;
        }else if(grade.charAt(0)=='D'){
            first = 1.0;
        }

        double second = 0;
        if(grade.charAt(1)=='+'){
            second = 0.3;
        }else if(grade.charAt(1)=='-'){
            second = -0.3;
        }

        System.out.printf("%.1f", first+second);


    }// main

}// Main