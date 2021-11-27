package com.ml.ad.sender;

import com.ml.ad.mysql.dto.MySQLRowData;

/**
 * @author Mr.ml
 * @date 2021/11/27
 */
public interface ISender {

    /**
     * @param rowData
     */
    void send(MySQLRowData rowData);
}
