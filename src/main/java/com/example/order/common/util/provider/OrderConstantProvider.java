package com.example.order.common.util.provider;

/**
 * 订单相关常量
 */
public class OrderConstantProvider {

    // 订单是否有效
    public static Integer ORDER_VALID = 1;  // 有效
    public static Integer ORDER_INVALID = 0;    // 无效

    // todo 以后将常量的更改为配置文件形式或者数据库配置

    public static Long DEFAULT_ORDER_TIMEOUT = 60 * 60L;    // 订单超过一小时即判定为订单超时

    public static Long DEFAULT_PAY_TIMEOUT = 30 * 60L;  // 超过 30 分钟未支付即判定为支付超时

    public static Long DEFAULT_RETURN_TIMEOUT = 2 * 60 * 60L;   // 超过两小时即判定为取消订单超时

    public static Long DEFAULT_ORDER_RECV_TIMEOUT = 15 * 60L;   // 订单接收超时

    /**
     * 订单状态常量
     */
    public enum OrderStatus {

        WAITING_PAY(1 << 0, "WAITING_PAY", "等待付款"),  // 当在暂未收到您款项时，订单会显示“等待到款”，建议您在订单保留期限内及时付款。
        WAITING_GOODS(1 << 1, "WAITING_GOODS", "等待预售商品到货"),   // 若您订购的商品为预售商品，商品到货前订单会显示“等待预售商品到货”。
        PICKING(1 << 2, "PICKING", "正在配货"),  // 此状态说明您的订单正在库房配货。
        PICKED(1 << 3, "PICKED", "已配货"),   // 此状态说明您的订单已完成配货，正在等待发货。
        SHIPPED(1 << 4, "SHIPPED", "已发货"),   // 订单已从库房发出，正在配送途中，订单会显示“已发货”。
        ARRIVED(1 << 5, "ARRIVED", "已送达"),   // 若您已收到商品并在“我的订单”中进行了“收货反馈”，订单会显示“已送达”。
        ORDER_SUCCESS(1 << 6, "ORDER_SUCCESS", "交易成功"),  // 若您的订单状态为“已送达”，且此状态后的15天内未发生退货，系统将默认变为“交易成功”。
        ORDER_FAIL(1 << 7, "ORDER_FAIL", "交易未成功"), // 若订单未送达、送达后未签收或签收后办理了退货，订单状态都会默认显示“交易未成功”。
        ORDER_CANCEL(1 << 8, "ORDER_CANCEL", "取消");    // 若您订单中的商品缺货，或您的订单超出了订单保留期限，或您将订单进行了取消操作，订单都将显示“取消”状态。

        // 状态常量
        int status;
        // 状态短语
        String msg;
        // 状态文字说明
        String explain;

        OrderStatus(int status, String msg, String explain) {
            this.status = status;
            this.msg = msg;
            this.explain = explain;
        }

        public int getStatus() {
            return status;
        }

        public String getMsg() {
            return msg;
        }

        public String getExplain() {
            return explain;
        }

        /**
         * 判断订单是否完成 448 表示 (1 << 8 | 1 << 7 | 1 << 6)
         *
         * @param status
         * @return
         */
        public Boolean isFinish(Integer status) {
            return (status & 448) > 0;
        }
    }

    /**
     * 支付状态
     */
    public enum PayStatus {

        // 等待支付
        WAIT_PAYMENT(0, "WAIT_PAYMENT"),
        // 支付成功
        SUCCESS_PAYMENT(1 << 1, "SUCCESS_PAYMENT"),
        // 支付失败
        FAILED_PAYMENT(1 << 2, "FAILED_PAYMENT"),
        // 超时支付
        TIMEOUT_PAYMENT(1 << 3, "TIMEOUT_PAYMENT");

        private int code;
        private String codeStr;

        private PayStatus(int code, String codeStr) {
            this.code = code;
            this.codeStr = codeStr;
        }

        private PayStatus() {
            this.code = 0;
            this.codeStr = "WAIT_PAYMENT";
        }

        public int getCode() {
            return code;
        }

        public String getCodeStr() {
            return codeStr;
        }

        /**
         * 判断是否支付成功
         *
         * @param status
         * @return
         */
        public Boolean isFinish(Integer status) {
            // 1 << 1 是支付成功代表的值
            return (status == 1 << 1);
        }
    }

    /**
     * 支付方式
     */
    public enum PayStyle {
        // 在线支付
        ONLINE_PAY(1);

        private final int code;

        private PayStyle(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public enum WalletTradeType {
        // 支付
        VIRTUAL_TRADE_RECHARGE(0, "VIRTUAL_TRADE_RECHARGE"),
        // 提现
        VIRTUAL_TRADE_WITHDRAW(1, "VIRTUAL_TRADE_WITHDRAW"),

        TRADE_RECHARGE(1 << 1, "TRADE_RECHARGE"),
        TRADE_WITHDRAW(1 << 2, "TRADE_WITHDRAW"),
        TRADE_PAY(1 << 3, "TRADE_PAY");

        // 常量代码
        private final Integer code;
        // 常量说明
        private final String msg;

        WalletTradeType(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
