import java.io.*;
import java.util.*;

public class Main {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //알파벳 소문자로만 이루어져 있다.
        //인접한 문자가 모두 달라야한다.
        //1. 등장한 알파벳과 그 갯수를 저장하고 그리디하게 풀어보는 방법
        //2. 등장한 알파벳과 그 갯수를 저장하고 수열로 완전탐색. 그리디를 사용해서 가지치기

        char[] input = br.readLine().toCharArray();
        inLen = input.length;

        //문자를 숫자로 바꿔서 문제를 푼다. 인덱스 : 해당 숫자, value : 그 숫자가 몇개있는지
        numbers = new int['z'-'a'+1];
        len = numbers.length;

        for (int i = 0; i < input.length; i++) {
            numbers[ input[i]-'a' ]++;
        }

        func(0,-1);

        System.out.println(ans);
    }// main

    static int len, ans, inLen;
    static int[] numbers;

    static void func(int depth, int pre){
        if(depth == inLen){
            ans++;
            return;
        }

        for (int i = 0; i < len; i++) {
            if(numbers[i]==0 || i==pre) continue;
            numbers[i]--;
            func(depth+1, i);
            numbers[i]++;
        }
    }

}// Main