package com.muli.picturemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muli.picturemanage.model.dto.picture.*;
import com.muli.picturemanage.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muli.picturemanage.model.entity.User;
import com.muli.picturemanage.model.vo.PictureVO;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletRequest;

/**
* @author 沐璃
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2025-02-08 21:55:26
*/
public interface PictureService extends IService<Picture> {
    void validPicture(Picture picture);

    /**
     * 上传图片
     *
     * @param inputSource
     * @param pictureUploadRequest
     * @param loginUser
     * @return
     */
    PictureVO uploadPicture(Object inputSource,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);

    /**
     * 将用户传入的参数改造成查询的QueryWrapper
     * @param pictureQueryRequest
     * @return
     */
    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);


    /**
     * 获取单条脱敏后的图片对象
     * @param picture
     * @param request
     * @return
     */
    PictureVO getPictureVO(Picture picture, HttpServletRequest request);

    /**
     * 获取分页包装脱敏后的图片对象
     * @param picturePage
     * @param request
     * @return
     */
    Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);

    /**
     * 图片审核
     *
     * @param pictureReviewRequest
     * @param loginUser
     */
    void doPictureReview(PictureReviewRequest pictureReviewRequest, User loginUser);

    /**
     * 填充审核状态
     * @param picture
     * @param loginUser
     */
    void fillReviewParams(Picture picture, User loginUser);

    /**
     * 批量抓取和创建图片
     *
     * @param pictureUploadByBatchRequest
     * @param loginUser
     * @return 成功创建的图片数
     */
    Integer uploadPictureByBatch(
            PictureUploadByBatchRequest pictureUploadByBatchRequest,
            User loginUser
    );

    Page<PictureVO> listPictureVOByPageLocalCache(PictureQueryRequest pictureQueryRequest, HttpServletRequest request);

    Page<PictureVO> listPictureVOByPageRedis(PictureQueryRequest pictureQueryRequest, HttpServletRequest request);

    Page<PictureVO> listPictureVOByPageLocalCacheAndRedis(PictureQueryRequest pictureQueryRequest, HttpServletRequest request);

    @Async
    void clearPictureFile(Picture oldPicture);

    void checkPictureAuth(User loginUser, Picture picture);

    void deletePicture(long pictureId, User loginUser);

    void editPicture(PictureEditRequest pictureEditRequest, User loginUser);
}
