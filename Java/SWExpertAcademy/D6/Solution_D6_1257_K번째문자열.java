//영어 소문자로 된 문자열이 있다.
//이 문자열의 부분문자열은 문자열의 두 위치를 골라서, 이 사이의 연속한 문자열을 뽑아낸 것이다.
//두 위치가 같을 때는 길이가 1인 부분 문자열이 된다.
//예를 들어, 문자열 love의 모든 부분 문자열은 l, o, v, e, lo,ov, ve, lov, ove, love이다.
//또 다른 예로, 문자열 food의 부분 문자열은 f, o, d, fo, oo,od, foo, ood, food가 있다. 동일한 문자열 o가 두번 나오지만, 중복을 제거한 것에 유의하자.
//이 문자열에 대해서 사전 순서로 정렬을 하는 것을 고려해보자.
//두 문자열을 왼쪽부터 오른쪽으로 비교해나가면서, 처음으로 다른 글자가 나왔을 때 알파벳 순으로 먼저 나오는 문자가 있는 쪽이 순서가 앞이다.
//다른 글자가 나오기 전에 한 문자열이 끝난다면 이 문자열이 순서가 앞이다.
//
//love, food에 대해 사전 순서로 정렬하면 결과는 다음과 같다.
//	 순서 love의 부분 		문자열 food의 부분 문자열 
//1  e 					d 
//2  l 					f 
//3  lo 				fo 
//4  lov 				foo 
//5  love 				food 
//6  o 					o 
//7  ov 				od 
//8  ove 				oo 
//9  v 					ood 
//10 ve   
//
//문자열과 정수 K가 주어지고, 이 문자열의 부분 문자열들을 사전 순서대로 나열하였을 때 K번째에 오는 문자열을 출력하는 프로그램을 작성하시오.
//다음의 입출력 조건을 준수하시오.
//  
//[입력]
//가장 첫 줄은 전체 테스트 케이스의 수이다.
//각 테스트 케이스는 정수 K 하나가 쓰여진 줄 다음에 영어 소문자로 된 문자열이 쓰인 줄로 이루어진다.
//문자열의 길이는 최대 400이다.
//  
//[출력]
//각 테스트 케이스마다, 첫 줄에는 “#C”를 출력해야 하는데 C는 케이스 번호이다.
//같은 줄에 빈 칸을 하나 사이에 두고 이어서 사전 순서로 K번째 나오는 부분 문자열을 출력한다.
//만약 K번째 문자열이 존재하지 않는다면, “none”을 출력한다.

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Solution {
	static HashSet<String> hs;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);

		int T = Integer.parseInt(sc.nextLine());
		for (int test_case = 1; test_case <= T; ++test_case) {
			hs = new HashSet<String>();
			String ans = "";
			int n = Integer.parseInt(sc.nextLine());
			String str = sc.nextLine();

			int len = 1;
			int totalLen = str.length();
			String temp;
			while (len <= totalLen) {
				for (int i = 0; i <= totalLen - len; i++) {
					temp = str.substring(i, i + len);
					hs.add(temp);
				}
				len++;
			}
			ArrayList<String> temp2 = new ArrayList<String>();
			temp2.addAll(hs);
			Collections.sort(temp2);

			System.out.printf("#%d %s\n", test_case, temp2.get(n - 1));
		}
	}
}