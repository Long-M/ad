package com.ml.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Mr.ml
 * @date 2021/11/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlanTable {

    private Long id;
    private Long userId;
    private Integer planStatus;
    private Date startDate;
    private Date endDate;

}
