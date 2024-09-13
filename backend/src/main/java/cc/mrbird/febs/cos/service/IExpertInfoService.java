package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ExpertInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 学生信息管理 service层
 *
 * @author FanK
 */
public interface IExpertInfoService extends IService<ExpertInfo> {

    /**
     * 分页获取学生信息信息
     *
     * @param page       分页对象
     * @param expertInfo 学生信息信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectExpertPage(Page<ExpertInfo> page, ExpertInfo expertInfo);

    /**
     * 根据求职者获取面试信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    LinkedHashMap<String, Object> selectInterByExpert(Integer userId);

    /**
     * 根据求职者获取面试信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectInterViewByExpert(Integer userId);

    /**
     * 根据企业编号获取推荐专家
     *
     * @param enterpriseCode 企业编号
     * @return 结果
     */
    List<ExpertInfo> selectExpertRecommend(String enterpriseCode);

    /**
     * 导入专家信息列表
     *
     * @param file 文件
     * @return 结果
     */
    String importExcel(MultipartFile file) throws Exception;
}
