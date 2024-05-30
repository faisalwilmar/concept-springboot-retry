package com.demo.retryer.controller;

import com.demo.retryer.dto.Response;
import com.demo.retryer.dto.bookledger.BookLedgerResponse;
import com.demo.retryer.service.bookledger.BookLedger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MainController {

     private final BookLedger bookLedger;

     @GetMapping("/basic-response")
     public ResponseEntity<Response<BookLedgerResponse>> welcomeBasicResponse(@RequestParam String someString) {

          BookLedgerResponse bookLedgerResponse = bookLedger.postNewBookLedger(someString);
          Response<BookLedgerResponse> rs = new Response<>();
          rs.setCode("200");
          rs.setMessage(someString);
          rs.setStatus(true);
          rs.setData(bookLedgerResponse);

          return ResponseEntity.ok(rs);
     }
}
