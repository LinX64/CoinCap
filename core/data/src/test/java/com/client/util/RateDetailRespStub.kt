package com.client.util

import com.client.data.model.RateDetailResp

fun rateDetailRespStub() = RateDetailResp(
    rate = rateStub(),
    timestamp = 1234567890
)
