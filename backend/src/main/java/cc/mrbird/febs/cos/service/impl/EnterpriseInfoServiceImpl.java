package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.InterviewInfoMapper;
import cc.mrbird.febs.cos.dao.PostInfoMapper;
import cc.mrbird.febs.cos.entity.*;
import cc.mrbird.febs.cos.dao.EnterpriseInfoMapper;
import cc.mrbird.febs.cos.service.IEnterpriseInfoService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 企业信息管理 实现层
 *
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnterpriseInfoServiceImpl extends ServiceImpl<EnterpriseInfoMapper, EnterpriseInfo> implements IEnterpriseInfoService {

    private final InterviewInfoMapper interviewInfoMapper;

    private final PostInfoMapper postInfoMapper;

    /**
     * 分页获取企业信息信息
     *
     * @param page           分页对象
     * @param enterpriseInfo 企业信息信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectEnterprisePage(Page<EnterpriseInfo> page, EnterpriseInfo enterpriseInfo) {
        return baseMapper.selectEnterprisePage(page, enterpriseInfo);
    }

    /**
     * 根据企业获取面试信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> selectInterViewByEnterprise(Integer userId) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("post", Collections.emptyList());
                put("pluralism", Collections.emptyList());
            }
        };
        if (userId == null) {
            return result;
        }
        // 获取企业信息
        EnterpriseInfo enterpriseInfo = this.getOne(Wrappers.<EnterpriseInfo>lambdaQuery().eq(EnterpriseInfo::getUserId, userId));
        if (enterpriseInfo == null) {
            return result;
        }
        // 获取面试信息
        List<InterviewInfo> interviewList = interviewInfoMapper.selectList(Wrappers.<InterviewInfo>lambdaQuery().eq(InterviewInfo::getEnterpriseId, enterpriseInfo.getId()));
        if (CollectionUtil.isEmpty(interviewList)) {
            return result;
        }
        // 根据类型转MAP
        Map<Integer, List<InterviewInfo>> integerMap = interviewList.stream().collect(Collectors.groupingBy(InterviewInfo::getType));
        for (Integer interType : integerMap.keySet()) {
            if (interType == 2) {
                List<Integer> interPostIds = integerMap.get(interType).stream().map(InterviewInfo::getId).collect(Collectors.toList());
                result.put("post", interviewInfoMapper.selectInterViewPostByIds(interPostIds));
            }
        }
        return result;
    }

    /**
     * 根据岗位ID获取详情
     *
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> selectPostDetail(Integer postId) {
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        if (postId == null) {
            return null;
        }
        // 获取岗位信息
        PostInfo postInfo = postInfoMapper.selectById(postId);
        if (postInfo == null) {
            return null;
        }
        // 获取公司信息
        EnterpriseInfo enterpriseInfo = baseMapper.selectById(postInfo.getEnterpriseId());
        result.put("post", postInfo);
        result.put("enterprise", enterpriseInfo);
        return result;
    }

    /**
     * 根据企业ID获取面试信息
     *
     * @param userId 企业ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> selectInterViewByEnterPrise(Integer userId) {
        // 返回数据
        List<LinkedHashMap<String, Object>> result = new ArrayList<>();
        if (userId == null) {
            return result;
        }
        // 获取企业信息
        EnterpriseInfo enterpriseInfo = this.getOne(Wrappers.<EnterpriseInfo>lambdaQuery().eq(EnterpriseInfo::getUserId, userId));
        if (enterpriseInfo == null) {
            return result;
        }
        // 面试信息
        List<InterviewInfo> interviewInfoList = interviewInfoMapper.selectList(Wrappers.<InterviewInfo>lambdaQuery().eq(InterviewInfo::getEnterpriseId, enterpriseInfo.getId()).eq(InterviewInfo::getStatus, 1));
        Map<Integer, List<InterviewInfo>> integerMap = interviewInfoList.stream().collect(Collectors.groupingBy(InterviewInfo::getType));
        // 数据统计
        for (Integer type : integerMap.keySet()) {
            if (type == 2) {
                List<Integer> postIds = integerMap.get(type).stream().map(InterviewInfo::getId).collect(Collectors.toList());
                result.addAll(interviewInfoMapper.selectInterViewPostByIds(postIds));
            }
        }
        return result;
    }

    /**
     * 导入企业信息列表
     *
     * @param file 文件
     * @return 结果
     */
    @Override
    public String importExcel(MultipartFile file) throws Exception {
        ExcelReader excelReader = ExcelUtil.getReader(file.getInputStream(), 0);
        setExcelHeaderAlias(excelReader);
        List<EnterpriseInfo> reports = excelReader.read(1, 2, Integer.MAX_VALUE, EnterpriseInfo.class);
        StringBuilder error = new StringBuilder("");
        if (CollectionUtil.isEmpty(reports)) {
            error.append("导入数据不得为空。");
            return error.toString();
        }
        for (EnterpriseInfo expert : reports) {
            if (StrUtil.isEmpty(expert.getName())) {
                error.append("\n名称不能为空");
                return error.toString();
            }
            if (StrUtil.isEmpty(expert.getCreditCode())) {
                error.append("\n统一社会信用代码不能为空");
                return error.toString();
            }
            expert.setCode("EP-" + System.currentTimeMillis());
            expert.setCreateDate(DateUtil.formatDateTime(new Date()));
        }
        if (StrUtil.isEmpty(error.toString())) {
            this.saveBatch(reports);
            return null;
        }
        return error.toString();
    }

    /**
     * 设置HeaderAlias
     *
     * @param excelReader HeaderAlias
     */
    public void setExcelHeaderAlias(ExcelReader excelReader) {
        excelReader.addHeaderAlias("功能供应商名称", "name");
        excelReader.addHeaderAlias("单位简称或代号", "abbreviation");
        excelReader.addHeaderAlias("统一社会信用代码", "creditCode");
        excelReader.addHeaderAlias("单位性质", "nature");
        excelReader.addHeaderAlias("二级企业单位性质", "natureTwo");
        excelReader.addHeaderAlias("经营状态", "status");
        excelReader.addHeaderAlias("法定代表人", "corporateRepresentative");
        excelReader.addHeaderAlias("法定代表人身份证号", "corporateRepresentativeId");
        excelReader.addHeaderAlias("法定代表人电话", "corporateRepresentativePhone");
        excelReader.addHeaderAlias("注册资本", "registeredCapital");
        excelReader.addHeaderAlias("注册资金币种", "registeredCapitalCurrency");
        excelReader.addHeaderAlias("成立日期", "establishmentDate");
        excelReader.addHeaderAlias("营业期限始期", "businessBeginDate");
        excelReader.addHeaderAlias("营业期限止期", "businessEndDate");
        excelReader.addHeaderAlias("注册地址", "registeredAddress");
        excelReader.addHeaderAlias("经营范围", "businessScope");
        excelReader.addHeaderAlias("省", "province");
        excelReader.addHeaderAlias("市", "city");
        excelReader.addHeaderAlias("区", "district");
        excelReader.addHeaderAlias("英文企业名称", "enName");
        excelReader.addHeaderAlias("所属行业", "industry");
        excelReader.addHeaderAlias("单位简介", "unitDescription");
    }
}
