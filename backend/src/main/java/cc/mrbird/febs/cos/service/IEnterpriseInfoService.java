package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.EnterpriseInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 企业信息管理 service层
 *
 * @author FanK
 */
public interface IEnterpriseInfoService extends IService<EnterpriseInfo> {

    /**
     * 分页获取企业信息信息
     *
     * @param page           分页对象
     * @param enterpriseInfo 企业信息信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectEnterprisePage(Page<EnterpriseInfo> page, EnterpriseInfo enterpriseInfo);

    /**
     * 根据企业获取面试信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    LinkedHashMap<String, Object> selectInterViewByEnterprise(Integer userId);

    /**
     * 根据岗位ID获取详情
     *
     * @param postId 岗位ID
     * @return 结果
     */
    LinkedHashMap<String, Object> selectPostDetail(Integer postId);

    /**
     * 根据企业ID获取面试信息
     *
     * @param userId 企业ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectInterViewByEnterPrise(Integer userId);

    /**
     * 导入企业信息列表
     *
     * @param file 文件
     * @return 结果
     */
    String importExcel(MultipartFile file) throws Exception;
}
