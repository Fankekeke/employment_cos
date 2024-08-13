package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 学生会场预约
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ReserveInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 会场ID
     */
    private Integer venueId;

    /**
     * 学生ID
     */
    private Integer studentId;

    /**
     * 申请时间
     */
    private String createDate;

    /**
     * 审核状态（0.未审核 1.已通过 2.未通过）
     */
    private String status;

    /**
     * 审核时间
     */
    private String auditDate;

    /**
     * 简历ID
     */
    private Integer resumeId;

    /**
     * 备注消息
     */
    private String remark;


}
