package com.example.order.common.util.provider;

public class ProductConstantProvider {

    /**
     * 商品状态
     */
    public enum ProductStatue {
        SHELVE(0),  // 下架
        ONSALE(1),  // 在售
        PRESELL(2); // 预售

        private int statue;

        private ProductStatue(int statue) {
            this.statue = statue;
        }

        public int getStatue() {
            return statue;
        }
    }
}
