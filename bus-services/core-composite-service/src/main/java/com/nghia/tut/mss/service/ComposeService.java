package com.nghia.tut.mss.service;


import com.nghia.tut.mss.domain.CartDetail;

public interface ComposeService {
    CartDetail getCartDetail(String  authKey, String userCode, String productCode);
}
