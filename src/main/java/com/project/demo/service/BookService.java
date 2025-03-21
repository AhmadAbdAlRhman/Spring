package com.project.demo.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import com.project.demo.model.Book;
import com.project.demo.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable("books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Cacheable(value = "book", key = "#id")
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @CacheEvict(value = "books", allEntries = true)
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @CacheEvict(value = "book", key = "#id") // حذف التخزين المؤقت عند تحديث كتاب معين
    @Transactional
    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublicationYear(updatedBook.getPublicationYear());
            book.setIsbn(updatedBook.getIsbn());
            return bookRepository.save(book);
        }).orElse(null);
    }

    @CacheEvict(value = {"book", "books"}, key = "#id") // حذف البيانات المؤقتة عند الحذف
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}