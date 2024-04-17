# SWEA 2382. 미생물 격리

## ✓ Difficult Point
### 처음에 시도한 접근 방법
#### 첫 번째 방법
- while문을 이용해 시간을 1시간씩 증가시킬 때 for문을 통해 한 칸씩 접근해 미생물 군집을 정해진 방향으로 한 칸으로 옮긴다. 이때 주어진 조건들 1. 약품이 칠해진 칸에 들어갈 경우, 2. 군집끼리 겹칠 때를 처리한다.
- 위와 같은 접근 방법의 문제점은 군집끼리 겹치는 경우를 제대로 처리하지 못한다는 점이다. 아직 옮기지 않은 군집이 존재하는 칸으로 특정 군집을 옮기면 겹치는 것으로 처리하게 되는데, 아직 옮기지 않았기 때문에 그렇게 판단하면 안된다.

#### 두 번째 방법
- while문을 이용해 시간을 1시간씩 증가시킬 때 for문을 통해 한 칸씩 미생물 군집을 정해진 방향으로 다 옮긴 후, 다시 배열을 순회하며 같은 칸에 존재하는 군집들을 골라내서 해당 조건에 맞는 로직을 처리한다.
- 굳이 2차원 배열을 쓰지 않아도 된다고 생각하여 1차원 배열에 담긴 군집들의 좌표를 검사하면서 서로 같으면 따로 다른 배열에 담았다. 
- 위와 같은 접근 방법의 문제점은 같은 칸에 여러 군집이 존재하는 경우가 2개 이상일 경우 따로 다른 배열에 한꺼번에 담겨 좌표별로 처리하기가 어려워진다는 점이다. 

### 수정한 접근 방법
- 2차원 배열을 사용하되 각 배열이 `ArrayDeque` 구성되도록 한다. 즉, `Deque<Microbe>[][]` plate와 같은 자료형으로 만든다.
- 각 좌표에 해당하는 군집을 `ArrayDeque`에 넣고, for문을 통해 좌표 하나씩 접근할 때 `ArrayDeque`에서 군집을 하나씩 꺼내서 주어진 조건에 맞춰 처리한다.
- 만약 `ArrayDeque`의 크기가 1이상이라면, 즉 두 개 이상의 군집이 동시에 존재한다면 해당 군집들의 미생물 수를 비교하여 가장 많은 미생물을 가진 군집을 기준으로 미생물들을 합치고 나머지 군집들의 미생물 수를 0으로 만든다.

## ✓ Class & Method
### Class
```java
class Microbe {

    int num, direction;

    Microbe() {}

    Microbe(int num, int direction) {
        this.num = num;
        this.direction = direction;
    }

}
```
- 미생물 군집에 대한 속성들을 담고 있는 클래스
  - `num`:미생물 수
  - `direction`: 방향

### Method
- `public static void experiment()`: 주어진 시간동안 미생물을 키우는 메소드   
- `public static Microbe findMaxMicrobe(Deque<Microbe> plate)`: 한 칸에 2개 이상의 군집이 존재할 때 미생물 수가 가장 많은 군집을 찾는 메소드
- `public boolean chemicalRange(int r, int c)`: 약품이 칠해진 범위에 들어가는지 검사하는 메소드

## ✓ Logic
### Step 1
1. 미생물 군집을 옮길 새로운 배열 `newPlate`을 만들고 배열의 각 칸을 `ArrayDeque`로 초기화 시킨다.
2. `plate`의 각 칸에 접근하여 해당 칸의 `ArrayDeque`에 들어있는 미생물 군집을 하나씩 꺼낸다.
3. 미생물 수가 0인 경우는 없는 것으로 취급하므로 0이 아닌 경우에 대해서만 각 군집이 가진 방향으로 1칸 이동한다.
4. 만약 이동한 위치가 약품이 칠해진 곳이라면 방향을 반대로 바꾸고, 미생물 수를 절반으로 줄인다.
5. 1칸 이동한 좌표에 맞추어 `newPlate`의 해당 좌표의 `ArrayDeque`에 넣는다.

### Step 2
1. `newPlate`의 각 칸에 접근하여 `ArrayDeque`의 길이가 2이상, 즉 2개 이상의 미생물 군집이 존재하는 경우에 대하여 `findMaxMicrobe`메소드를 이용해 미생물 군집을 합친다.
2. `plate`를 `newPlate`로 갱신한다.


## ✓ Code
```java
import java.util.*;

class Microbe {

    int num, direction;

    Microbe() {}

    Microbe(int num, int direction) {
        this.num = num;
        this.direction = direction;
    }

}

public class Solution {

    static Scanner sc;
    static int tc, N, M, K, r, c, num, direction;
    static Microbe[] tmp;
    static Deque<Microbe>[][] plate;
    static Deque<Microbe>[][] newPlate;

    static int[] deltaR = {-1, 1, 0, 0};
    static int[] deltaC = {0, 0, -1, 1};

    public static void main(String[] args) {

        sc = new Scanner(System.in);

        tc = sc.nextInt();

        for (int t = 1; t <= tc; t++) {

            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();

            plate = new Deque[N][N];

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    plate[r][c] = new ArrayDeque<>();
                }
            }

            for (int i = 0; i < K; i++) {
                r = sc.nextInt();
                c = sc.nextInt();
                num = sc.nextInt();
                direction = sc.nextInt() - 1;
                plate[r][c].addLast(new Microbe(num, direction));
            }

            int answer = 0;

            experiment();

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!plate[r][c].isEmpty()) {
                        while (!plate[r][c].isEmpty()) {
                            answer += plate[r][c].pollFirst().num;
                        }
                    }
                }
            }

            System.out.printf("#%d %d%n", t, answer);
        }

    }
    public static void experiment() {

        int time = 0;

        while (time < M) {

            newPlate = new Deque[N][N];
            
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    newPlate[r][c] = new ArrayDeque<>();
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    while (!plate[r][c].isEmpty()) {
                        Microbe current = plate[r][c].pollFirst();
                        if (current.num != 0) {
                            int newR = r + deltaR[current.direction];
                            int newC = c + deltaC[current.direction];

                            if (chemicalRange(newR, newC)) {
                                if (current.direction == 0 || current.direction == 2) current.direction += 1;
                                else if (current.direction == 1 || current.direction == 3) current.direction -= 1;
                                current.num /= 2;
                            }

                            newPlate[newR][newC].addLast(current);
                        }
                    }
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!newPlate[r][c].isEmpty() && newPlate[r][c].size() > 1) {
                        newPlate[r][c].addLast(findMaxMicrobe(newPlate[r][c]));
                    }
                }
            }

            plate = newPlate;

            time++;
        }
    }

    public static Microbe findMaxMicrobe(Deque<Microbe> plate) {

        int max = 0;
        int sum = 0;
        int maxDirection = 0;

        while (!plate.isEmpty()) {
            Microbe tmp = plate.pollFirst();
            sum += tmp.num;
            if (tmp.num > max) {
                max = tmp.num;
                maxDirection = tmp.direction;
            }
        }
        return new Microbe(sum, maxDirection);
    }


    public static boolean chemicalRange(int r, int c) {

        if (r == 0 && c >= 0 && c < N) return true;
        if (r == N - 1 && c >= 0 && c < N) return true;
        if (c == 0 && r >= 0 && r < N) return true;
        if (c == N - 1 && r >= 0 && r < N) return true;

        return false;

    }
}

```

## ✓ What I realized
- 2차원 배열을 쓰지 않아도 되겠다는 생각에 집착해서 시간을 많이 버린 것 같다. 일단 문제를 푸는 것이 중요하므로 2차원 배열을 사용해서 쉽게 풀릴 것 같으면 그 방법으로 푼 후 리팩토링 해보는 것으로 하자.
- 여러 가지 자료구조를 중첩해서 사용하다보니 잘못하면 헷갈릴 수 있었는데, 자료구조에 대한 이해가 충분히 된 후 사용하니 오히려 편리했던 것 같다. 이번 문제에서는 배열과 Deque를 사용했지만, 아직 내가 익숙하지 않은 PriorityQueue나 LinkedList도 얼른 공부해서 잘 써먹을 수 있도록 해야겠다.
- 배열과 델타탐색을 이용하는 문제에서는 항상 out of range를 유의해야 하는데, 이를 if문 내 모두 쓰는 것이 아니라 위 문제에서 만든 `chemicalRange` 메소드와 같이 만들면 훨씬 편하고 가독성도 좋아지는 것 같다. 앞으로 자주 활용해야겠다.