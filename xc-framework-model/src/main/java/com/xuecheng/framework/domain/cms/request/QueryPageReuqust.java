package com.xuecheng.framework.domain.cms.request;


import com.xuecheng.framework.model.request.RequestData;
import lombok.Data;

@Data
public class QueryPageReuqust  extends RequestData {
      //站点id
    private String siteId; //页面ID
     private String pageId; //页面名称
     private String pageName; //别名
    private String pageAliase; //模版id
     private String templateId;
}
