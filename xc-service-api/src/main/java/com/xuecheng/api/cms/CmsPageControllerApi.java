package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageReuqust;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(value = "cms页面管理接口",description = "cms页面管理接口提供增删改查操作")
public interface CmsPageControllerApi {


    @ApiOperation(value = "查询页面接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int", paramType = "path"),

            @ApiImplicitParam(name = "size", value = "每页显示条数", dataType = "int", paramType = "path")
    } )
    public QueryResponseResult findPage(int page,int size,QueryPageReuqust queryPageRequest);

    @ApiOperation(value = "添加页面接口")
    public CmsPageResult addPage(CmsPage cmsPage);

    @ApiOperation(value = "修改页面接口")
    public CmsPageResult updatePage(String id,CmsPage cmsPage);

    @ApiOperation(value = "删除页面接口")
    public ResponseResult deletePage(int pageid);


    @ApiOperation(value = "查询页面详情")
    public CmsPage findByIdPage(String id,CmsPage cmsPage);

    @ApiOperation(value = "发布页面")
    public ResponseResult postPage(int pageid);

    @ApiOperation(value = "保存页面接口")
    public CmsPageResult savePage(CmsPage cmsPage);

    @ApiOperation(value = "一键发布页面接口")
    public ResponseResult postPageQuick(CmsPage cmsPage);







}
