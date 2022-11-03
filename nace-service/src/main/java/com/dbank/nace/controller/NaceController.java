package com.dbank.nace.controller;

import com.dbank.nace.entity.NaceData;
import com.dbank.nace.service.NaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="NACE controller api", description="Provides API to persist/retrieve data from NACE DB")
@RestController
@RequestMapping("/api")
@Slf4j
public class NaceController {

    @Autowired
    private NaceService naceService;

    @PostMapping("/")
    @ApiOperation(value="Persist a NACE data in table",
            notes="This post method saves NACE data information in a table in one single row",
            response=NaceData.class)
    public NaceData postNaceData(@RequestBody NaceData naceData) {
        log.info("Inside post of NaceController");
        return naceService.saveNaceData(naceData);
    }

    @GetMapping("/{order}")
    @ApiOperation(value="Find Nace data by an order",
    notes="Get a single row associated with an order",
    response=NaceData.class)
    public NaceData getNaceDataById(@PathVariable("order") Long orderId) {
        log.info("Inside getNaceDataById of NaceController");
        return naceService.getNaceData(orderId);
    }

    @GetMapping("/")
    @ApiOperation(value="Get all NACE data",
            notes="Get all the data present in NACE database",
            response=NaceData.class)
    public List<NaceData> getAll() {
        log.info("Inside getAllData of NaceController");
        return naceService.getAllData();
    }

    @DeleteMapping("/{orderId}")
    @ApiOperation(value = "Delete a NACE data")
    public void deleteNaceData(@PathVariable Long orderId){
        naceService.deleteNaceData(orderId);
    }

}
