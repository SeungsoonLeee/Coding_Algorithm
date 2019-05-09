//[���α׷��ӽ�]
//�迭 array�� i��° ���ں��� j��° ���ڱ��� �ڸ��� �������� ��, k��°�� �ִ� ���� ���Ϸ� �մϴ�.
//���� ��� array��[1, 5, 2, 6, 3, 7, 4], i = 2, j = 5, k = 3�̶��
//
//array�� 2��°���� 5��°���� �ڸ���[5, 2, 6, 3]�Դϴ�.
//1���� ���� �迭�� �����ϸ�[2, 3, 5, 6]�Դϴ�.
//2���� ���� �迭�� 3��° ���ڴ� 5�Դϴ�.

#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int getSortAns(vector<int> arr, int st, int e, int idx) {
	//arr�� st���� e������ ���� �����ϰ�, idx��° ���� ��ȯ�ϴ� �޼ҵ�
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