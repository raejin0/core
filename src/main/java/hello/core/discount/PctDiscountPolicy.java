package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

 // Percentage
public class PctDiscountPolicy implements DiscountPolicy {
	private int discountRate = 10;

	@Override
	public int discount(Member member, int price) {
		if ( member.getGrade() == Grade.VIP) {
			return price / 100 * discountRate;
		} else {
			return 0;
		}
	}
}
