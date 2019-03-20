package com.backup;

public class CourseApp {

	/*

	public List<Book> getBooksList() {
		List<Book> booksList = new ArrayList<>();
		try (FileInputStream fileIn = new FileInputStream("books.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);) {
			booksList = (ArrayList<Book>) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return booksList;
	}

	

	public void searchBook(List<Book> existingBookList) {
		System.out.println("Enter title to search:");
		String searchStr = sc.nextLine();
		List<Book> selectedBooks = existingBookList.stream().filter(book -> book.getTitle().contains(searchStr))
				.collect(Collectors.toList());
		if (Objects.nonNull(selectedBooks)) {
			selectedBooks.stream().forEach(book -> {
				System.out.println(book.getBookId() + ", " + book.getTitle());
			});
		} else {
			System.out.println("No books found");
		}
	}

	public void addBook(List<Book> existingBookList) {
		System.out.println("Enter title:");
		String title = sc.nextLine();
		System.out.println("Enter price:");
		double price = sc.nextDouble();
		System.out.println("Enter volume:");
		int volume = sc.nextInt();
		String publishedDate = sc.nextLine();
		LocalDate pubDate = LocalDate.parse(publishedDate, DateTimeFormatter.ofPattern("d/MM/yyyy"));

		Book lastBook = existingBookList.get(existingBookList.size());
		Book newBook = new Book(lastBook.getBookId() + 1, title, price, volume, pubDate);
		existingBookList.add(newBook);
		saveNewBook(newBook);

	}

	private void saveNewBook(Book newBook) {
		try (FileOutputStream file = new FileOutputStream("books.ser");
				ObjectOutputStream out = new ObjectOutputStream(file);) {
			out.writeObject(newBook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Book saved");
	}*/
}
