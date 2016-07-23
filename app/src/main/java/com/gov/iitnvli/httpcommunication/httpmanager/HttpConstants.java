package com.gov.iitnvli.httpcommunication.httpmanager;

/**
 * Created by Murtuza
 */
public interface HttpConstants {

    String endPoint = "http://dev-nvli.iitb.ac.in/rest/v1/";

    String getDashBoardList = endPoint + "resource_type?";
    String getDetails = endPoint + "node/";
    String getSearchResult = endPoint + "search?";
}
