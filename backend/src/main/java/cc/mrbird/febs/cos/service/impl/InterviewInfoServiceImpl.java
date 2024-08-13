package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.InterviewInfo;
import cc.mrbird.febs.cos.dao.InterviewInfoMapper;
import cc.mrbird.febs.cos.service.IInterviewInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 面试管理 实现层
 *
 * @author FanK
 */
@Service
public class InterviewInfoServiceImpl extends ServiceImpl<InterviewInfoMapper, InterviewInfo> implements IInterviewInfoService {

}
