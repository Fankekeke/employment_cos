package cc.mrbird.febs.cos.entity;

import java.time.LocalDate;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 面试管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InterviewInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 员工ID
     */
    private Integer expertId;

    /**
     * 面试ID
     */
    private Integer baseId;

    /**
     * 面试类型（1.兼职 2.岗位）
     */
    private Integer type;

    /**
     * 面试时间
     */
    private String createDate;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    /**
     * 删除标识
     */
    private Integer delFlag;

    /**
     * 状态（1.已投递 2.已查看 3.不符合 4.邀约面试 5.面试结束）
     */
    private Integer status;

    @TableField(exist = false)
    private String enterpriseName;

    @TableField(exist = false)
    private String abbreviation;

    @TableField(exist = false)
    private String expertName;
}
