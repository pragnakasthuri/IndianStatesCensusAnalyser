package com.bridgelabz;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

/**
 * Creating IndianStatesCensusAnalyser class to display the welcome message
 * Ability to read IndianStatesCensusData.csv file
 */
public class IndianStatesCensusAnalyser {

    /**
     * Creating loadIndiaCensusData method to load Indian state census data from csv file
     * @param csvFilePath - path of the csv file
     * @return number of entries
     * @throws CensusAnalyserException
     */
    public static int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            CsvToBeanBuilder<IndianStateCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndianStateCensusCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndianStateCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndianStateCensusCSV> censusCSVIterator = csvToBean.iterator();;
            Iterable<IndianStateCensusCSV> csvIterable = () -> censusCSVIterator;
            int numberOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return numberOfEntries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                                              CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                                              CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    /**
     * Creating main method for modification and calling the methods
     * @param args - Default java params
     * @throws CensusAnalyserException
     */
    public static void main(String[] args) throws CensusAnalyserException {
        System.out.println("Welcome to Indian States Census Analyser");
        System.out.println("Number Of Entries: " +loadIndiaCensusData("/Users/nareshadla/Desktop/Pragna/Projects/IndianStatesCensusAnalyser/IndiaStateCensusData.csv"));
    }
}
