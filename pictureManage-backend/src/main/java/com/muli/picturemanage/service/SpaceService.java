package com.muli.picturemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muli.picturemanage.model.dto.space.SpaceAddRequest;
import com.muli.picturemanage.model.dto.space.SpaceQueryRequest;
import com.muli.picturemanage.model.entity.Space;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muli.picturemanage.model.entity.User;
import com.muli.picturemanage.model.vo.SpaceVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author 沐璃
* @description 针对表【space(空间)】的数据库操作Service
* @createDate 2025-02-15 15:22:05
*/
public interface SpaceService extends IService<Space> {

    long addSpace(SpaceAddRequest spaceAddRequest, User loginUser);

    void validSpace(Space space, boolean add);

    void fillSpaceBySpaceLevel(Space space);

    SpaceVO getSpaceVO(Space space, HttpServletRequest request);

    Page<SpaceVO> getSpaceVOPage(Page<Space> spacePage, HttpServletRequest request);

    QueryWrapper<Space> getQueryWrapper(SpaceQueryRequest spaceQueryRequest);
}
