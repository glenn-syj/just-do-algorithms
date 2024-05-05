import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/* B19237. 어른 상어
 * 
 * 1. 조건
 * 	1-1. 상어에는 1이상 M이하의 자연수 번호가 붙어있고, 모든 번호는 서로 다르다
 * 	1-2. 상어들은 영역을 사수하기 위해 다른 상어들을 쫓아내려고 함
 * 		-> 겹치면 해당 영역에서 번호가 가장 작은 상어가 다 쫓아냄
 *	1-3. 이동방향
 *		(1) 보는 방향에 따라 우선순위가 주어짐
 *		(2) 우선순위 칸부터 순서대로 비어있는 칸을 선택
 *		(3) 아무 냄새가 없는 칸 > 자신의 냄새가 있는 칸 
 *	1-4. 냄새
 *		(1) 냄새는 k초 후 사라짐
 *		(2) 상어가 지나가면 냄새를 뿌림
 *	1-5. "모든 상어가 동시에" 상하좌우로 인접한 칸 중 하나로 이동
 *		-> 상어를 모두 이동시키기
 *		-> 이동 전 자리가 상어가 아니라면 냄새를 k의 시간으로 두기
 *		-> 시간에 대해 모두 -1로 하고, 적용뒤 시간값이 0이면 없애주기
 *	1-6. 2 <= N <= 20, 2 <= M <= N^2, 1 <= k <= 1000
 *	1-7. 1000초가 넘어도 다른 상어가 격자에 남아있으면 -1을 출력
 * 2. 풀이
 *	2-1. 상어와 냄새에 대한 객체 배열을 생성해서 이동시키며 진행
 *		-> 상어와 냄새에 대한 클래스 생성 필요
 * 	2-2. 상어 클래스
 * 		-> 상어 번호, 현재 위치, 이전 위치를 모두 저장하고 있어야 함
 * 		-> 각 위치에 대한 우선순위를 담고 있어야 함
 * 	2-3. 냄새 클래스
 * 		-> 좌표와 남은 시간을 모두 저장하고 있어야 함
 */

public class Main {
	
	static int N, M, K, time;
	static int[][] board;
	static Object[][] objectBoard;
	static Shark[] sharks;
	static int sharkCount;
	// 위치 순서: 위 아래 왼쪽 오른쪽
	static int[][] deltas = new int[][] { {0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
	
	// 기본적인 자료구조 클래스 생성
	// 나중에는 builder 형태로 이용해보자
	
	static class Shark {
		
		int id, dir, currRow, currCol, lastRow, lastCol;
		int[][] priorities;
		
		public Shark() {
			
		}
		
		public Shark(int id, int currRow, int currCol, int lastRow, int lastCol) {
			
			this.id = id;
			this.currRow = currRow;
			this.currCol = currCol;
			this.lastRow = lastRow;
			this.lastCol = lastCol;
			this.priorities = new int[5][5];
			
		}
		
	}
	
	static class Frag {
		
		int id, currRow, currCol, lastTime;
		
		public Frag() {
			
		}
		
		public Frag(int id, int currRow, int currCol, int lastTime) {
			
			this.id = id;
			this.currRow = currRow;
			this.currCol = currCol;
			this.lastTime = lastTime;
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		time = 0;
		objectBoard = new Object[N][N];
		sharks = new Shark[M+1];
		int parsedToken;
		
		// objectBoard 초기화와 shark를 이용하기 위한 sharks 배열 설정
		for (int row=0; row<N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col=0; col<N; col++) {
				parsedToken = Integer.parseInt(st.nextToken());
				if (parsedToken > 0) {
					objectBoard[row][col] = new Shark(parsedToken, row, col, -1, -1);
					sharks[parsedToken] = (Shark) objectBoard[row][col];
				}
			}
		}
		
		
		// shark의 방향과 각 방향에 따른 우선순위 설정
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=M; i++) {
			sharks[i].dir = Integer.parseInt(st.nextToken());
		}
		
		for (int i=1; i<=M; i++) {
			for (int dir=1; dir<5; dir++) {
				st = new StringTokenizer(br.readLine());
				for (int p=1; p<5; p++) {
					sharks[i].priorities[dir][p] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		
		int ans = 0;
		sharkCount = M;
		while (time <= 1000) {
			
			for (int i=1; i<=M; i++) {
				if (sharks[i] == null) {
					continue;
				}
//				System.out.println(i+"번째 상어 위치: " + sharks[i].currRow + " " + sharks[i].currCol + " 방향: " + sharks[i].dir);
			}
			
			if (sharkCount == 1) {
				break;
			}
			
			move();
			
			
			
			time++;
			
			
		}
		
		if (time > 1000) {
			ans = -1;
		} else {
			ans = time;
		}
		
		sb.append(ans);
		bw.write(sb.toString());
		bw.close();
		
	}
	
	public static void move() {
		
		int nextRow, nextCol, tmpRow, tmpCol, tmpDir;
		Frag frag;
		Shark tmpShark;
		
		// 1단계: shark를 이동시키고 
		sharkLoop:
		for (Shark shark: sharks) {
			
			if (shark == null) {
				continue;
			}
			
			shark.lastRow = shark.currRow;
			shark.lastCol = shark.currCol;
			
			for (int i=1; i<5; i++) {
				
				nextRow = shark.currRow + deltas[shark.priorities[shark.dir][i]][0];
				nextCol = shark.currCol + deltas[shark.priorities[shark.dir][i]][1];
				
				if (rangeCheck(nextRow, nextCol) 
						&& objectBoard[nextRow][nextCol] == null) {
					shark.currRow = nextRow;
					shark.currCol = nextCol;
					shark.dir = shark.priorities[shark.dir][i];
					continue sharkLoop;
				}
				
			}
			
			for (int i=1; i<5; i++) {
				
				nextRow = shark.currRow + deltas[shark.priorities[shark.dir][i]][0];
				nextCol = shark.currCol + deltas[shark.priorities[shark.dir][i]][1];

				if (rangeCheck(nextRow, nextCol) 
						&& objectBoard[nextRow][nextCol] instanceof Frag
						&& ((Frag) objectBoard[nextRow][nextCol]).id == shark.id) {
					shark.currRow = nextRow;
					shark.currCol = nextCol;
					shark.dir = shark.priorities[shark.dir][i];
					continue sharkLoop;
				}
				
			}
		}
		
		// 2단계: shark의 이전 위치에 대해 Fragrance를 생성해주기
		
		for (Shark shark: sharks) {
			
			if (shark == null) {
				continue;
			}
			objectBoard[shark.lastRow][shark.lastCol] = new Frag(shark.id, shark.lastRow, shark.lastCol, K);
			
		}
		
		// 3단계: objectBoard를 순회하며 frag마다 시간을 1씩 감소하고, 0이되면 null로 제거해주기
		
		for (int row=0; row<N; row++) {
			for (int col=0; col<N; col++) {
				if (objectBoard[row][col] != null 
						&& objectBoard[row][col] instanceof Frag) {
					frag = (Frag) objectBoard[row][col];
					frag.lastTime--;
					
					if (frag.lastTime == 0) {
						objectBoard[row][col] = null;
					}
				}
			}
		}
		
		// 4단계: shark를 순회하며 shark의 현재 위치에 맞춰 objectBoard를 업데이트
		// -> 위에서 원래 자리는 frag가 되었으므로, 바로 shark의 위치를 넣어주면 됨
		// -> 만약 그 자리에 이미 shark가 있다면, sharks의 해당 index는 null로 초기화해주고 count--
		
		for (int i=1; i<=M; i++) {
			
			if (sharks[i] == null) {
				continue;
			}
			
			tmpShark = sharks[i];
			
			if (objectBoard[tmpShark.currRow][tmpShark.currCol] != null 
					&& objectBoard[tmpShark.currRow][tmpShark.currCol] instanceof Shark) {
				sharks[i] = null;
				sharkCount--;
				continue;
			}
			
			objectBoard[tmpShark.currRow][tmpShark.currCol] = sharks[i];
			
		}
	}
	
	public static boolean rangeCheck(int row, int col) {
		return (row >= 0 && row < N && col >= 0 && col < N);
	}
}