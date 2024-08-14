package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 沟通信息
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学生编号
     */
    private String expertCode;

    /**
     * 企业编号
     */
    private String enterpriseCode;

    /**
     * 发送类型（1.学生发企业收 2企业发学生收）
     */
    private Integer type;

    /**
     * 内容
     */
    private String content;

    /**
     * 发送时间
     */
    private String createDate;

    @TableField(exist = false)
    private String enterpriseName;

    @TableField(exist = false)
    private String abbreviation;

    @TableField(exist = false)
    private String expertName;
}
