package com.project.demo.service;

import java.time.LocalDate;
import com.project.demo.repository.*;
import com.project.demo.model.*;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class BorrowingService {
    private final BorrowingRecordRepository recordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    
    @Transactional
    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        Patron patron = patronRepository.findById(patronId).orElseThrow();
        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowDate(java.sql.Date.valueOf(LocalDate.now()));
        return recordRepository.save(record);
    }
    
    @Transactional
    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        Optional<BorrowingRecord> recordOpt = recordRepository.findAll().stream()
                .filter(r -> r.getBook().getId().equals(bookId) && r.getPatron().getId().equals(patronId) && r.getReturnDate() == null)
                .findFirst();
        if (recordOpt.isEmpty()) throw new RuntimeException("No active borrow found.");
        BorrowingRecord record = recordOpt.get();
        record.setReturnDate(java.sql.Date.valueOf(LocalDate.now()));
        return recordRepository.save(record);
    }
}
