package hello.core.member.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

	private int defaultAmount = 1000;

	@Override
	public int discount(Member member, int price) {
		if ( member.getGrade() == Grade.VIP) {
			return defaultAmount;
		} else {
			return 0;
		}
	}
}
