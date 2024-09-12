package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.*;
import cc.mrbird.febs.cos.entity.*;
import cc.mrbird.febs.cos.service.IReserveInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 学生会场预约 实现层
 *
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReserveInfoServiceImpl extends ServiceImpl<ReserveInfoMapper, ReserveInfo> implements IReserveInfoService {

    private final ExpertInfoMapper expertInfoMapper;

    private final VenueInfoMapper venueInfoMapper;

    private final EnterpriseInfoMapper enterpriseInfoMapper;

    private final ResumeInfoMapper resumeInfoMapper;

    /**
     * 分页获取学生会场预约信息
     *
     * @param page        分页对象
     * @param reserveInfo 学生会场预约信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectReservePage(Page<ReserveInfo> page, ReserveInfo reserveInfo) {
        return baseMapper.selectReservePage(page, reserveInfo);
    }

    /**
     * 查询学生会场预约信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> queryReserveDetail(Integer id) {
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("reserve", null);
                put("expert", null);
                put("enterprise", null);
                put("venue", null);
                put("resume", null);
            }
        };
        // 预约信息
        ReserveInfo reserveInfo = this.getById(id);
        if (reserveInfo == null) {
            return result;
        }

        ExpertInfo expertInfo = expertInfoMapper.selectById(reserveInfo.getStudentId());
        VenueInfo venueInfo = venueInfoMapper.selectById(reserveInfo.getVenueId());
        EnterpriseInfo enterpriseInfo = enterpriseInfoMapper.selectById(venueInfo.getEnterpriseId());
        ResumeInfo resumeInfo = resumeInfoMapper.selectById(reserveInfo.getResumeId());

        result.put("reserve", reserveInfo);
        result.put("expert", expertInfo);
        result.put("enterprise", enterpriseInfo);
        result.put("venue", venueInfo);
        result.put("resume", resumeInfo);
        return result;
    }
}
