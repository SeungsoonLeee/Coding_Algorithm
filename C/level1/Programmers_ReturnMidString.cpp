// 단어 s의 가운데 글자를 반환하는 함수, solution을 만들어 보세요.
// 단어의 길이가 짝수라면 가운데 두글자를 반환하면 됩니다.
//ex)
//입력    출력
//abcde   c
//qwer    we
#include <string>
#include <vector>
using namespace std;

string solution(string s) {
    string answer = "";
    int mid = s.length() / 2;
    
    if(s.length() % 2 == 0){
        answer += s[mid - 1];
        answer += s[mid];
    }
    else{
        answer += s[mid];
    }
    
    return answer;
}