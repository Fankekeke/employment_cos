package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ExpertInfo;
import cc.mrbird.febs.cos.entity.ReserveInfo;
import cc.mrbird.febs.cos.service.IExpertInfoService;
import cc.mrbird.febs.cos.service.IReserveInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 学生会场预约 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/reserve-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReserveInfoController {

    private final IReserveInfoService reserveInfoService;

    private final IExpertInfoService expertInfoService;

    /**
     * 分页获取学生会场预约信息
     *
     * @param page        分页对象
     * @param reserveInfo 学生会场预约信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ReserveInfo> page, ReserveInfo reserveInfo) {
        return R.ok(reserveInfoService.selectReservePage(page, reserveInfo));
    }

    /**
     * 查询学生会场预约信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(reserveInfoService.getById(id));
    }

    /**
     * 查询学生会场预约信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(reserveInfoService.list());
    }

    /**
     * 新增学生会场预约信息
     *
     * @param reserveInfo 学生会场预约信息
     * @return 结果
     */
    @PostMapping
    public R save(ReserveInfo reserveInfo) {
        ExpertInfo expertInfo = expertInfoService.getOne(Wrappers.<ExpertInfo>lambdaQuery().eq(ExpertInfo::getUserId, reserveInfo.getStudentId()));
        if (expertInfo != null) {
            reserveInfo.setStudentId(expertInfo.getId());
        }
        // 审核状态
        reserveInfo.setStatus("0");
        // 提交时间
        reserveInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(reserveInfoService.save(reserveInfo));
    }

    /**
     * 预约审核
     *
     * @param id     主键ID
     * @param status 状态
     * @return 结果
     */
    @GetMapping("/audit")
    public R audit(Integer id, String status) {
        return R.ok(reserveInfoService.update(Wrappers.<ReserveInfo>lambdaUpdate().set(ReserveInfo::getStatus, status).eq(ReserveInfo::getId, id)));
    }

    /**
     * 修改学生会场预约信息
     *
     * @param reserveInfo 学生会场预约信息
     * @return 结果
     */
    @PutMapping
    public R edit(ReserveInfo reserveInfo) {
        return R.ok(reserveInfoService.updateById(reserveInfo));
    }

    /**
     * 删除学生会场预约信息
     *
     * @param ids ids
     * @return 学生会场预约信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(reserveInfoService.removeByIds(ids));
    }
}
