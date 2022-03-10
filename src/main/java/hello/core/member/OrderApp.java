package hello.core.member;

import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

	public static void main(String[] args) {
		MemberService memberService = new MemberServiceImpl();
		OrderService orderService = new OrderServiceImpl();

		Long memberId = 1L;
		Member member = new Member(memberId, "name", Grade.VIP);
		memberService.join(member);

//		Member findMember = memberService.findMember(1L);
//		System.out.println("result = " + (findMember == member)) ;

		Order order = orderService.createOrder(memberId, "itemA", 10000);

		System.out.println("order = " + order);
		System.out.println("order.calculatePrice = " + order.calculatePrice());


	}
}
