package boj_2252_줄세우기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * 유니온 파인드를 이용한 줄세우기 문제.
 * 
 * 인줄 알았으나, 위상정렬을 이용한 문제였다.
 * 
 * 단, 내가 배웠던 위상정렬이란, 자신을 가리키는 인원을 카운트하여 진행했지만,
 * 
 * 이곳에서는 싸워서 이긴 사람을 앞에 두고, 진 사람을 뒤에 두는,
 * 
 * 조건으로 자료를 구성하였기에, 각 개인으로 하여금 싸워서 이긴 사람 수를 카운트하여 진행하였다.
 * 
 * 즉, 가리키는 사람이 없는 사람은 앞에 서야하는 싸움꾼으로 보고,
 * 
 * 줄을 세워주었다.
 * 
 * 
 */

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		// 입력을 위한 buffered reader이다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 출력을 위한 buffered writer이다.
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// 학생의 수를 담은 변수이다.
		int studentCnt = Integer.parseInt(st.nextToken());
		
		// 총 싸운 횟수를 나타내는 변수이다.
		int fightCnt = Integer.parseInt(st.nextToken());
		
		// 각 학생별로, 승리목록, 즉 싸워서 패배시킨 인물을 담아주었다.
		List<List<Integer>> rank = new ArrayList<>();
		
		// 각 학생별로 싸워서 패배한 숫자를 카운트해주었다.
		int[] lossRank = new int[studentCnt + 1];
		
		// 번호와 인덱스를 매치시키기 위해, 학생 수에서 1을 추가한 횟수만큼 목록을 만들어주었다.
		for(int i = 0; i <= studentCnt; i++) {
			List<Integer> tmp = new ArrayList<>();
			
			rank.add(new ArrayList<>());
		}
		
		// 싸운 횟수만큼 반복하며, 이긴 사람의 목록에 진 사람 번호를 넣어주고,
		// 진사람에게 진 횟수를 추가해주었다.
		for(int i = 0; i < fightCnt; i++) {
			st = new StringTokenizer(br.readLine());

			int winner = Integer.parseInt(st.nextToken());
			
			int loser = Integer.parseInt(st.nextToken());
			
			rank.get(winner).add(loser);
			
			lossRank[loser]++;
			
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i < lossRank.length; i++) {
			// rank.get(i).size() == 0
			// 아래 if 조건에 이 코드를 넣어서 틀렸었다.
			// 이게 틀린 이유, rank 이중 리스트는
			// winner와 싸워서 패배한 loser들만 들어있기 때문에,
			// 이게 0개인 사람은 제일 많이 진 사람을 가리키게 된다.
			// 그러면 가장 약한 애를 출력하고 그 약한 사람에게 진 사람을 뽑아오는데,
			// 가장 약한 애에게 패배하는 사람은 없으므로 약한애만 출력하고 로직이 끝나버린다.
			if(lossRank[i] == 0) {
				queue.add(i);
			}
		}
		
		// 큐가 빌 때 까지, 반복한다.
		while(!queue.isEmpty()) {
			
			// 큐의 맨 앞에 있는 강자를 뽑아온다.
			int fighter = queue.poll();

			// 출력문에 저장한다.
			bw.write(fighter + " ");

			// 그에게 진 사람들의 목록을 가져온다.
			List<Integer> winList = rank.get(fighter);
			
			for(int i = 0; i < winList.size(); i++) {
				// 패배자들의 패배횟수들을 하나씩 감소시킨다.
				
				int loser = winList.get(i);
				
				lossRank[loser]--;
				
				// 패배자의 패배횟수들이 0이 된다는 것은,
				// 패배자보다 이긴 횟수가 많은 강자들이 전부 앞에 갔음을 의미하기에,
				// 해당 패배자 또한 queue에 넣어준다.
				if(lossRank[loser] == 0) {
					queue.offer(loser);
				}
			}
			
		}
		
		
		bw.flush();
		
	}
}