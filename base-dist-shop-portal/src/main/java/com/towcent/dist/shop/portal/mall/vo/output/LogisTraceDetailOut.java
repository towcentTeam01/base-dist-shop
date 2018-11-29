/**
 * 
 */
package com.towcent.dist.shop.portal.mall.vo.output;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @author shiwei
 *
 */
@Data
public class LogisTraceDetailOut implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date traceTime; // 跟踪时间.

	private String traceDesc; // 物流描述.

	private String location; // 物流坐标.
}
