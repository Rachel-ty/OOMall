package cn.edu.xmu.oomall.wechatpay.model.po;

import java.time.LocalDateTime;

public class WeChatPayTransactionPo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oomall_wechatpay_transaction.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oomall_wechatpay_transaction.out_trade_no
     *
     * @mbg.generated
     */
    private String outTradeNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oomall_wechatpay_transaction.trade_state
     *
     * @mbg.generated
     */
    private String tradeState;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oomall_wechatpay_transaction.total
     *
     * @mbg.generated
     */
    private Integer total;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oomall_wechatpay_transaction.payer_total
     *
     * @mbg.generated
     */
    private Integer payerTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oomall_wechatpay_transaction.success_time
     *
     * @mbg.generated
     */
    private LocalDateTime successTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oomall_wechatpay_transaction.id
     *
     * @return the value of oomall_wechatpay_transaction.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oomall_wechatpay_transaction.id
     *
     * @param id the value for oomall_wechatpay_transaction.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oomall_wechatpay_transaction.out_trade_no
     *
     * @return the value of oomall_wechatpay_transaction.out_trade_no
     *
     * @mbg.generated
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oomall_wechatpay_transaction.out_trade_no
     *
     * @param outTradeNo the value for oomall_wechatpay_transaction.out_trade_no
     *
     * @mbg.generated
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oomall_wechatpay_transaction.trade_state
     *
     * @return the value of oomall_wechatpay_transaction.trade_state
     *
     * @mbg.generated
     */
    public String getTradeState() {
        return tradeState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oomall_wechatpay_transaction.trade_state
     *
     * @param tradeState the value for oomall_wechatpay_transaction.trade_state
     *
     * @mbg.generated
     */
    public void setTradeState(String tradeState) {
        this.tradeState = tradeState == null ? null : tradeState.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oomall_wechatpay_transaction.total
     *
     * @return the value of oomall_wechatpay_transaction.total
     *
     * @mbg.generated
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oomall_wechatpay_transaction.total
     *
     * @param total the value for oomall_wechatpay_transaction.total
     *
     * @mbg.generated
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oomall_wechatpay_transaction.payer_total
     *
     * @return the value of oomall_wechatpay_transaction.payer_total
     *
     * @mbg.generated
     */
    public Integer getPayerTotal() {
        return payerTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oomall_wechatpay_transaction.payer_total
     *
     * @param payerTotal the value for oomall_wechatpay_transaction.payer_total
     *
     * @mbg.generated
     */
    public void setPayerTotal(Integer payerTotal) {
        this.payerTotal = payerTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oomall_wechatpay_transaction.success_time
     *
     * @return the value of oomall_wechatpay_transaction.success_time
     *
     * @mbg.generated
     */
    public LocalDateTime getSuccessTime() {
        return successTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oomall_wechatpay_transaction.success_time
     *
     * @param successTime the value for oomall_wechatpay_transaction.success_time
     *
     * @mbg.generated
     */
    public void setSuccessTime(LocalDateTime successTime) {
        this.successTime = successTime;
    }
}