package com.adda.controller.web;

import java.util.List;

public class ServiceResponse
{
    public static int SUCCESS = Integer.MAX_VALUE;
    public static int FAILURE = Integer.MIN_VALUE;

    private int result = SUCCESS;
    private String error = null;

    private List<Object> resultSet;

    public ServiceResponse()
    {

    }


    public ServiceResponse(int resultValue, String errorString)
    {
        setResult(resultValue);
        setError(errorString);
    }



    public void setResult(int value)
    {
        result = value;
    }

    public int getResult()
    {
        return(result);
    }


    public String getError()
    {
        return(error);
    }

    public void setError(String errorString)
    {
        error = (errorString != null) ? new String(errorString) : null;
    }

    public static ServiceResponse createSuccessResponse(String message)
    {
        ServiceResponse response = new ServiceResponse(ServiceResponse.SUCCESS,message);

        return(response);
    }

    public static ServiceResponse createFailureResponse(String error)
    {
        ServiceResponse response = new ServiceResponse(ServiceResponse.FAILURE,error);

        return(response);
    }



    public List<Object> getResultSet() {
        return resultSet;
    }


    public void setResultSet(List<Object> resultSet) {
        this.resultSet = resultSet;
    }





}
