package com.nghia.tut.mss.service;

import com.nghia.tut.mss.domain.CartDetail;
import com.nghia.tut.mss.utils.BaseService;

public interface ComposeService extends BaseService {
    CartDetail getCartDetail(String  authKey, String userCode, String productCode);
}
