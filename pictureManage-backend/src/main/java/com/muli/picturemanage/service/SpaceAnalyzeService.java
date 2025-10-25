package com.muli.picturemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muli.picturemanage.model.dto.space.SpaceAddRequest;
import com.muli.picturemanage.model.dto.space.SpaceQueryRequest;
import com.muli.picturemanage.model.dto.space.analyze.*;
import com.muli.picturemanage.model.entity.Space;
import com.muli.picturemanage.model.entity.User;
import com.muli.picturemanage.model.vo.SpaceVO;
import com.muli.picturemanage.model.vo.space.analyze.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 沐璃
* @description 针对表【space(空间)】的数据库操作Service
* @createDate 2025-02-15 15:22:05
*/
public interface SpaceAnalyzeService extends IService<Space> {

    SpaceUsageAnalyzeResponse getSpaceUsageAnalyze(SpaceUsageAnalyzeRequest spaceUsageAnalyzeRequest, User loginUser);

    List<SpaceCategoryAnalyzeResponse> getSpaceCategoryAnalyze(SpaceCategoryAnalyzeRequest spaceCategoryAnalyzeRequest, User loginUser);

    List<SpaceTagAnalyzeResponse> getSpaceTagAnalyze(SpaceTagAnalyzeRequest spaceTagAnalyzeRequest, User loginUser);

    List<SpaceSizeAnalyzeResponse> getSpaceSizeAnalyze(SpaceSizeAnalyzeRequest spaceSizeAnalyzeRequest, User loginUser);

    List<SpaceUserAnalyzeResponse> getSpaceUserAnalyze(SpaceUserAnalyzeRequest spaceUserAnalyzeRequest, User loginUser);

    List<Space> getSpaceRankAnalyze(SpaceRankAnalyzeRequest spaceRankAnalyzeRequest, User loginUser);
}
