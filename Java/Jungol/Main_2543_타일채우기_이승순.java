package com.ssafy.homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_2543_타일채우기_이승순 {
	static int N;
	static int[][] room;
	static boolean[][] check;
	static int[][] tile = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		room = new int[N][N];
		check = new boolean[N][N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int holeY = Integer.parseInt(st.nextToken());
		int holeX = Integer.parseInt(st.nextToken());

//		room[holeY][holeX] = 5;
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				bw.write(room[i][j] + " ");
//			}
//			bw.write("\n");
//		}
//		bw.flush();

		setTile(0, 0, holeY, holeX, N);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bw.write(room[i][j] + " ");
			}
			bw.write("\n");
		}
		bw.flush();
	}

	public static void setTile(int startY, int startX, int holeY, int holeX, int size) {
		if (size == 1)
			return;
		int half = size / 2;
		int centerY = startY + half - 1;
		int centerX = startX + half - 1;
		int select = 0;
		for (int i = 0; i < 4; i++) {
			int nextR = centerY + tile[i][0];
			int nextC = centerX + tile[i][1];
			if (holeY <= centerY && holeX <= centerX) { // 1번 타일
				if (i == 0) {
					setTile(startY, startX, holeY, holeX, half);
					continue;
				}
				room[nextR][nextC] = 1;
				if (select == 0)
					setTile(startY, nextC, nextR, nextC, half);
				else if (select == 1)
					setTile(nextR, startX, nextR, nextC, half);
				else
					setTile(nextR, nextC, nextR, nextC, half);
				select++;
			} else if (holeY <= centerY && holeX > centerX) { // 2번 타일
				if (i == 1) {
					setTile(startY, nextC, holeY, holeX, half);
					continue;
				}
				room[nextR][nextC] = 2;
				if (select == 0)
					setTile(startY, startX, nextR, nextC, half);
				else if (select == 1)
					setTile(nextR, startX, nextR, nextC, half);
				else
					setTile(nextR, nextC, nextR, nextC, half);
				select++;
			} else if (holeY > centerY && holeX <= centerX) { // 3번 타일
				if (i == 2) {
					setTile(nextR, startX, holeY, holeX, half);
					continue;
				}
				room[nextR][nextC] = 3;
				if (select == 0)
					setTile(startY, startX, nextR, nextC, half);
				else if (select == 1)
					setTile(startY, nextC, nextR, nextC, half);
				else
					setTile(nextR, nextC, nextR, nextC, half);
				select++;
			} else { // 4번타일
				if (i == 3) {
					setTile(nextR, nextC, holeY, holeX, half);
					continue;
				}
				room[nextR][nextC] = 4;
				if (select == 0)
					setTile(startY, startX, nextR, nextC, half);
				else if (select == 1)
					setTile(startY, nextC, nextR, nextC, half);
				else
					setTile(nextR, startX, nextR, nextC, half);
				select++;
			}
		}
	}
}
