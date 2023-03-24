namespace java thrift

/**
* 厂商 (开放平台,应用商)
**/
struct SupplierProfileData {
    1:i64 supplierId; // 厂商id
    2:string inviteCode; // 邀请码(唯一标识)
    3:string enterpriseName; // 厂商企业名
    4:i32 supplierCommissionRate; // 厂商分佣比, 剩下的(10000-x)/10000就是群主的分佣比率
    5:i64 supplierSettleUserId; // 厂商结算用户id
}

/**
* 供货商 (供货商管理后台)
**/
struct MerchantProfileData {
    1:i64 merchantId; // 供货商id
    2:byte merchantStatus; // 状态
    3:string enterpriseName; // 供货商企业名
    4:byte supportType; // 客服系统类型 1H5 2原生
    5:list<i64> supportUidList; // 原生客服id集
    6:i64 h5SupportAppId; // h5客服系统appId
    7:string h5SupportAppSecret; // h5客服系统app密钥
    8:i64 merchantSettleUserId; // 供货商结算用户id
}

/**
* 群信息
**/
struct OkaymallGroupProfileData {
    1:i64 groupId; // 群id
    2:i64 ownerId; // 群主Id
    3:i64 supplierId; // 绑定的厂商id
    4:byte shopOpenStatus; // 群商店开关,默认 关-0 开-1 申请中-2
    5:i64 shopOpenTime; // 开店时间
    6:GroupAddressInfo groupAddress;
}

struct GroupAddressInfo {
    1:i64 ownerAddressId;
    2:string realName;
    3:string mobile;
    4:string postcode;
    5:string province;
    6:string city;
    7:string area;
    8:string detailedAddress;
}

/**
* 系统分类
**/
struct SystemCategory {
    1:i32 categoryId;
    2:string categoryName;
    3:string sqlCondition;
    4:list<SelfDefinedSecondCategory> sonCategories;
}
// 自定义二级类目
struct SelfDefinedSecondCategory {
    1:i32 categoryId;
    2:string categoryName;
    3:string goodsIdList;
}

/**
* 活动模块
**/
struct PromoActivityModuleData {
    1:i32 moduleId; // 模块id
    2:string title; // 模块标题
    3:string photoUrl; // 模块图片
    4:byte locationType; // 1横图 2左右小图 3商品列表广告
    5:string h5Url; // 不为空时跳转活动的H5页面
    6:i64 promoActivityId; // 活动id
}

/**
* 活动
**/
struct PromoActivityData {
    1:i64 promoActivityId; // 活动id
    2:string name; // 活动名
    3:string bannerUrl; // banner图
    4:string linkName; // 跳转名,例: 特产百科
    5:string linkH5Url; // 跳转的H5页面
    6:i64 beginningTime; // 开始时间
    7:i64 closingTime; // 结束时间
}