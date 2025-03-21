package com.project.demo.controller;
import org.springframework.web.bind.annotation.*;
import com.project.demo.service.BorrowingService;
import com.project.demo.model.BorrowingRecord;
@RestController
@RequestMapping("/api")
class BorrowingController {
    private final BorrowingService borrowingService;
    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public BorrowingRecord borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        return borrowingService.borrowBook(bookId, patronId);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public BorrowingRecord returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        return borrowingService.returnBook(bookId, patronId);
    }
}
