package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ExpertInfo;
import cc.mrbird.febs.cos.entity.ResumeInfo;
import cc.mrbird.febs.cos.service.IExpertInfoService;
import cc.mrbird.febs.cos.service.IResumeInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 简历管理 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/resume-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResumeInfoController {

    private final IResumeInfoService resumeInfoService;

    private final IExpertInfoService expertInfoService;

    /**
     * 分页获取简历管理信息
     *
     * @param page       分页对象
     * @param resumeInfo 简历管理信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ResumeInfo> page, ResumeInfo resumeInfo) {
        return R.ok(resumeInfoService.selectResumePage(page, resumeInfo));
    }

    /**
     * 查询简历管理信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(resumeInfoService.getById(id));
    }

    /**
     * 查询简历管理信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(resumeInfoService.list());
    }

    /**
     * 新增简历管理信息
     *
     * @param resumeInfo 简历管理信息
     * @return 结果
     */
    @PostMapping
    public R save(ResumeInfo resumeInfo) {
        resumeInfo.setCode("RES-" + System.currentTimeMillis());
        ExpertInfo expertInfo = expertInfoService.getOne(Wrappers.<ExpertInfo>lambdaQuery().eq(ExpertInfo::getUserId, resumeInfo.getStudentId()));
        if (expertInfo != null) {
            resumeInfo.setStudentId(expertInfo.getId());
        }
        if ("1".equals(resumeInfo.getDefaultFlag())) {
            resumeInfoService.update(Wrappers.<ResumeInfo>lambdaUpdate().set(ResumeInfo::getDefaultFlag, "0")
                    .eq(ResumeInfo::getStudentId, resumeInfo.getStudentId()));
        }
        resumeInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(resumeInfoService.save(resumeInfo));
    }

    /**
     * 修改简历管理信息
     *
     * @param resumeInfo 简历管理信息
     * @return 结果
     */
    @PutMapping
    public R edit(ResumeInfo resumeInfo) {
        ResumeInfo resumeInfo1 = resumeInfoService.getById(resumeInfo.getId());
        if ("1".equals(resumeInfo.getDefaultFlag())) {
            resumeInfoService.update(Wrappers.<ResumeInfo>lambdaUpdate().set(ResumeInfo::getDefaultFlag, "0")
                    .eq(ResumeInfo::getStudentId, resumeInfo1.getStudentId()));
        }
        return R.ok(resumeInfoService.updateById(resumeInfo));
    }

    /**
     * 删除简历管理信息
     *
     * @param ids ids
     * @return 简历管理信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(resumeInfoService.removeByIds(ids));
    }
}
