//2부터 N까지의 모든 소수의 합 구하기.
//"에라토스테네스의 체" 이용

#include <cstdio>
using namespace std;
const int MAX = 10000051;

long long solution(int N) {
	long long answer = 0;
	bool isPrime[MAX];
	for (int i = 2; i <= N; ++i) {
		isPrime[i] = true;
	}
	
	for (int i = 2; i <= N; ++i) {
		if (isPrime[i]) {
			answer += i;
			for (int j = i * 2; j <= N; j += i) {
				isPrime[j] = false;
			}
		}
	}
	return answer;
}