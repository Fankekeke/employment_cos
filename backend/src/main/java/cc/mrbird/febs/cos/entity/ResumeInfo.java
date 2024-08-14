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
 * 简历管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ResumeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 简历编号
     */
    private String code;

    /**
     * 简历名称
     */
    private String name;

    /**
     * 附件地址
     */
    private String fileUrl;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 所属学生
     */
    private Integer studentId;

    @TableField(exist = false)
    private String expertName;
}
