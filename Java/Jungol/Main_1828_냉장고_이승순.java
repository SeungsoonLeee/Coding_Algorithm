package com.lss.jungol;

import java.util.Arrays;
import java.util.Scanner;

public class Main_1828_냉장고_이승순 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Chemical[] cs = new Chemical[N];
		int ans = 1;

		for (int i = 0; i < N; i++) {
			cs[i] = new Chemical(sc.nextInt(), sc.nextInt());
		}

		Arrays.sort(cs);
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				if (cs[i].over >= cs[j].under && cs[i].over <= cs[j].over) {
					continue;
				}
				ans++;
				i = j - 1;
				break;
			}
		}

		System.out.println(ans);
	}
}

class Chemical implements Comparable<Chemical> {
	int under;
	int over;

	public Chemical() {
	}

	public Chemical(int under, int over) {
		this.under = under;
		this.over = over;
	}

	@Override
	public int compareTo(Chemical o) {
		if (this.over == o.over)
			return Integer.compare(this.under, o.under);
		return Integer.compare(this.over, o.over);
	}
}
