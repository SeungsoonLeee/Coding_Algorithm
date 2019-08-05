//다음 주어진 조건에 따라 n개의 수를 처리하면 8자리의 암호를 생성할 수 있다.
//- 8개의 숫자를 입력 받는다.
//- 첫 번째 숫자를 1 감소한 뒤, 맨 뒤로 보낸다. 
//다음 첫 번째 수는 2 감소한 뒤 맨 뒤로, 그 다음 첫 번째 수는 3을 감소하고 맨 뒤로, 그 다음 수는 4, 그 다음 수는 5를 감소한다.
//이와 같은 작업을 한 사이클이라 한다.
//- 숫자가 감소할 때 0보다 작아지는 경우 0으로 유지되며, 프로그램은 종료된다. 이 때의 8자리의 숫자 값이 암호가 된다.

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_D3_1225_암호생성기_이승순 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			int n = sc.nextInt();
			Queue<Integer> queue = new LinkedList<Integer>();

			for (int i = 0; i < 8; i++) {
				queue.offer(sc.nextInt());
			}

			int cnt = 1;
			while (true) {
				if (cnt > 5) cnt = 1;
				int temp = queue.poll() - cnt;
				if (temp <= 0) {
					temp = 0;
					queue.offer(temp);
					break;
				}
				queue.offer(temp);
				cnt++;
			}

			System.out.print("#" + test_case);
			while (!queue.isEmpty())
				System.out.print(" " + queue.poll());
			System.out.println();
		}
	}
}
