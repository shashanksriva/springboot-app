package com.dbank.nace.service;

import com.dbank.nace.entity.NaceData;
import com.dbank.nace.exceptions.NaceNotFoundException;
import com.dbank.nace.helper.CsvHelper;
import com.dbank.nace.repository.NaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class NaceService {

    @Autowired
    private NaceRepository naceRepository;

    public NaceData saveNaceData(NaceData naceData) {
        log.info("Inside save of Nac Service");
        return naceRepository.save(naceData);
    }

    public NaceData getNaceData(Long id) {
        return naceRepository.findById(id).orElseThrow(() -> new NaceNotFoundException(id));
    }

    public void save(MultipartFile file) {
        try {
            List<NaceData> naceDataList = CsvHelper.csvToDbOpenCsv(file.getInputStream());
            naceRepository.saveAll(naceDataList);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<NaceData> getAllData() {
        List<NaceData> naceDataList = naceRepository.findAll();
        if(naceDataList.isEmpty()){
            throw new NaceNotFoundException("No NAC data found, upload NACE csv file to populate table!");
        }
        return naceRepository.findAll();
    }

    public void deleteNaceData(Long orderId) {
        naceRepository.deleteById(orderId);
    }
}
