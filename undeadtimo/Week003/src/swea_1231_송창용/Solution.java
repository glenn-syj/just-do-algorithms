package swea_1231_송창용;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 첫 번째 클래스, Node
// 클래스로서 Node를 정의하여, 가지고있는 data, 왼쪽에 해당하는 또 다른 Node, 오른쪽에 해당하는 Node
// 위의 데이터를 갖도록하면 자연스럽게 데이터를 저장하면서 왼쪽 오른쪽에 해당하는 Node에 접근할 수 있게 된다.
class Node {
	// 주어지는 문자를 해당 Node 객체가 갖도록 변수를 만들어주었다.
	String data;
	
	// 왼쪽에 해당하는 Node 객체의 주소를 가리킬 left 변수
	Node left;
	
	// 오른쪽에 해당하는 Node 객체의 주소를 가리킬 right 변수
	Node right;

	// 보다시피 기본생성자를 따로 정의하지 않아도 예외가 발생하지 않는다.
	
	public Node(String data, Node left, Node right) {
		// Node를 생성할 때, 매개변수로 data와 왼쪽에 해당하는 node, 오른쪽에 해당하는 node를
		// 받아와서 저장할 수 있도록 하였다.
		this.data = data;
		this.left = left;
		this.right = right;

	}
}


// 두 번째 클래스
public class Solution {


	public static void main(String[] args) throws IOException {
		// 빠른 입력을 위하여 BufferedReader와 InputStream 클래스를 import 후 사용했다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 빠른 입력을 위한 StringTokenizer 선언
		StringTokenizer st;

		// 테스트 케이스가 10번이기 때문에 10번 반복하는 for 문을 구성하였다.
		for (int test_case = 1; test_case <= 10; test_case++) {
			// 주어지는 노드의 수를 할당받는 변수 N
			// 해당 줄에는 N의 수치만 존재하기 때문에,
			// nextToken() 사용없이 바로 Integer.parseInt로 정수형으로 변환해주었다.
			int N = Integer.parseInt(br.readLine());

			// Node 타입의 객체들을 저장할 nodeArr을 생성했다.
			// 번호에 따라 편하게 node들을 가져올 수 있도록 N + 1개 크기로 설정하였다.
			Node[] nodeArr = new Node[N + 1];

			// 주어지는 입력값들을 저장할 String 타입의 배열
			// nodeArr과 마찬가지로 node들을 번호에 맞는 index로 바로 가져올 수 있도록
			// N + 1개 크기로 설정하였다.
			String[] input = new String[N + 1];

			// 먼저 주어지는 입력값들을 가져와 input 배열에 그대로 넣어주었다.
			for (int i = 1; i <= N; i++) {
				input[i] = br.readLine();
			}

			// 맨 아래의 노드부터 출력하는 것이므로,
			// i가 N일 때부터 1일 때까지 역으로 감소하면서 탐색하도록 하였다.
			// 이렇게 되면 자식이 없는 마지막 노드에서 출발하여,
			// root node를 마지막으로 탐색하게 된다.
			for (int i = N; i >= 1; i--) {
				// 입력값에 저장한 String 값을 StringTokenizer로 받아왔다.
				st = new StringTokenizer(input[i]);
				
				// 첫 번째 수치인 해당 Node의 index값은 필요하지 않으므로,
				// 변수에 할당하지 않고 버려지도록 했다.
				st.nextToken();
				
				// 두 번째 수치인 해당 Node의 data를 s 변수에 할당하였다.
				String s = st.nextToken();

				// 만약 남아있는 토큰, 즉 문자가 두 개라면,
				// 하나는 left라는 이름의 변수에 할당할 것이고,
				// 다른 하나는 right라는 이름의 변수에 할당할 것이다.
				// 이것은 각자 왼쪽, 오른쪽에 있는 node의 위치를 나타낸다.
				// 이 정보들을 통해서 마지막 번호부터 시작해 Node 타입의 객체를 형성할 수 있다.
				// 예를 들어 첫 번째 테스트 케이스를 보면,
				// 8번이 마지막 노드인데, 첫 번째 반복에서는 이 8번의 데이터인 'S'가 data에 저장되고,
				// 왼쪽과 오른쪽에는 아무것도 없으니, else 문이 실행되어
				// left 변수와 rigth 변수에 각각 null이 할당될 것이다.
				if (st.countTokens() == 2) {
					int left = Integer.parseInt(st.nextToken());
					int right = Integer.parseInt(st.nextToken());
					nodeArr[i] = new Node(s, nodeArr[left], nodeArr[right]);
				} else if (st.countTokens() == 1) {
					int left = Integer.parseInt(st.nextToken());
					nodeArr[i] = new Node(s, nodeArr[left], null);
				} else {
					nodeArr[i] = new Node(s, null, null);
				}

			}
			// 형식을 맞추기 위해 테스트 케이스를 출력하였다.
			System.out.print("#" + test_case + " ");
			
			// root node인 첫 번째 노드를 inOrder 메소드의 매개변수로 사용하였다.
			inOrder(nodeArr[1]);
		
			// 하나의 테스트 케이스가 끝났으므로 개행문자를 출력하였다.
			System.out.println();
		}

	}
	
	// 핵심 로직인 inOrder 메소드
	static void inOrder(Node node) {
		// 만약 node가 존재하지 않는다면 메소드를 끝내고 돌아간다.
		// 예를 들어 마지막 8번 노드의 경우,
		// inOrder(node.left), inOrder(node.right)가 시행되면
		// 이는 사실 둘 다 inOrder(null)과 같다.
		// 8번 노드에는 자식이 없기 떄문이다.
		// 즉, 존재하지 않는 node에서는 재귀를 멈추고 바로 돌아오도록 하는 조건문이다.
		if(node == null) {
			return;
		}
		// 머릿속으로 그려보아라.
		// inOrder 메소드가 힙영역에 끊임없이 쌓여나가며 한없이 이어져나갈 것이다.
		// 마지막 node에 도착하면 위의 조건문으로 인해 return이 실행된다.
		// 모든 inOrder(node.left);가 끝나고 return으로 돌아가면서,
		// System.out.print 메소드로 각 노드들이 가지고 있는 data들이 역순으로 출력될 것이다.
		// 그러나 바로 전부 돌아가는 것이 아니라 해당 노드의 데이터를 출력하고 오른쪽을 탐색하게 된다.
		inOrder(node.left);
		System.out.print(node.data);
		inOrder(node.right);
		
		// 8번 노드를 상상해보자.
		// 8번 노드는 자식이 없으니 return이 실행될 것이고 inOrder(node.left);가 끝나서
		// 8번 노드의 데이터인 'S'가 출력될 것이다.
		// 이후, inOrder(node.right); 가 실행되고 오른쪽에도 자식이 없으니 return이 실행된다.
		// 8번이 끝난다면 어떻게 될 것인가?
		// 8번이 실행된 배경을 살펴보면,
		// 8번은 4번의 inOrder(node.left) 메소드로 인해 실행된 노드이다.
		// 따라서 4번의 inOrder(node.left);가 끝났으니,
		// 4번의 System.out.print(node.data)가 실행되어 4번의 문자가 출력된다.
		
		
		// 오른쪽을 탐색하고 돌아오면서 데이터가 또 출력되는 것은 아닌가? 하고 의문을 가졌다.
		// 그러나, 오른쪽을 탐색하는 inOrder 메소드는
		// 현재 노드의 데이터를 출력한 이후에 존재하므로,
		// 오른쪽을 탐색하고 돌아온다고 해도 별다른 수행 없이 바로 위로 올라가게 된다.
	}
	
}
