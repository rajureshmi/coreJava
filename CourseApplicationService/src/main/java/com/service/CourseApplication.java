package com.service;

import java.util.Map;
import java.util.Scanner;

import com.google.common.collect.ImmutableMap;

public class CourseApplication {

	private static final String BOOK = "Book";
	private static final String SUBJECT = "Subject";
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		int selectedOption = getSelectedOption();
		switch (selectedOption) {
		case 1:
			getHandler(BOOK).search();
			break;
		case 2:
			getHandler(BOOK).add();
			break;
		case 3:
			getHandler(BOOK).delete();
			break;
		case 4:
			getHandler(SUBJECT).search();
			break;
		case 5:
			getHandler(SUBJECT).add();
			break;
		case 6:
			getHandler(SUBJECT).delete();
			break;
		default:
			break;
		}
	}

	public static int getSelectedOption() {
		System.out.println("1. Search Book");
		System.out.println("2. Add Book");
		System.out.println("3. Delete Book");
		System.out.println("4. Search Subject");
		System.out.println("5. Add Subject");
		System.out.println("6. Delete Subject");
		System.out.println("7. Quit");
		System.out.print("Enter Your Choice : ");

		return sc.nextInt();
	}

	static Map<String, CourseHandler> handlerMap = ImmutableMap.<String, CourseHandler>builder()
			.put(BOOK, new BookHandler()).put(SUBJECT, new SubjectHandler()).build();

	private static CourseHandler getHandler(String input) {
		return handlerMap.get(input);
	}

}
