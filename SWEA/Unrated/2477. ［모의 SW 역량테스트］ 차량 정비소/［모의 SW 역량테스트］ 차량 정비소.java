import java.io.*;
import java.util.*;

public class Solution {

    static int stoi(String s) {return Integer.parseInt(s);}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = stoi(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

        	st = new StringTokenizer(br.readLine());
        	int N = stoi(st.nextToken()); //접수 창구 수
        	int M = stoi(st.nextToken()); //정비 창구 수
        	int K = stoi(st.nextToken()); //고객의 수
        	int A = stoi(st.nextToken()); //타겟고객 접수창구 번호
        	int B = stoi(st.nextToken()); //타겟고객 정비창구 번호
        	
        	
        	// 고객 도착 큐 			-client
        	// 접수창구 대기 큐		-client
        	// 접수창구 리스트		-desk
        	// 정비창구 대기 큐		-client  
        	// 정비창구 리스트		-desk
        	
        	PriorityQueue<Client> arive = new PriorityQueue<>((a,b)->{return a.time-b.time;});
        	PriorityQueue<Client> recWait = new PriorityQueue<>((a,b)->{return a.id-b.id;});
        	PriorityQueue<Client> repWait = new PriorityQueue<>((a,b)->{return sort(a,b);});
        	List<Desk> reception = new ArrayList<>();
        	List<Desk> repair = new ArrayList<>();
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 1; i <= N; i++) reception.add(new Desk(i,stoi(st.nextToken())));
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 1; i <= M; i++) repair.add(new Desk(i,stoi(st.nextToken())));
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 1; i <= K; i++) arive.add(new Client(i,stoi(st.nextToken())));
        	
        	int hour = 0;
        	int ans = 0;
        	while(true) {
        		
        		//도착한 애들 모두 대기큐에 넣기
        		while(!arive.isEmpty() && arive.peek().time==hour) recWait.add(arive.poll());
        		
        		//접수창구 집어넣기, 집어넣으면서 끝난애 정비창구 대기큐에 넣기
        		for (int i = 0; i < N; i++) {
        			Desk desk = reception.get(i);
        			//창구 안이 비어있거나 안에 있는 애 시간이 나갔을 시간이 됐을 때
        			if(desk.client == null || desk.client.time<=hour) {
//        				if(desk.client != null) System.out.println(desk.client.id);
        				//안에있는애 내보내면서 정비창구 대기큐에 넣기
        				if(desk.client != null) {
        					repWait.add(desk.client);
        					desk.client = null;
        				}
        				
        				if(recWait.isEmpty()) continue;
       					Client client = recWait.poll();
       					client.time = hour+desk.cost;
       					client.rec = desk.id;
       					desk.client = client;
        			}
				}
        		
        		
        		for (int i = 0; i < M; i++) {
					Desk desk = repair.get(i);

					if(desk.client ==null || desk.client.time==hour) {
						desk.client = null;
						if(repWait.isEmpty()) continue;
						
						Client client = repWait.poll();
       					client.time = hour+desk.cost;
       					desk.client = client;
       					if(client.rec==A && desk.id==B) {
       						ans+=client.id;
       						client.id = 0;
       					}
					}
				}
        		
        		hour++;
        		if(hour==1000000) break;
        	}
        	
            bw.write("#" + tc + " " + (ans == 0 ? -1 : ans) + "\n");
        } //tc

        bw.flush();
        bw.close();
    }
    
    static int sort(Client A, Client B) {
    	if(A.time!=B.time) return A.time-B.time;
    	return A.rec-B.rec;
    }
    
    //창구 클래스
    static class Desk {
    	int id;
    	int cost;
    	Client client; //안에 있는 클라이언트가 누군지
    	Desk(int i,int c){id = i;cost = c;};
    }
    
    static class Client {
    	int id; //고객번호
    	int time; //처음엔 도착시간, 그다음엔 접수끝나는시간으로 쓰임
    	int rec; //사용한 접수창고 번호
    	Client (int i, int t){id=i;time=t;}
    }
}// Solution