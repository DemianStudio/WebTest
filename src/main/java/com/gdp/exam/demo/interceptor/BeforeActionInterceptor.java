package com.gdp.exam.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gdp.exam.demo.service.MemberService;
import com.gdp.exam.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
	private Rq rq;
	
	public BeforeActionInterceptor(Rq rq) {
		this.rq = rq;
	}
	@Autowired
	private MemberService memberService;
	
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		// System.out.println("실행가능?");
		
		// 이제는 Rq 객체가 자동으로 만들어지기 때문에 필요 없음.
		//	Rq rq = new Rq(req, resp, memberService);
		//	req.setAttribute("rq", rq);
		rq.initOnBeforeActionInterceptor();
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
