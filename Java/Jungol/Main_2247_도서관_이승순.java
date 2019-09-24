package com.lss.jungol;

import java.util.Arrays;
import java.util.Scanner;

public class Main_2247_도서관_이승순 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		InLibrary[] inLibrary = new InLibrary[N];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int notEmptyTime = 0;
		int emptyTime = 0;

		for (int i = 0; i < N; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			inLibrary[i] = new InLibrary(s, e);
			if (s < min)
				min = s;
			if (e > max)
				max = e;
		}

		Arrays.sort(inLibrary);

		notEmptyTime = inLibrary[0].e - inLibrary[0].s;
		for (int i = 1; i < N; i++) {
			// 안겹치는 경우
			if (inLibrary[i - 1].e < inLibrary[i].s) {
				int temp = inLibrary[i - 1].e - inLibrary[i - 1].s;
				if (temp > notEmptyTime)
					notEmptyTime = temp;
				temp = inLibrary[i].e - inLibrary[i].s;
				if (temp > notEmptyTime)
					notEmptyTime = temp;
				temp = inLibrary[i].s - inLibrary[i - 1].e;
				if (temp > emptyTime)
					emptyTime = temp;
			}
			// 뒤가 앞을 포함해 버리는 경우
			else if (inLibrary[i - 1].s >= inLibrary[i].s && inLibrary[i - 1].e <= inLibrary[i].e) {
				int temp = inLibrary[i].s + inLibrary[i].e;
				if (temp > notEmptyTime)
					notEmptyTime = temp;
			}
			// 뒤의 일부와 앞의 일부가 겹치는 경우
			else if (inLibrary[i - 1].s < inLibrary[i].s && inLibrary[i - 1].e <= inLibrary[i].e) {
				int temp = inLibrary[i - 1].s + inLibrary[i].e;
				if (temp > notEmptyTime)
					notEmptyTime = temp;
			}
		}

		System.out.println(min + " " + max);
		System.out.println(notEmptyTime + " " + emptyTime);
		
		// 1명의 학생이라도 포함되는 가장 긴 앞부분을 체크해야 함
	}
}

class InLibrary implements Comparable<InLibrary> {
	int s;
	int e;

	public InLibrary() {
	}

	public InLibrary(int s, int e) {
		super();
		this.s = s;
		this.e = e;
	}

	@Override
	public int compareTo(InLibrary o) {
		if (this.e == o.e) {
			return this.s - o.s;
		}
		return this.e - o.e;
	}
}