package com.ml.ad.mysql.listener;

import com.ml.ad.mysql.dto.BinlogRowData;

/**
 * @author Mr.ml
 * @date 2021/11/27
 */
public interface IListener {

    void register();

    void onEvent(BinlogRowData eventData);
}
