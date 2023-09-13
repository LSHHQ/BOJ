import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        Position[] positions = new Position[N];
        for(int i=0; i<N; i++){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            long x = Long.parseLong(tokenizer.nextToken());
            long y = Long.parseLong(tokenizer.nextToken());
            positions[i] = new Position(x, y);
        }
        reader.close();

        long area = 0;
        for(int i=1; i+1<N; i++){
            area += outerProduct(positions[0], positions[i], positions[i+1]);
        }
        area = Math.abs(area);
        System.out.printf("%.1f\n", (double) area/2);
    }

    static long outerProduct(Position first, Position second, Position third){
        return (first.x * second.y + second.x * third.y + third.x * first.y) - (first.x * third.y + second.x * first.y + third.x * second.y);
    }

    static class Position {
        long x;
        long y;
        public Position(long x, long y){
            this.x = x;
            this.y = y;
        }
    }
}