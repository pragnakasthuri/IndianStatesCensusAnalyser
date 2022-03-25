package com.bridgelabz;

/**
 * Creating the CensusAnalyserException class which extends the Exception class
 * and creating an enum which holds two types of custom exceptions
 */
public class CensusAnalyserException extends Exception{
    enum ExceptionType {
        CENSUS_FILE_PROBLEM,UNABLE_TO_PARSE
    }

    ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}

