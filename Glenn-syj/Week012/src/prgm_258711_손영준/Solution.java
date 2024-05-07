package prgm_258711_도넛과막대그래프;

import java.util.HashSet;
import java.util.Set;

/*

1. 조건
 1-1. 도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프
 1-2. 정점이 하나 추가됨
    -> 이는 곧, 여러 그래프들로 향하는 단 하나의 정점임
 1-3. 생성한 정점의 번호, 도넛 모양 그래프 수, 막대 모양 그래프 수, 8자 모양 그래프 수
2. 풀이
 2-1. unionfind를 이용해, 최상위 정점을 찾으면 그것이 곧 생성된 정점임
 2-2. 해당 정점에서 뻗어나가는 간선을 제외하면 모두 분리됨
 2-3. 나가는 간선이 2개인 점이 하나라도 있으면 8자 모양 그래프
        마지막 정점에서 나가는 간선이 없다면 막대 모양 그래프
        둘 다 아니면 도넛 모양 그래프

*/

class Solution {
    
    public int[] solution(int[][] edges) {
        
        int[] starts = new int[1000001];
        int[] ends = new int[1000001];
        int[] answer = new int[4];
        Set<Integer> vertexes = new HashSet<>();

        for (int[] edge: edges) {

            starts[edge[0]]++;
            ends[edge[1]]++;
            
            vertexes.add(edge[0]);
            vertexes.add(edge[1]);
        }
        
        int addedVertex = 0;
        
        for (int idx: vertexes) {
            if (starts[idx] >= 2 && ends[idx] == 0) {
                addedVertex = idx;
                break;
            }
        }
        for (int idx: vertexes) {
            if (idx == addedVertex) continue;
            if (starts[idx] >= 2 && ends[idx] >= 2) {
                answer[3]++;
            }
            if (starts[idx] == 0 && ends[idx] > 0) {
                answer[2]++;
            }
        }
        answer[1] = starts[addedVertex] - answer[3] - answer[2];
        answer[0] = addedVertex;
        
        return answer;
    }
    
}