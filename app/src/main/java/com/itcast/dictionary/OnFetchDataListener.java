package com.itcast.dictionary;

import com.itcast.dictionary.Models.APIResponse;

public interface OnFetchDataListener {
    void onFetchData(APIResponse apiResponse,String message);
    void onError(String message);
}
