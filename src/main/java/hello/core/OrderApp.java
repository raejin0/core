package hello.core;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

	public static void main(String[] args) {
		AppConfig appConfig = new AppConfig();

		MemberService memberService = appConfig.memberService();
		OrderService orderService = appConfig.orderService();
//		MemberService memberService = new MemberServiceImpl(null);
//		OrderService orderService = new OrderServiceImpl(null, null);

		Long memberId = 1L;
		Member member = new Member(memberId, "name", Grade.VIP);
		memberService.join(member);

//		Member findMember = memberService.findMember(1L);
//		System.out.println("result = " + (findMember == member)) ;

		Order order = orderService.createOrder(memberId, "itemA", 20000);

		System.out.println("order = " + order);
		System.out.println("order.calculatePrice = " + order.calculatePrice());


	}
}
