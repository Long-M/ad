package com.ml.ad.mysql.dto;

import com.ml.ad.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.ml
 * @date 2021/11/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MySQLRowData {

    private String tableName;
    private String level;
    private OpType opType;
    private List<Map<String, String>> fieldValueMap = new ArrayList<>();

}
