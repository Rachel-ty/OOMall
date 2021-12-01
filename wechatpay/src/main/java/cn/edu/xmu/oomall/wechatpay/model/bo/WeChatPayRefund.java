package cn.edu.xmu.oomall.wechatpay.model.bo;

import cn.edu.xmu.oomall.wechatpay.model.vo.WeChatPayRefundRetVo;
import cn.edu.xmu.oomall.wechatpay.model.vo.WeChatPayRefundVo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ziyi guo
 * @date 2021/11/30
 */
@Data
@NoArgsConstructor
public class WeChatPayRefund implements Serializable {

    private Long id;
    private String refundId;
    private String outRefundNo;
    private String transactionId;
    private String outTradeNo;
    private String channel;
    private String userReceivedAccount;
    private LocalDateTime createTime;
    private String status;
    private Integer total;
    private Integer refund;
    private Integer payerTotal;
    private Integer payerRefund;
    private Integer settlementRefund;
    private Integer settlementTotal;
    private Integer discountRefund;
    private String currency;
    private String reason;
    private String notifyUrl;

    public WeChatPayRefund(WeChatPayRefundVo weChatPayRefundVo){
        this.transactionId = weChatPayRefundVo.getTransactionId();
        this.outTradeNo = weChatPayRefundVo.getOutTradeNo();
        this.outRefundNo = weChatPayRefundVo.getOutRefundNo();
        this.reason = weChatPayRefundVo.getReason();
        this.notifyUrl = weChatPayRefundVo.getNotifyUrl();
        this.refund = weChatPayRefundVo.getAmount().getRefund();
        this.total = weChatPayRefundVo.getAmount().getTotal();
        this.currency = weChatPayRefundVo.getAmount().getCurrency();
    }

    public WeChatPayRefundRetVo createVo(){
        return new WeChatPayRefundRetVo(this);
    }

}
