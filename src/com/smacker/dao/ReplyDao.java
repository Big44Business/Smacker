package com.smacker.dao;

import java.util.List;

import com.smacker.bean.Reply;

public interface ReplyDao {
	public boolean saveReply(Reply reply);
	public boolean deleteReply(int replyId);
	/**
	 * 获取用户的所有留言
	 * @param userId
	 * @return
	 */
	public List<Reply> getReplysByUserId(String userId);
	/**
	 * 获取某个商品下的所有留言
	 * @param commodityId
	 * @return
	 */
	public List<Reply> getReplysBuCommodityId(String commodityId);
}
