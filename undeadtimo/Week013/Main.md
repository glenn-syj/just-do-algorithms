# 1043 - 거짓말

[문제 링크](https://www.acmicpc.net/problem/1043)

## 로직

진실을 알고 있는 사람들을 시작으로 같은 파티에 있는 사람들과 union 해주는 방식을 통해 문제를 해결하였다.

### 진실을 알게 된 사람을 구분해줄 수 있는 boolean 배열을 만들어준다.

```java
int know_num = Integer.parseInt(st.nextToken());
		
		// 진실을 아는 사람을 넣어준다.
		for(int i = 1; i <= know_num; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			people_know[tmp] = true;
		}
```

### 로직을 통해 한 번 처리한 사람을 구분해줄 수 있는 visit 배열을 만들어준다.

```java

boolean[] visited = new boolean[n + 1];

```

반복문을 통해, 사용할 수 있는 사다리의 갯수를 직접적으로 지정해주고, dfs 함수에 해당 사다리의 갯수를 매개변수로 사용하였다.

이러한 사용할 수 있는 사다리의 갯수를 적은 숫자부터 하나씩 할당하며 확인하는 방식은,

그렇지 않고 아무것도 놓지 않았을 때, 하나를 놓았을 때, 두 개를 놓았을 때, 세 개를 놓았을 때를 한 번에 확인하는 방식보다

'세 배' 정도 빠른 속도로 문제를 해결했다.

```
사다리의 갯수를 지정했을 때의 속도는 대략 360ms,
사다리의 갯수를 지정하지 않았을 때의 속도는 대략 1080ms이 나왔다.
```

### 파티에서 진실을 알고 있는 사람이 아무도 없는 것을 체크하는 로직

```java

int result = 0;
		for(int i = 1; i <= m; i++) {
			boolean flag = false;
			for(int person : parties[i]) {
				if(people_know[person]) {
					flag = true;
					break;
				}
			}
			if(!flag)result++;
		}

```




<br>