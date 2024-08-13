package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ChatInfo;
import cc.mrbird.febs.cos.service.IChatInfoService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 沟通信息 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/chat-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatInfoController {

    private final IChatInfoService chatInfoService;

    /**
     * 分页获取沟通信息
     *
     * @param page     分页对象
     * @param chatInfo 沟通信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ChatInfo> page, ChatInfo chatInfo) {
        return R.ok();
    }

    /**
     * 查询沟通信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(chatInfoService.getById(id));
    }

    /**
     * 查询沟通信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(chatInfoService.list());
    }

    /**
     * 新增沟通信息
     *
     * @param chatInfo 沟通信息
     * @return 结果
     */
    @PostMapping
    public R save(ChatInfo chatInfo) {
        return R.ok(chatInfoService.save(chatInfo));
    }

    /**
     * 修改沟通信息
     *
     * @param chatInfo 沟通信息
     * @return 结果
     */
    @PutMapping
    public R edit(ChatInfo chatInfo) {
        return R.ok(chatInfoService.updateById(chatInfo));
    }

    /**
     * 删除沟通信息
     *
     * @param ids ids
     * @return 沟通信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(chatInfoService.removeByIds(ids));
    }
}
