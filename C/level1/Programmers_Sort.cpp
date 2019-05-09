//[프로그래머스]
//배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.
//예를 들어 array가[1, 5, 2, 6, 3, 7, 4], i = 2, j = 5, k = 3이라면
//
//array의 2번째부터 5번째까지 자르면[5, 2, 6, 3]입니다.
//1에서 나온 배열을 정렬하면[2, 3, 5, 6]입니다.
//2에서 나온 배열의 3번째 숫자는 5입니다.

#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int getSortAns(vector<int> arr, int st, int e, int idx) {
	//arr의 st부터 e까지의 수를 정렬하고, idx번째 수를 반환하는 메소드
	vector<int> temp;
	for (int i = st - 1; i <= e - 1; ++i) {
		temp.push_back(arr[i]);
	}
	sort(temp.begin(), temp.end());
	return temp[idx - 1];
}

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
	vector<int> answer;

	for (int i = 0; i < commands.size(); ++i) {
		answer.push_back(getSortAns(array, commands[i][0], commands[i][1], commands[i][2]));
	}
	return answer;
}