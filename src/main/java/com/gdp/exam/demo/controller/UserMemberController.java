package com.gdp.exam.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdp.exam.demo.service.MemberService;
import com.gdp.exam.demo.utill.Ut;
import com.gdp.exam.demo.vo.Member;
import com.gdp.exam.demo.vo.ResultData;
import com.gdp.exam.demo.vo.Rq;

@Controller
public class UserMemberController {
	private MemberService memberService;
	private Rq rq;
	
	public UserMemberController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}
	
	@RequestMapping("/user/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		if ( Ut.empty(loginId) ) {
			return ResultData.from("F-1", "loginId(을)를 입력해주세요.");
		}
		
		if ( Ut.empty(loginPw) ) {
			return ResultData.from("F-2", "loginPw(을)를 입력해주세요.");
		}
		
		if ( Ut.empty(name) ) {
			return ResultData.from("F-3", "name(을)를 입력해주세요.");
		}
		
		if ( Ut.empty(nickname) ) {
			return ResultData.from("F-4", "nickname(을)를 입력해주세요.");
		}
		
		if ( Ut.empty(cellphoneNo) ) {
			return ResultData.from("F-5", "cellphoneNo(을)를 입력해주세요.");
		}
		
		if ( Ut.empty(email) ) {
			return ResultData.from("F-6", "email(을)를 입력해주세요.");
		}
		
		// S-1
		// 회원가입이 완료되었습니다.
		// 7
		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		if ( joinRd.isFail() ) {
			return (ResultData)joinRd;
		}
		
		Member member = memberService.getMemberById(joinRd.getData1());
		
		return ResultData.newData(joinRd, "member", member);
	}
	
	@RequestMapping("/user/member/doLogout")
	@ResponseBody
	public String doLogout() {
		if ( !rq.isLogined() ) {
			return rq.jsHistoryBack("이미 로그아웃 상태입니다.");
		}
		
		rq.logout();
		
		return rq.jsReplace("로그아웃 되었습니다.", "/");
	}
	
	@RequestMapping("/user/member/login")
	public String showLogin() {
		return "user/member/login";
	}
	
	@RequestMapping("/user/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {
		if ( rq.isLogined() ) {
			return rq.jsHistoryBack("이미 로그인되었습니다.");
		}
		
		if ( Ut.empty(loginId) ) {
			return rq.jsHistoryBack("loginId(을)를 입력해주세요.");
		}
		
		if ( Ut.empty(loginPw) ) {
			return rq.jsHistoryBack("loginPw(을)를 입력해주세요.");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if ( member == null ) {
			return rq.jsHistoryBack("존재하지 않은 로그인아이디 입니다.");
		}
		
		if ( member.getLoginPw().equals(loginPw) == false ) {
			return rq.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}
		
		rq.login(member);
		
		return rq.jsReplace(Ut.f("%s님 환영합니다.", member.getNickname()), "/");
	}
	
	@RequestMapping("/user/member/getMembers")
	@ResponseBody
	public List<Member> getMembers() {
		return memberService.getMembers();
	}
	
	@RequestMapping("/user/member/myPage")
	public String showMyPage() {
		return "user/member/myPage";
	}
	
	@RequestMapping("/user/member/checkPassword")
	public String checkPassword() {	
		return "user/member/checkPassword";
	}
	
	@RequestMapping("/user/member/doCheckPassword")
	@ResponseBody
	public String checkPassword(String loginPw, String replaceUri) {
		
		if(rq.getLoginedMember().getLoginPw().equals(loginPw) == false) {
			return rq.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}
		
		if(replaceUri.equals("../member/modify")) {
			String memberModifyAuthKey = memberService.genMemberModifyAuthKey(rq.getLoginedMemberId());
			
			replaceUri += "?memberModifyAuthKey" +memberModifyAuthKey;
		}
		
		
		return rq.jsReplace("", replaceUri);
	}
	
	
	@RequestMapping("/user/member/modify")
	public String showModify() {
		return "user/member/modify";
	}
	
	@RequestMapping("/user/member/doModify")
	@ResponseBody
	public String doModify(String loginPw, String name, String nickname, String email, String cellphoneNo) {
		if (Ut.empty(loginPw)) {
			loginPw = null;
		}
		
		if (Ut.empty(name)) {
			return rq.jsHistoryBack("이름을 입력해주세요.");
		}
		
		if (Ut.empty(nickname)) {
			return rq.jsHistoryBack("닉네임을 입력해주세요.");
		}
		
		if (Ut.empty(email)) {
			return rq.jsHistoryBack("이메일을 입력해주세요.");
		}
		
		if (Ut.empty(cellphoneNo)) {
			return rq.jsHistoryBack("휴대전화번호를 입력해주세요.");
		}
		
		ResultData modifyRd = memberService.modify(rq.getLoginedMemberId(), loginPw, name, nickname, email, cellphoneNo);
		
		return rq.jsReplace(modifyRd.getMsg(), "/"); 
	}
}