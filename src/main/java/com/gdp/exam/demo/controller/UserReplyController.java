package com.gdp.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdp.exam.demo.service.ReplyService;
import com.gdp.exam.demo.utill.Ut;
import com.gdp.exam.demo.vo.Reply;
import com.gdp.exam.demo.vo.ResultData;
import com.gdp.exam.demo.vo.Rq;

@Controller
public class UserReplyController {
	private ReplyService replyService;
	private Rq rq;

	public UserReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}

	@RequestMapping("/user/reply/doWrite")
	@ResponseBody
	public String doWrite(String relTypeCode, int relId, String body, String replceUri) {
		if (Ut.empty(relTypeCode)) {
			return rq.jsHistoryBack("relTypeCode(을)를 입력해주세요.");
		}

		if (Ut.empty(relId)) {
			return rq.jsHistoryBack("relId(을)를 입력해주세요.");
		}

		if (Ut.empty(body)) {
			return rq.jsHistoryBack("body(을)를 입력해주세요.");
		}

		ResultData<Integer> writeReplyRd = replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);

		int id = writeReplyRd.getData1();

		if (Ut.empty(replceUri)) {
			switch (relTypeCode) {
			case "article":
				replceUri = Ut.f("../article/detail?id=%d", relId);
				break;
			}
		}

		return rq.jsReplace(writeReplyRd.getMsg(), replceUri);

	}
	
	
	@RequestMapping("/user/reply/doDelete")
	@ResponseBody
	public String doDelete(int id, String replceUri) {
		if (Ut.empty(id)) {
			return rq.jsHistoryBack("relTypeCode(을)를 입력해주세요.");
		}

		Reply reply = replyService.getForPrintReply(rq.getLoginedMemberId(), id);
		
		if(reply == null) {
			rq.jsHistoryBack(Ut.f("%d번 댓글이 존재하지 않습니다.", id));
		}
		
		if(reply.isExtra__actorCanDelete() == false) {
			return rq.jsHistoryBack(Ut.f("%번 댓글을 삭제할 권한이 없습니다.", id));
		}
		
		ResultData deleteReplyRd = replyService.deleteReply(id);
		
		if (Ut.empty(replceUri)) {
			switch (reply.getRelTypeCode()) {
			case "article":
				replceUri = Ut.f("../article/detail?id=%d", reply.getRelId());
				break;
			}
		}
		
		return rq.jsReplace(deleteReplyRd.getMsg(), replceUri);
		
	}
	

	@RequestMapping("/user/reply/modify")
	public String modify(Model model, int id) {
		if (Ut.empty(id)) {
			return rq.jsHistoryBack("relTypeCode(을)를 입력해주세요.");
		}
		
		Reply reply = replyService.getForPrintReply(rq.getLoginedMemberId(), id);
		
		if(reply == null) {
			rq.jsHistoryBack(Ut.f("%d번 댓글이 존재하지 않습니다.", id));
		}
		
		if(reply.isExtra__actorCanModify() == false) {
			return rq.jsHistoryBack(Ut.f("%번 댓글을 수정할 권한이 없습니다.", id));
		}
		
		
		model.addAttribute("reply", reply);
		return "user/reply/modify";
		
	}
	
	
	@RequestMapping("/user/reply/doModify")
	@ResponseBody
	public String doModify(int id, String body, String replceUri) {
		if (Ut.empty(id)) {
			return rq.jsHistoryBack("relTypeCode(을)를 입력해주세요.");
		}

		Reply reply = replyService.getForPrintReply(rq.getLoginedMemberId(), id);
		
		if(reply == null) {
			rq.jsHistoryBack(Ut.f("%d번 댓글이 존재하지 않습니다.", id));
		}
		
		if(reply.isExtra__actorCanModify() == false) {
			return rq.jsHistoryBack(Ut.f("%번 댓글을 수정할 권한이 없습니다.", id));
		}
		
		if(Ut.empty(body)) {
			return rq.jsHistoryBack("body(을)를 입력해주세요.");
		}
		
		ResultData modifyReplyRd = replyService.modifyReply(id, body);
		
		if (Ut.empty(replceUri)) {
			switch (reply.getRelTypeCode()) {
			case "article":
				replceUri = Ut.f("../article/detail?id=%d", reply.getRelId());
				break;
			}
		}
		
		return rq.jsReplace(modifyReplyRd.getMsg(), replceUri);
		
	}

}
