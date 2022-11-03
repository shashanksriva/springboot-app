package com.dbank.nace.helper;

import com.dbank.nace.entity.NaceData;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CsvHelper {
    public static String TYPE = "text/csv";
    static String[] HEADER = { "Order",	"Level", "Code",	"Parent",	"Description",	"This item includes",
            "This item also includes",	"Rulings",	"This item excludes","Reference to ISIC Rev. 4"
    };

    public static boolean isCsvFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<NaceData> csvToDbOpenCsv(InputStream is) {
        List<NaceData> naceDataList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        CSVReader csvReader =  new CSVReader(reader);
        log.info("File Content in CSV ::>>>>");
        /*List<NaceData> naceDataList = new CsvToBeanBuilder(reader).withType(NaceData.class)
                .build()
                .parse();*/
        try {
            List<String[]> rowsList = csvReader.readAll();
            List<String[]> rows = rowsList.subList(1, rowsList.size());
            for (String[] row : rows) {
                log.info(row[0] + "," + row[6] + "," + row[7] + "," + row[8]);
                NaceData data = new NaceData(
                        Long.parseLong(row[0]),
                        Integer.parseInt(row[1]),
                        row[2],
                        row[3],
                        row[4],
                        row[5],
                        row[6],
                        row[7],
                        row[8],
                        row[9]
                );
                naceDataList.add(data);
            }
        } catch(IOException ie) {

        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        return naceDataList;
    }

    public static List<NaceData> csvToDatabaseFormat(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_16BE));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.EXCEL.withFirstRecordAsHeader().withHeader(HEADER));)
        {
            List<NaceData> naceDataList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            log.info("... Check if record . present  >>> " + csvRecords);
            for (CSVRecord csvRecord : csvRecords) {
                log.info("Order ::::"+ csvRecord.get("Order"));
                NaceData naceData = new NaceData(
                        Long.parseLong(csvRecord.get("Order")),
                        Integer.parseInt(csvRecord.get("Level")),
                        csvRecord.get("Code"),
                        csvRecord.get("Parent"),
                        csvRecord.get("Description"),
                        csvRecord.get("This item includes"),
                        csvRecord.get("This item also includes"),
                        csvRecord.get("Rulings"),
                        csvRecord.get("This item excludes"),
                        csvRecord.get("Reference to ISIC Rev. 4")
                        );
                naceDataList.add(naceData);
            }
            log.info("NACE data list size :: " + naceDataList.size());
            return naceDataList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    /*public static List<NaceData>  csvToDatabaseFormat(InputStream is) throws IOException, CsvException {
        String fileName = "nace_data.csv";

        List<String[]> r;
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            r = reader.readAll();
        }

        int listIndex = 0;
        for (String[] arrays : r) {
            System.out.println("\nString[" + listIndex++ + "] : " + Arrays.toString(arrays));

            int index = 0;
            for (String array : arrays) {
                System.out.println(index++ + " : " + array);
            }
        }
        return new ArrayList<>();
    }*/

}
