package com.muli.picturemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muli.picturemanage.model.dto.spaceUser.SpaceUserAddRequest;
import com.muli.picturemanage.model.dto.spaceUser.SpaceUserQueryRequest;
import com.muli.picturemanage.model.entity.SpaceUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muli.picturemanage.model.vo.SpaceUserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 沐璃
* @description 针对表【space_user(空间用户关联)】的数据库操作Service
* @createDate 2025-10-20 11:42:29
*/
public interface SpaceUserService extends IService<SpaceUser> {

    long addSpaceUser(SpaceUserAddRequest spaceUserAddRequest);

    void validSpaceUser(SpaceUser spaceUser, boolean add);

    QueryWrapper<SpaceUser> getQueryWrapper(SpaceUserQueryRequest spaceUserQueryRequest);

    SpaceUserVO getSpaceUserVO(SpaceUser spaceUser, HttpServletRequest request);

    List<SpaceUserVO> getSpaceUserVOList(List<SpaceUser> spaceUserList);
}
