package hello.core;

import hello.core.discount.AmtDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.PctDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	// 1. 이렇게 총 5번 new 연산자로 객체 생성을 해야하는 것 같지만
	// call AppConfig.memberService
	// call AppConfig.memberRepository

	// call AppConfig.memberRepository

	// call AppConfig.orderService
	// call AppConfig.memberRepository

	// 2. 실제로는 각 한번씩만 호출된다.
	// call AppConfig.memberService
	// call AppConfig.memberRepository
	// call AppConfig.orderService

	@Bean
	public MemberService memberService() {
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository();
	}

	@Bean
	public OrderService orderService() {
		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl( memberRepository(), discountPolicy() );
	}

	@Bean
	public DiscountPolicy discountPolicy() {
		return new AmtDiscountPolicy();
//		return new PctDiscountPolicy();
	}
}
