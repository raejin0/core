package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

//  An amount of money, A quantity of money
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
