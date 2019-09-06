package com.lss.baekjoon;

//Main_17143_낚시왕
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_17143_낚시왕 {
	static int R, C, M, ans;
	static int[][] map;
	static ArrayList<Shark2> sharks;
	static int[][] tempMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		tempMap = new int[R][C];
		sharks = new ArrayList<Shark2>();
		ans = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			sharks.add(new Shark2(r, c, s, d, z));
			map[r][c] = z;
		}

		for (int i = 0; i < C; i++) {
			startFishing(i);
			moveSharks();
		}

		System.out.println(ans);
	}

	private static void startFishing(int c) {
		for (int i = 0; i < R; i++) {
			if (map[i][c] != 0) {
				sharks.remove(findSharkIdx(map[i][c], sharks));
				ans += map[i][c];
				map[i][c] = 0;
				return;
			}
		}
	}

	private static int findSharkIdx(int size, ArrayList<Shark2> arr) {
		for (int j = 0; j < arr.size(); j++) {
			if (arr.get(j).z == size)
				return j;
		}
		return -1;
	}

	private static void moveSharks() {
		ArrayList<Shark2> tempShark = new ArrayList<Shark2>();
		for (int i = 0; i < sharks.size(); i++) {
			int curR = sharks.get(i).r;
			int curC = sharks.get(i).c;
			int moveS = 0;
			// 움직여야 하는 거리를 축소시켜주고
			if (sharks.get(i).d == 0 || sharks.get(i).d == 1)
				moveS = sharks.get(i).s % (2 * (R - 1));
			if (sharks.get(i).d == 2 || sharks.get(i).d == 3)
				moveS = sharks.get(i).s % (2 * (C - 1));
			// 축소시킨 거리만큼 움직이는데
			for (int move = 1; move <= moveS; move++) {
				if (sharks.get(i).d == 0)
					curR--;
				else if (sharks.get(i).d == 1)
					curR++;
				else if (sharks.get(i).d == 2)
					curC++;
				else
					curC--;
				// 경계면 방향을 전환한다.
				if (curR < 0 || curR >= R || curC < 0 || curC >= C) {
					if (sharks.get(i).d == 0) {
						sharks.get(i).d = 1;
						curR += 2;
					} else if (sharks.get(i).d == 1) {
						sharks.get(i).d = 0;
						curR -= 2;
					} else if (sharks.get(i).d == 2) {
						sharks.get(i).d = 3;
						curC -= 2;
					} else {
						sharks.get(i).d = 2;
						curC += 2;
					}
				}
			}

			// 이동한 위치로 상어 좌표 수정
			sharks.set(i, new Shark2(curR, curC, sharks.get(i).s, sharks.get(i).d, sharks.get(i).z));
			tempShark.add(sharks.get(i));
			// 이동을 마친 장소에 다른 상어가 있는지 체크
			if (tempMap[curR][curC] != 0) { // 다른 상어가 존재하면
				// 크기가 큰 상어가 작은 상어를 잡아먹음
				Shark2 temp = tempShark.get(findSharkIdx(tempMap[curR][curC], tempShark));
				if (temp.z < sharks.get(i).z) {
					tempShark.remove(findSharkIdx(temp.z, tempShark));
					tempMap[curR][curC] = sharks.get(i).z;
				} else {
					tempShark.remove(findSharkIdx(sharks.get(i).z, tempShark));
				}
			} else { // 존재하지 않으면 위치 저장
				tempMap[curR][curC] = sharks.get(i).z;
			}
		}

		sharks = tempShark;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = tempMap[i][j];
//				System.out.print(tempMap[i][j] + " ");
				tempMap[i][j] = 0;
			}
//			System.out.println();
		}
//		System.out.println();
	}
}

class Shark2 {
	int r;
	int c;
	int s; // 속도
	int d; // 방향(상하우좌 순)
	int z; // 크기

	public Shark2() {
	}

	public Shark2(int r, int c, int s, int d, int z) {
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
	}
}