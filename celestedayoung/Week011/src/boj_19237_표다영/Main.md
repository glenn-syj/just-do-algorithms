# BOJ 19237. 어른 상어

## ✓ Difficult Point
- 상어가 이동한 후에는 상어의 방향을 이동한 방향으로 바꿔주어야 한다.
- 위 순서에서 2번과 3번을 바꾸게 되면 상어가 새로운 냄새를 생성하자마자 1 씩 깎이기 때문에 순서를 지켜야 한다.
- 상어 리스트를 for 문으로 돌면서 경쟁에서 패배한 상어를 바로 제거하니 런타임 에러가 발생한다. → for 문 도중에는 요소를 삭제하지 말고 기록해두었다가 나중에 삭제한다.

## ✓ Class & Method & Used Data Structures

### Class
`Shark`
- 우선 순위는 priority[현재 방향][우선 순위] 형태다.
- 현재 상어의 방향을 기준으로 우선순위가 높은 방향부터 candiates 셋에 존재하는지 확인한다..
#### - Member field
- `id`: 번호
- `x`, `y`: 좌표
- `direction`: 방향
- `priority`: [현재 방향][우선 순위]를 담는 배열
#### - Member method
- `findNextDir`: 다음 이동 방향을 찾는 메소드
- `candidates` 셋은 상어가 이동할 수 있는 방향들

### Method
- `moveShark()`: 상어 이동, 다음 방향 계산, 이동, 경쟁을 통해 작은 번호의 상어 생존  
- `decreaseSmellTime()`: 모든 냄새들 1 씩 감소  
- `createSmell()`: 상어가 이동한 자리에 새로운 냄새 생성

### Used Data Structures
#### - 2D array
- `arr`: 상어의 위치
- `smellOwner`: 냄새의 주인
- `leftTime`: 냄새들의 남은 시간

#### - HashSet
- `noSmellSet`, `mySmellSet`
  - 상어가 이동할 수 있는 공간을 미리 구하기 위해 사용한다.
  - 상어는 먼저 냄새가 없는 곳을 찾고 만약 4방향 전부 냄새가 존재한다면 자기 냄새가 있는 방향으로 이동한다.
  - 이동할 수 있는 곳은 여러 개가 될 수 있기 때문에 Set 자료구조에
  - 저장하고 상어의 현재 방향에 따른 우선순위를 통해 비교한다.

#### - Queue
- `willRemove`
  - 경쟁에서 뒤쳐진 상어는 제거해야 한다.
  - 한번에 여러 마리가 겹칠 수 있기 때문에 Queue에 담아준다.


## ✓ Logic
1. 매 초마다 움직이며 냄새를 뿌리는 상어들이 여러 마리 존재한다.
2. 각 상어들은 자신들이 바라보는 방향과 기준에 따라서 다음으로 움직일 방향을 선정한다.
3. 상어들이 같은 격자에 동시에 들어가게 되면 숫자가 작은 상어만 남고 나머지는 전부 내보낸다.
4. 가장 강한 1 번 상어가 남는 시간은 몇 초 뒤인지 구한다.
   
## ✓ Code
```java
package APS.BOJ.어른상어;

import java.io.*;
import java.util.*;

class Shark {
    int id, x, y, direction;
    int[][] priority = new int[5][5];

    Shark() { }

    int findNextDir(Set<Integer> candidates) {
        for (int i = 1; i < 5; i++) {
            if (candidates.contains(priority[direction][i])) {
                return priority[direction][i];
            }
        }
        return 0;
    }
}

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, k;
    static int[][] arr = new int[21][21];
    static int[][] smellOwner = new int[21][21];
    static int[][] leftTime = new int[21][21];
    static Map<Integer, Shark> sharks = new HashMap<>();
    static int time = 0;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] > 0) {
                    Shark s = new Shark();
                    s.id = arr[i][j];
                    s.x = i;
                    s.y = j;
                    sharks.put(s.id, s);

                    smellOwner[i][j] = s.id;
                    leftTime[i][j] = k;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            Shark s = sharks.get(i + 1);
            s.direction = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            Shark s = sharks.get(i + 1);

            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());

                for (int z = 0; z < 4; z++) {
                    s.priority[j + 1][z + 1] = Integer.parseInt(st.nextToken());
                }
            }
        }
        solution();
    }

    static void solution() {
        while (time++ < 1000) {
            moveShark();
            decreaseSmellTime();
            createSmell();

            if (sharks.size() == 1) {
                System.out.println(time);
                return;
            }
        }
        System.out.println(-1);
    }

    static void moveShark() {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Deque<Integer> willRemove = new ArrayDeque<>();

        for (Integer id : sharks.keySet()) {
            Set<Integer> noSmellSet = new HashSet<>();
            Set<Integer> mySmellSet = new HashSet<>();
            Shark s = sharks.get(id);

            for (int i = 0; i < 4; i++) {
                int nx = s.x + dx[i];
                int ny = s.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                if (smellOwner[nx][ny] == 0) {
                    noSmellSet.add(i + 1);
                } else if (smellOwner[nx][ny] == s.id) {
                    mySmellSet.add(i + 1);
                }
            }

            int nextDirection = s.findNextDir(noSmellSet);

            if (nextDirection == 0) {
                nextDirection = s.findNextDir(mySmellSet);
            }

            arr[s.x][s.y] = 0;
            if (nextDirection == 1) {
                s.x -= 1;
            } else if (nextDirection == 2) {
                s.x += 1;
            } else if (nextDirection == 3) {
                s.y -= 1;
            } else if (nextDirection == 4) {
                s.y += 1;
            }

            if (arr[s.x][s.y] == 0 || s.id < arr[s.x][s.y]) {
                arr[s.x][s.y] = s.id;
                s.direction = nextDirection;
            } else {
                willRemove.addLast(s.id);
            }
        }

        while (!willRemove.isEmpty()) {
            sharks.remove(willRemove.pollFirst());
        }
    }

    static void decreaseSmellTime() {
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (leftTime[i][j] == 0) continue;

                leftTime[i][j]--;

                if (leftTime[i][j] == 0) {
                    smellOwner[i][j] = 0;
                }
            }
        }
    }

    static void createSmell() {
        for (Integer id : sharks.keySet()) {
            Shark s = sharks.get(id);

            smellOwner[s.x][s.y] = s.id;
            leftTime[s.x][s.y] = k;
        }
    }
}

```
문제 출처: https://www.acmicpc.net/problem/19237

## ✓ What I realized
- 클래스를 만들어 각 객체의 멤버 필드를 변경 시키며 상태를 변화 시키는 건 많이 경험해봤지만, 이번 경우처럼 클래스 내 멤버 메소드까지 정의해 풀어보는 문제는 처음이었고 앞으로도 응용할 수 있는 문제에 적절하게 적용해야겠다고 생각했다. 
- 위 문제와 같이 여러 가지 자료구조를 사용 + 주어진 규칙에 따라 구현하는 문제에 많이 약한 것 같다. 시뮬레이션 문제를 잘 푸는 것이 앞으로 더 복잡한 문제를 푸는 것에 있어 기초가 된다 생각하기 때문에 쉬운 난이도라 표시되어 있더라도 시뮬레이션 문제에 집중해 실력을 다져야겠다.