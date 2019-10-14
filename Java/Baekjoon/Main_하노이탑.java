package com.ssafy.step2.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_하노이탑 {
	static int cnt = 0;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		cnt = (int) Math.pow(2, n) - 1;
		bw.write(cnt + "\n");
		hanoi(n, 1, 2, 3);
		bw.flush();

	}

	public static void hanoi(int n, int from, int temp, int to) throws IOException {
		if (n == 1) {
			bw.write(from + " " + to + "\n");
		} else {
			hanoi(n - 1, from, to, temp);
			bw.write(from + " " + to + "\n");
			hanoi(n - 1, temp, from, to);
		}
	}
}
