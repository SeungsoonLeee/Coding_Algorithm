// 1부터 입력받은 숫자 n 사이에 있는 소수의 개수를 반환하는 함수, solution을 만들어 보세요.
// 소수는 1과 자기 자신으로만 나누어지는 수를 의미합니다.
// (1은 소수가 아닙니다.)

#include <string>
#include <vector>
using namespace std;

int solution(int n) {
    bool isPrime[n+1];
    int answer = 0;
    for(int i=2; i<=n; ++i){
        isPrime[i] = true;
    }
    for(int i=2; i<=n; ++i){
        if(isPrime[i]){
            answer++;
            for(int j=i*2; j<=n; j+=i){
                isPrime[j] = false;
            }
        }
    }
    return answer;
}