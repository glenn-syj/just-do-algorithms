package boj_4386_별자리만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 
 * 각 주어지는 별에 대해서 임의로 1번부터 번호를 매겨서 유니온 파인드를 적용하였다.
 * 
 * 크루스칼은 그 개념이 다소 쉽기에 바로 생각해내서 이 문제에 적용하였지만,
 * 
 * 프림과, 다익스트라는 개념이 어려워 구현과정을 떠올리지 못했다.
 * 
 * 후에, 프림, 다익스트라를 사용해 해당 문제를 풀어보도록 하겠다.
 * 
 */

public class Main {
	
	static int[] p;
	
	public static class Line implements Comparable<Line>{
		
		int st;
		int ed;
		double wg;
		
		Line(int st, int ed, double wg){
			this.st = st;
			this.ed = ed;
			this.wg = wg;
		}
		
		@Override
		public int compareTo(Line o) {
			return Double.compare(this.wg, o.wg);
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int starCnt = Integer.parseInt(st.nextToken());
		
		double[][] galaxy = new double[starCnt + 1][2];
		
		for(int i = 1; i <= starCnt; i++) {
			st = new StringTokenizer(br.readLine());
			double startX = Double.parseDouble(st.nextToken());
			double startY = Double.parseDouble(st.nextToken());
			
			galaxy[i][0] = startX;
			galaxy[i][1] = startY;
			
		}
		
		PriorityQueue<Line> lines = new PriorityQueue<>();
		
		List<Double>[] distances = new ArrayList[starCnt + 1];
		
		for(int i = 1; i < starCnt; i++) {
			double startX = galaxy[i][0];
			double startY = galaxy[i][1];
			
			for(int j = i + 1; j <= starCnt; j++) {
				double endX = galaxy[j][0];
				double endY = galaxy[j][1];
				
				double dist = Math.sqrt(Math.pow(Math.abs(startX - endX), 2) + Math.pow(Math.abs(startY - endY), 2));
			
				lines.add(new Line(i, j, dist));
			}
		}
		
		double ans = 0;
		
		p = new int[starCnt + 1];
		
		for(int i = 1; i <= starCnt; i++) {	
			p[i] = i;
		}
		
		int cnt = 0;
		
		while(cnt != starCnt - 1) {
			Line line = lines.poll();
			
			if(find(line.st) == find(line.ed)) {
				continue;
			}
			
			ans += line.wg;
			
			union(line.st, line.ed);
			
			cnt++;
			
		}
		
		System.out.println(ans);
		
	}
	
	public static int find(int x) {
		if(p[x] == x) {
			return x;
		}else {
			return p[x] = find(p[x]);
		}
		
	}

	public static void union(int x, int y) {
		int xLeader = find(x);
		int yLeader = find(y);
		
		p[yLeader] = xLeader;
		
	}
	
	
}