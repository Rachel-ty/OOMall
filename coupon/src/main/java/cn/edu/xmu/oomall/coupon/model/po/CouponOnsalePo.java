package cn.edu.xmu.oomall.coupon.model.po;

import java.time.LocalDateTime;

public class CouponOnsalePo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oomall_coupon_onsale.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oomall_coupon_onsale.activity_id
     *
     * @mbg.generated
     */
    private Long activityId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oomall_coupon_onsale.onsale_id
     *
     * @mbg.generated
     */
    private Long onsaleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oomall_coupon_onsale.created_by
     *
     * @mbg.generated
     */
    private Long createdBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oomall_coupon_onsale.create_name
     *
     * @mbg.generated
     */
    private String createName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oomall_coupon_onsale.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oomall_coupon_onsale.id
     *
     * @return the value of oomall_coupon_onsale.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oomall_coupon_onsale.id
     *
     * @param id the value for oomall_coupon_onsale.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oomall_coupon_onsale.activity_id
     *
     * @return the value of oomall_coupon_onsale.activity_id
     *
     * @mbg.generated
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oomall_coupon_onsale.activity_id
     *
     * @param activityId the value for oomall_coupon_onsale.activity_id
     *
     * @mbg.generated
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oomall_coupon_onsale.onsale_id
     *
     * @return the value of oomall_coupon_onsale.onsale_id
     *
     * @mbg.generated
     */
    public Long getOnsaleId() {
        return onsaleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oomall_coupon_onsale.onsale_id
     *
     * @param onsaleId the value for oomall_coupon_onsale.onsale_id
     *
     * @mbg.generated
     */
    public void setOnsaleId(Long onsaleId) {
        this.onsaleId = onsaleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oomall_coupon_onsale.created_by
     *
     * @return the value of oomall_coupon_onsale.created_by
     *
     * @mbg.generated
     */
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oomall_coupon_onsale.created_by
     *
     * @param createdBy the value for oomall_coupon_onsale.created_by
     *
     * @mbg.generated
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oomall_coupon_onsale.create_name
     *
     * @return the value of oomall_coupon_onsale.create_name
     *
     * @mbg.generated
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oomall_coupon_onsale.create_name
     *
     * @param createName the value for oomall_coupon_onsale.create_name
     *
     * @mbg.generated
     */
    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oomall_coupon_onsale.create_time
     *
     * @return the value of oomall_coupon_onsale.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oomall_coupon_onsale.create_time
     *
     * @param createTime the value for oomall_coupon_onsale.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}