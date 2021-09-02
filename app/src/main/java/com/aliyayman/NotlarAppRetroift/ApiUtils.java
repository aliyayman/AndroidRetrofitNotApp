package com.aliyayman.NotlarAppRetroift;

public class ApiUtils {

    public static final String BASE_URL="http://www.byrmkus.tk/";

    public static NotlarDaoInterface getNotlarDaoInterface(){
        return RetrofitClient.getClient(BASE_URL).create(NotlarDaoInterface.class);


    }
}
