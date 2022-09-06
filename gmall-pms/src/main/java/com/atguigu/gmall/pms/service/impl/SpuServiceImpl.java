package com.atguigu.gmall.pms.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;

import com.atguigu.gmall.pms.mapper.SpuMapper;
import com.atguigu.gmall.pms.entity.SpuEntity;
import com.atguigu.gmall.pms.service.SpuService;


@Service("spuService")
public class SpuServiceImpl extends ServiceImpl<SpuMapper, SpuEntity> implements SpuService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SpuEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SpuEntity>()
        );

        return new PageResultVo(page);
    }

    @Override
    public PageResultVo querySpuByPageAndCid(PageParamVo paramVo, Long cid) {
        QueryWrapper<SpuEntity> wrapper = new QueryWrapper<>();
        //判断cid是否为0，不为0则需要加入分类的条件
        if (cid != 0) {
            wrapper.eq("category_id", cid);
        }
        //查询关键字
        String key = paramVo.getKey();
//        if (key != null && key.trim().length() > 0) {
//
//        }
        //isNotEmpty不会去空格
        if (StringUtils.isNotBlank(key)) {
            wrapper.and(t -> t.eq("id", key).or().like("name", key));
        }

        IPage<SpuEntity> page = this.page(
                paramVo.getPage(),
                wrapper
        );

        return new PageResultVo(page);
    }

}