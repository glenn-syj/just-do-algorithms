# PRGM 258712. 가장 많이 받은 선물

## ✓ Difficult Point

- 단순 구현 문제인 줄 알았으나, 주어진 친구들의 이름이 String이기 때문에 이를 어떻게 각 자료구조의 index와 매칭 시킬지 고민했다.
- Hash Map을 자주 사용해보지 않아 이를 활용해 위 문제를 해결하려 했다.

## ✓ Used Variables & Data Structures

- `friendsNum`: 친구들의 수
- `friendsInfo`: 친구의 이름을 키로 하고 그 친구의 인덱스를 값으로 하는 hash map
- `giftDegree`: 각 친구의 순 선물 수치를 나타내는 배열. 선물을 줄 때마다 1씩 증가하고 받을 때마다 1씩 감소
- `giftGraph`: 친구들 간의 선물 교환 상태를 나타내는 2차원 배열로, `giftGraph[i][j]`는 `i`번 친구가 `j`번 친구에게 준 선물의 수

## ✓ Logic

1. `friendsInfo` hash map을 초기화하여 각 친구의 이름에 해당하는 인덱스를 저장
2. 선물 교환 정보를 분석하여 `giftDegree`와 `giftGraph`를 업데이트
3. 모든 친구들을 반복하면서 다른 친구들과의 선물 교환 상태를 비교
4. 만약 어떤 친구 `i`가 다른 친구 `j`에게 준 선물이 `j`가 `i`에게 준 선물보다 많거나, 선물을 같은 수 만큼 주고 받았지만 `i`가 선물을 더 많이 보낸 경우, 이런 조건들을 만족하는 친구의 수를 계산
5. 모든 친구들에 대해 계산된 `num`중 최댓값을 `answer`로 반환

## ✓ Code

```java
import java.util.HashMap;

class Solution {
    public int solution(String[] friends, String[] gifts) {

        int answer = 0;
        int friendsNum = friends.length;

        HashMap<String, Integer> friendsInfo = new HashMap<>();

        int[] giftDegree = new int[friendsNum];
        int[][] giftGraph = new int[friendsNum][friendsNum];

        for ( int i = 0; i < friendsNum; i++ ) {
            friendsInfo.put(friends[i], i);
        }

        for ( String gift : gifts ) {
            String[] giftName = gift.split(" ");
            giftDegree[friendsInfo.get(giftName[0])]++;
            giftDegree[friendsInfo.get(giftName[1])]--;
            giftGraph[friendsInfo.get(giftName[0])][friendsInfo.get(giftName[1])]++;
        }

        for ( int i = 0; i < friendsNum; i++) {
            int num = 0;
            for ( int j = 0; j < friendsNum; j++) {
                if (i == j) continue;

                if (giftGraph[i][j] > giftGraph[j][i] ||
                     (giftGraph[i][j] == giftGraph[j][i] && giftDegree[i] > giftDegree[j])) {
                        num++;
                    }
            }

            if (answer < num) {
                answer = num;
            }

        }

        return answer;
    }
}

```

## ✓ What I realized

- 문제에서 다행히 표를 이용한 풀이를 예시로 제시해주었지만, 그렇지 않았다면 표를 통해 정리한 후 접근할 생각을 쉽게 떠올리지 못했을 것이다. 이와 같이 표를 통해 접근할 수 있는 문제는 2차원 배열로 만들면 된다는 것을 습득할 수 있었다.
- if문에 조건을 넣을 때 `||` 연산자와 `&&` 연산자를 함께 사용할 때 순서를 바꾸면 조건이 다르게 해석될 수 있다는 점을 유의하지 못해 해당 부분에서 시간이 걸렸다. 간단해 보이더라도 천천히 논리적으로 생각한 후 코드를 작성하도록 노력해야겠다.
