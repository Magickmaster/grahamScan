package test;

import java.util.Collections;

import list.MyList;

public class ListTest {
	public static void main(String[] args) {
		System.out.println("Starting tests");
		MyList<Integer> list = new MyList<>();
		System.out.println("adding items");
		for (int i = 0; i < 30; i++) {
			list.add(30-i);
			System.out.println(list.get(i));
		}
		System.out.println("printing items");
		for (int i : list) {
			System.out.println(i);
		}
		System.out.println("shuffling items");
		//Collections.shuffle(list);
		for (int i : list) {
			System.out.println(i);
		}
		System.out.println("sorting items");
		Collections.sort(list);
		for (int i : list) {
			System.out.println(i);
		}

	}

}
