package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.VenueInfo;
import cc.mrbird.febs.cos.service.IVenueInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会场信息 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/venue-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VenueInfoController {

    private final IVenueInfoService venueInfoService;

    /**
     * 分页获取会场信息信息
     *
     * @param page      分页对象
     * @param venueInfo 会场信息信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<VenueInfo> page, VenueInfo venueInfo) {
        return R.ok(venueInfoService.selectVenuePage(page, venueInfo));
    }

    /**
     * 查询会场信息信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(venueInfoService.getById(id));
    }

    /**
     * 查询会场信息信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(venueInfoService.list());
    }

    /**
     * 新增会场信息信息
     *
     * @param venueInfo 会场信息信息
     * @return 结果
     */
    @PostMapping
    public R save(VenueInfo venueInfo) {
        return R.ok(venueInfoService.save(venueInfo));
    }

    /**
     * 修改会场信息信息
     *
     * @param venueInfo 会场信息信息
     * @return 结果
     */
    @PutMapping
    public R edit(VenueInfo venueInfo) {
        return R.ok(venueInfoService.updateById(venueInfo));
    }

    /**
     * 删除会场信息信息
     *
     * @param ids ids
     * @return 会场信息信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(venueInfoService.removeByIds(ids));
    }
}
