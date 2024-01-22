# What is just-do-algorithms?

## 1. Organization

### 1-1. Introduction

**그냥하자(just-do-algorithms)** 는 2024년 SSAFY (Samsung Software Academy For Youth) 내에서 조직된 알고리즘 스터디 그룹입니다. 공통 문제와 난이도 별 문제를 각자 풀고 함께 논의하며 알고리즘 실력의 향상을 도모합니다. 물론, git의 사용과 커밋 컨벤션(conventinoal commits)에 익숙해지면 더 좋겠죠!

### 1-2. Contributors

- **박건택**(qkrrjsxor)
- **손영준**(glenn-syj)         
- **송창용**(undeadtimo)
- **유서현**(ucream11)
- **표다영**(celestedayoung)
- **황민욱**(minukHwang)

## 2. Conventions

### 2-1. Organization Rules

```java
(1) 목적: 문제 풀기 전 항상 "자신의 로직" 적기, 문제 풀이 시도 후 고민도 기록하기
(2) 책임: 각 문제 난이도 별 "2명 권장", 몰릴 시에는 알아서 정하기
(3) 도전: 공통 문제 + 맡은 문제 다 풀었으면, "다른 문제도 풀어보기"
(4) 효율: 모임 전에 "남의 코드 읽어오기"
(5) 기한: "수요일 오전 9시까지" 코드 제출 하기
(6) 위약: 일단... "라이브 코딩"
```

### 2-2. Submission

**File Format**

```java
1. 코드 내 주석 처리: .java 파일만 제출

		파일명: "<Week>_<Platform>_문제번호_성함.java"
		cf) Week0_SWEA_1234_손영준.java

2. 종이에 작성: .java파일과 함께 수기 자료 제출

		파일명: "<Week>_<Platform>_문제번호_성함.png/pdf/..."
		(기본 방식은 자바와 동일)
```


**Fork and Pull Request**

```java
(1) 각자 원본 Repository에 있는 "자신의 브랜치" Fork 하기
(2) 이후 "자신의 Forked Repository"에서 Commit, Push 등 작업하기
(3) Forked Repository에서 원본 Repository 내 "자신의 브랜치"로 Pull Request
```


### 2-3. Conventional Commits

```java
// 1. 기본 커밋 메시지 형식

(1) git commit -m "[ALG-<주차>] <Type>: <Title>"

    예를 들어서 1주차의 문제 1234번 문제를 "풀었을 때"
    O: git commit -m "[ALG-001] Solve: SWEA 문제 1234번 풀이"
    X: git commit -m "[ALG-001] Solve: SWEA Problem 1234"
		
// 2. 커밋 메시지 컨벤션

(1) 본문과 꼬릿말은 선택적 (궁금하신 분은 질문... 쓸 일 크게 없을 것 같기도)
(2) <주차>는 000, 001, 002, ..., 010, 011, ... 
(3) <Type> 작성법

		A. <Type> 종류
		
		Solve: "스스로 통과한" 문제의 정답을 제출할 때
		Peek: "남의 코드를 참고해 통과한" 문제의 정답을 제출할 때
		Perf: "통과해서 Solve/Peek으로 올렸던" 문제의 코드를 개선할 때
		Try: "통과하지 못한" 문제의 코드를 올릴 때
		Fix: "통과하지 못했었던" 문제의 코드를 고쳐서 통과됐을 때
		Refactor: "성능에 영향을 미치지 않는" 코드 내 변수명 등의 수정만 있을 때
        Docs: "로직 관련 수기/사진 자료나 주석" 관련 변경
        
        (물론 Solve, Peek, Try 만 사용해도 무방합니다...)

		B. <Type> 시작은 대문자로 쓰기

		O: Solve, Peek, Perf, ...
		X: solve, peek, perf, ...

(4) <Title> 작성법
		
		- "백준 문제 1234번 풀이" / "SWEA 문제 1235번 변수 이름 수정" 등
		- "<Platform> 문제 <Number>" 만 지켜지면 OK
		- 끝에 마침표('.') 찍지 않기

// 3. 기타 문의사항

간단한 것이라도 좋으니 무조건 물어보기!
git 관련 어려운 것도 언제나...
```
