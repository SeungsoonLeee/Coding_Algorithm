//[���α׷��ӽ�]
//���� ����
//������ ������ �������� �����濡 �����Ͽ����ϴ�.�� �� ���� ������ �����ϰ�� ��� ������ �������� �����Ͽ����ϴ�.
//�����濡 ������ �������� �̸��� ��� �迭 participant�� ������ �������� �̸��� ��� �迭 completion�� �־��� ��, �������� ���� ������ �̸��� return �ϵ��� solution �Լ��� �ۼ����ּ���.

//���ѻ���
//������ ��⿡ ������ ������ ���� 1�� �̻� 100, 000�� �����Դϴ�.
//completion�� ���̴� participant�� ���̺��� 1 �۽��ϴ�.
//�������� �̸��� 1�� �̻� 20�� ������ ���ĺ� �ҹ��ڷ� �̷���� �ֽ��ϴ�.
//������ �߿��� ���������� ���� �� �ֽ��ϴ�.

#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

string solution(vector<string> participant, vector<string> completion) {
	string answer = "";
	int idx_p = 0, idx_c = 0;
	sort(participant.begin(), participant.end());
	sort(completion.begin(), completion.end());
	for (int i = 0; i < participant.size(); ++i) {
		if (participant[idx_p] != completion[idx_c]) {
			answer = participant[idx_p];
			idx_p++;
		}
		else {
			idx_p++;
			idx_c++;
		}
	}
	return answer;
}