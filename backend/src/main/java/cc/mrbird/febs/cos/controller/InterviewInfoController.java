package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.EnterpriseInfo;
import cc.mrbird.febs.cos.entity.ExpertInfo;
import cc.mrbird.febs.cos.entity.InterviewInfo;
import cc.mrbird.febs.cos.entity.NotifyInfo;
import cc.mrbird.febs.cos.service.*;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 面试管理 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/interview-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterviewInfoController {

    private final IInterviewInfoService interviewInfoService;

    private final IExpertInfoService expertInfoService;

    private final IEnterpriseInfoService enterpriseInfoService;

    private final INotifyInfoService notifyInfoService;

    private final IPostInfoService postInfoService;

    /**
     * 分页获取所属面试管理信息
     *
     * @param page          分页对象
     * @param interviewInfo 所属面试管理信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<InterviewInfo> page, InterviewInfo interviewInfo) {
        return R.ok(interviewInfoService.selectInterviewPage(page, interviewInfo));
    }

    /**
     * 新增面试信息
     *
     * @param interviewInfo 面试信息
     * @return 结果
     */
    @PostMapping
    public R save(InterviewInfo interviewInfo) {
        ExpertInfo expertInfo = expertInfoService.getOne(Wrappers.<ExpertInfo>lambdaQuery().eq(ExpertInfo::getUserId, interviewInfo.getExpertId()));
        if (expertInfo != null) {
            interviewInfo.setExpertId(expertInfo.getId());
        }
        interviewInfo.setDelFlag(0);
        interviewInfo.setStatus(1);

        NotifyInfo notifyInfo = new NotifyInfo();
        notifyInfo.setDelFlag(0);
        EnterpriseInfo enterpriseInfo = enterpriseInfoService.getById(interviewInfo.getEnterpriseId());
        notifyInfo.setUserCode(enterpriseInfo.getCode());
        notifyInfo.setName(enterpriseInfo.getName());
        notifyInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        notifyInfo.setContent("你好，收到面试信息");
        notifyInfoService.save(notifyInfo);
        return R.ok(interviewInfoService.save(interviewInfo));
    }

    /**
     * 面试审核
     *
     * @param interviewInfo 面试信息
     * @return 结果
     */
    @PostMapping("/audit")
    public R audit(InterviewInfo interviewInfo) {
        if (interviewInfo.getStatus() == 3 || interviewInfo.getStatus() == 4) {
            NotifyInfo notifyInfo = new NotifyInfo();
            notifyInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
            notifyInfo.setDelFlag(0);
            ExpertInfo expertInfo = expertInfoService.getById(interviewInfo.getExpertId());
            notifyInfo.setUserCode(expertInfo.getCode());
            notifyInfo.setName(expertInfo.getName());
            String message = "";
            if (interviewInfo.getStatus() == 3) {
                message = "您的简历不符合我们的要求。";
            } else if (interviewInfo.getStatus() == 4) {
                message = "希望您参加面试，面试时间：" + interviewInfo.getCreateDate();
            }
            notifyInfo.setContent("你好，感谢您投递简历，" + message);
            notifyInfoService.save(notifyInfo);
        }
        return R.ok(interviewInfoService.updateById(interviewInfo));
    }

    /**
     * 面试详情
     *
     * @param id 面试ID
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R selectInterViewDetail(@PathVariable("id") Integer id) {
        InterviewInfo interviewInfo = interviewInfoService.getById(id);
        // 更新面试状态
        if (interviewInfo.getStatus() == 1) {
            interviewInfo.setStatus(2);
            interviewInfoService.updateById(interviewInfo);
        }
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("staff", expertInfoService.getById(interviewInfo.getExpertId()));
        if (interviewInfo.getType() == 2) {
            result.put("post", postInfoService.getById(interviewInfo.getBaseId()));
        }
        return R.ok(result);
    }

    /**
     * 查询所属面试管理信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(interviewInfoService.getById(id));
    }

    /**
     * 查询所属面试管理信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(interviewInfoService.list());
    }

    /**
     * 面试审核
     *
     * @param status 状态
     * @param id     主键
     * @return 结果
     */
    @GetMapping("/audit")
    public R audit(String status, Integer id) {
        return R.ok(interviewInfoService.update(Wrappers.<InterviewInfo>lambdaUpdate().set(InterviewInfo::getStatus, status).eq(InterviewInfo::getId, id)));
    }

    /**
     * 修改所属面试管理信息
     *
     * @param interviewInfo 所属面试管理信息
     * @return 结果
     */
    @PutMapping
    public R edit(InterviewInfo interviewInfo) {
        return R.ok(interviewInfoService.updateById(interviewInfo));
    }

    /**
     * 删除所属面试管理信息
     *
     * @param ids ids
     * @return 所属面试管理信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(interviewInfoService.removeByIds(ids));
    }
}
