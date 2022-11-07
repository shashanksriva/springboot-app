package com.dbank.nace.controller;

import com.dbank.nace.entity.NaceData;
import com.dbank.nace.helper.CsvHelper;
import com.dbank.nace.helper.ResponseMessage;
import com.dbank.nace.service.NaceService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

//@CrossOrigin("http://localhost:8081")
@Api(tags="CSV handler",
        description = "Provides API to perform CSV related operations")
@Controller
@RequestMapping("/csv")
@Slf4j
public class CsvController {

    @Autowired
    NaceService naceService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String message = "";
        log.info("File Size ::>>> " + file.getSize());
        //log.info(file.getBytes().toString());
        if (CsvHelper.isCsvFormat(file)) {
            try {
                naceService.save(file);
                message = "File upload successful: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "File not uploaded: " + file.getOriginalFilename() + " " +e.getMessage() + "!";
                //e.printStackTrace();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
}
