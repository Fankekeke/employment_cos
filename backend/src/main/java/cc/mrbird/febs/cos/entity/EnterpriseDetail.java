package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 企业详情
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EnterpriseDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 所属企业
     */
    private Integer enterpriseId;

    /**
     * 工作时间开始
     */
    private String workStartTime;

    /**
     * 工作时间结束
     */
    private String workEndTime;

    /**
     * 福利
     */
    private String welfare;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 公司介绍
     */
    private String content;

    /**
     * 公司logo
     */
    private String logo;


}
