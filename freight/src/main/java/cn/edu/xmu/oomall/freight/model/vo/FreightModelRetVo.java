package cn.edu.xmu.oomall.freight.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author xucangbai
 * @date 2021/11/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreightModelRetVo{
    private Long id;
    private String name;
    private Byte type;
    private Integer unit;
    private Byte defaultModel;
    private SimpleUserRetVo creator;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss.SSS" )
    private LocalDateTime gmtCreate;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss.SSS" )
    private LocalDateTime gmtModified;
    private SimpleUserRetVo modifier;
}