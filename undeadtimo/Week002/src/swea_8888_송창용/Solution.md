# [D3] 시험 - 8888

4 ~ 5시간을 소모한 것 같다.

이 문제 덕분에 마음 건강 설문조사를 안좋게 냈다.

면담이 들어올 것 같아 무섭지만, 일단 문제나 파악해보자.

## 나의 로직 🧐

__아하! 이것은 조건을 세세하게 설정하기만 하면 풀 수 있는 문제구나!__

__난이도 상 문제를 골랐지만 또 뭔가 다른 사람들보다 쉬운 문제를 골라버린 것 같다!__

문제에서 주어지는 조건들을 세세하게, 그리고 천천히 구성하면서 출력값들을 구해보았다.

- 사람들이 문제를 풀었고, 그 중 '지학'이의 번호가 주어지는데, 지학이의 등수와 총 점수를 구하는 문제다.

- 우선 문제의 점수는 해당 문제를 풀지 못한 사람의 수이다.
    - 따라서 문제를 풀지 못한 사람의 수를 구해 문제의 배점을 구해 배열에 넣어주었다.

- 각 사람의 총점을 구해주었다.
    - 다만 지학이와 점수가 같은 사람이 있다면, 지학이보다 많은 문제를 푼 사람이 지학이보다 높은 것으로 간주하기 때문에,
    - 각 인원마다 문제를 푼 갯수를 구해줘야 한다.

- 만약? 지학이랑 점수도 같은데?? 문제를 푼 갯수도 같다면???
    - 지학이보다 앞 번호면 지학이보다 높은 것으로 간주한다.
---

<b><span style="font-size:130%"> 쉽잖아?! 😏</span></b>

<b><span style="font-size:130%"> .....</span></b>

<b><span style="font-size:130%"> 쉽지 않았다... 😩 </span></b>

---

## StringTokenizer! BufferedReader! 이게 뭔데... 😓😱

```
간단하지! 그냥 저 이상한 애들을 사용해서 가져오기만 하면 되잖아!
```

- 떠듬 떠듬 스터디 때 배웠던 StringTokenizer를 사용해서 입력값을 받아보았다...

### NoSuchElemnetError!!🚫

어, 어라? 이게 뭐지? 중간에 뭐 이상한 게 있나?

그래 !받아온 값을 출력해서 어디가 잘못됐는지 보자! 😀

```
[]
[0, 0, 1]
...
```

[ ] 이 친구는 뭐지...?

이클립스가 고장난 거 아냐? 자바 언어가 맛이 간 거 아냐?!

마법처럼 생겨난 빈 괄호 [ ] 를 없애기 위해 시간을 많이 소모했다.

---

### next(), nextInt() __Before__ nextLine()
- 이전에 문제를 풀면서, nextInt를 사용한 이후에 nextLine을 사용하면

- 개행문자 (\n, enter)가 nextLine에 입력값으로 들어가버린다는 것을 알았다.

___ 
```
그렇기에, 
'nextLine()'을 사용하기 전에는, 
'nextInt()' 대신에 'next()'만 사용해주면 괜찮겠구나!!
```
<b><span style="font-size:130%">라고 생각했다.</span></b>


<b><span style="font-size:130%">. . . . .</span></b>

 갑자기 나타난 빈 배열 친구를 없애려고 이것저것 건드리다가,

<b><span style="font-size:130%">next를 nextLine으로 바꿔서</span></b> 입력값들을 살펴보니, 빈 배열이 사라졌다!!

<b><span style="font-size:200%">👿 😹 😤 </span></b>


- 비록 BefferedReader를 사용하여 문제를 풀지는 않았지만
- StringTokenizer 객체와 nextLine() 메소드를 이용하여
- 입력값을 받아오는 시간을 줄여 시간초과 판정에서 벗어날 수 있었다. 





[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AW45RuSae2gDFAQ7) 

### 성능 요약

메모리: 156,340 KB, 시간: 2,272 ms, 코드길이: 2,762 Bytes

### 제출 일자

2024-02-06 22:09



> 출처: SW Expert Academy, https://swexpertacademy.com/main/code/problem/problemList.do