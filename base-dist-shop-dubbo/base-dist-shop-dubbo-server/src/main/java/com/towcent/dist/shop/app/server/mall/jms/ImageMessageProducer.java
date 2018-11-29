package com.towcent.dist.shop.app.server.mall.jms;

import javax.jms.Destination;

import org.springframework.jms.core.JmsTemplate;

import com.towcent.base.common.mapper.JsonMapper;
import com.towcent.base.common.vo.ImageJmsVo;

/**
 * 图片处理消息
 * 
 * @author huangtao
 * @date 2015年7月24日 下午4:37:40
 * @version 0.1.0 
 * @copyright zuojian.com
 */
public class ImageMessageProducer {
	
	//Jms消息模版
	private JmsTemplate jmsTemplate;
	
	//消息队列
	private Destination notifyQueue;
	
	/**
	 * 发送消息
	 * @param imageJmsVo
	 */
	public void sendMessage(ImageJmsVo imageJmsVo) {
		this.convertAndSend(JsonMapper.toJsonString(imageJmsVo));
	}
	
	private void convertAndSend(String imageJmsVo) {
		jmsTemplate.convertAndSend(notifyQueue, imageJmsVo);
	}
	
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	public Destination getNotifyQueue() {
		return notifyQueue;
	}
	public void setNotifyQueue(Destination notifyQueue) {
		this.notifyQueue = notifyQueue;
	}
	
}
