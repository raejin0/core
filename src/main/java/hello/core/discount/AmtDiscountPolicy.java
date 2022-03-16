package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//  An amount of money, A quantity of money
@Component
//@Primary
@MainDiscountPolicy
public class AmtDiscountPolicy implements DiscountPolicy {

	private int defaultAmt = 1000;  // Amount

	@Override
	public int discount(Member member, int price) {
		if ( member.getGrade() == Grade.VIP) {
			return defaultAmt;
		} else {
			return 0;
		}
	}
}
