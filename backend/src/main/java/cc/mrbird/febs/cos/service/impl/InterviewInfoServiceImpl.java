package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.BulletinInfo;
import cc.mrbird.febs.cos.entity.EnterpriseInfo;
import cc.mrbird.febs.cos.entity.InterviewInfo;
import cc.mrbird.febs.cos.dao.InterviewInfoMapper;
import cc.mrbird.febs.cos.entity.ReserveInfo;
import cc.mrbird.febs.cos.service.IBulletinInfoService;
import cc.mrbird.febs.cos.service.IEnterpriseInfoService;
import cc.mrbird.febs.cos.service.IInterviewInfoService;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 面试管理 实现层
 *
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterviewInfoServiceImpl extends ServiceImpl<InterviewInfoMapper, InterviewInfo> implements IInterviewInfoService {

    private final IEnterpriseInfoService enterpriseInfoService;

    private final IBulletinInfoService bulletinInfoService;

    /**
     * 分页获取所属面试管理信息
     *
     * @param page          分页对象
     * @param interviewInfo 所属面试管理信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectInterviewPage(Page<InterviewInfo> page, InterviewInfo interviewInfo) {
        return baseMapper.selectInterviewPage(page, interviewInfo);
    }

    /**
     * 获取主页统计数据
     *
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> homeData(Integer userId) {
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("orderNum", 0);
                put("orderPrice", 0);
                put("staffNum", 0);
                put("memberNum", 0);
            }
        };

        if (userId != null) {
            // 获取企业信息
            EnterpriseInfo enterpriseInfo = enterpriseInfoService.getOne(Wrappers.<EnterpriseInfo>lambdaQuery().eq(EnterpriseInfo::getUserId, userId));
            if (enterpriseInfo != null) {
                userId = enterpriseInfo.getId();
            }
        }

        // 本月预约数量
        List<ReserveInfo> orderMonthList = baseMapper.selectOrderByMonth(userId);
        result.put("monthOrderNum", CollectionUtil.isEmpty(orderMonthList) ? 0 : orderMonthList.size());
        // 获取本月面试投递
        List<InterviewInfo> interviewMonthList = baseMapper.selectInterviewByMonth(userId);
        result.put("monthOrderTotal", CollectionUtil.isEmpty(interviewMonthList) ? 0 : interviewMonthList.size());

        // 本年预约数量
        List<ReserveInfo> orderYearList = baseMapper.selectOrderByYear(userId);
        result.put("yearOrderNum", CollectionUtil.isEmpty(orderYearList) ? 0 : orderYearList.size());
        // 本年面试投递
        List<InterviewInfo> interviewYearList = baseMapper.selectInterviewByYear(userId);
        result.put("yearOrderTotal", CollectionUtil.isEmpty(interviewYearList) ? 0 : interviewYearList.size());

        // 近十天预约统计
        result.put("orderNumDayList", baseMapper.selectOrderNumWithinDays(userId));
        // 近十天面试统计
        result.put("priceDayList", baseMapper.selectOrderPriceWithinDays(userId));

        // 公告信息
        result.put("bulletinInfoList", bulletinInfoService.list(Wrappers.<BulletinInfo>lambdaQuery().eq(BulletinInfo::getRackUp, 1)));

        return result;
    }
}
