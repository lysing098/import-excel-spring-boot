package com.example.importexcel.controller;

import com.example.importexcel.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/supply")
public class SupplyController {

    @Autowired
    private SupplyService supplyService;

    @PostMapping("/import-excel")
    public String importExcel(@RequestParam("file") MultipartFile file)throws IOException {
        supplyService.importExcel(file);
        return "import success";
    }

}
