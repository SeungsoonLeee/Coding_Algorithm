// 2016년 1월 1일은 금요일입니다. 2016년 a월 b일은 무슨 요일일까요?
// 두 수 a ,b를 입력받아 2016년 a월 b일이 무슨 요일인지 리턴하는 함수, solution을 완성하세요.

#include <string>
#include <vector>
using namespace std;

string solution(int a, int b) {
    string answer = "";
    int total_days = -1; //1월1일의 날짜를 빼주기 위해 -1로 초기화
    int days[12] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    string weeks[7] = {"FRI","SAT","SUN","MON","TUE","WED","THU"};
    for(int i=0; i<a-1; ++i)
        total_days += days[i];
    total_days += b;
    
    int week = total_days % 7;
    answer += weeks[week];
    return answer;
}