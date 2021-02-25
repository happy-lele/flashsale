
package com.le.flashsale.common;

/**
 * Date 2020/11/16 7:54 下午
 * Author le
 */
public class Constant {

    /**
     * 返回结果
     */
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";

    /**
     * 请求相关
     */
    public static final String HTTP_PREFIX = "flashsale"; // 请求前缀

    /**
     * Redis 相关
     */
    public static final String REDIS_PREFIX_ADD_ORDER = "FLASH_SALE_ADD_ORDER_"; // 用户针对某个商品是否执行过下单操作
    public static final String REDIS_PREFIX_ACTIVITY_STOCK_NUMS = "FLASH_SALE_ACTIVITY_STOCK_NUMS_"; // 活动涉及的库存量
    public static final String REDIS_PREFIX_ACTIVITY_STOCK_STARTTIME = "FLASH_SALE_ACTIVITY_STOCK_STARTTIME_"; // 活动涉及的开始时间
    public static final String REDIS_PREFIX_STOCK_ = "FLASH_SALE_STOCK_"; // 库存锁前缀

    /**
     * 提示语句相关
     */
    public static final String MESSAGE_STOCK_BASE_PARAM_NULL = "接口入参不能为空";

    public static final String MESSAGE_ORDER_SUBMITED = "请稍等，您的订单已在排队中，中签会收到邮件通知，请点击邮件中的链接查看具体详情";
    public static final String MESSAGE_ORDER_FAILED_1 = "您已提交过订单，无须再次提交";
    public static final String MESSAGE_ORDER_FAILED_2 = "实在抱歉，商品已售空，请15分钟之后再次尝试，抢购未支付的订单";
    public static final String MESSAGE_ORDER_FAILED_3 = "该活动不存在，请稍后再试";

    public static final String MESSAGE_STOCK_FAILED_STOCKID_NULL = "库存id不能为空";
    public static final String MESSAGE_STOCK_FAILED_USERID_NULL = "用户id不能为空";
    public static final String MESSAGE_STOCK_FAILED_NUMS_NULL = "扣减库存数量不能为空";
    public static final String MESSAGE_STOCK_FAILED_NUMS_ERROR = "扣减库存数量只能为正整数";
    public static final String MESSAGE_STOCK_FAILED_STOCK_NULL = "库存商品不存在";
    public static final String MESSAGE_STOCK_FAILED_NUMS_NOT_ENOUGH = "需要扣减的数量大于库存商品数量";

    public static final String MESSAGE_USER_FAILED_STOCKID_NULL = "用户id不能为空";
    public static final String MESSAGE_USER_FAILED_USER_NULL = "不保存空的用户数据";
    /**
     * 服务资源
     */
    public static final Long DATA_CENTER_ID = 3L;
    public static final Long MACHINE_ID = 10L;

    /**
     * 邮件服务
     */
    public static final String EMAIL_SUBJECT = "恭喜您！商品抢购成功！";
    public static final String EMAIL_CONTANT = "您抢购的商品详情为：http://127.0.0.1:8080/flashsale/order/query/%s "
            + "，请在15分钟内点击该链接进行支付。如未支付，则视为放弃购买。";

}
