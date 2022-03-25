package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IndianCensusAnalyserTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";


    @Test
    public void givenIndianCensusData_WithCorrectFile_ReturnsCorrectRecords() {
        try {
            IndianStatesCensusAnalyser censusAnalyser = new IndianStatesCensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assertions.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) { }
    }

    @Test
    public void givenIndianCensusData_WithWrongFile_ShouldThrowException() {
        try {
            IndianStatesCensusAnalyser censusAnalyser = new IndianStatesCensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assertions.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
}
