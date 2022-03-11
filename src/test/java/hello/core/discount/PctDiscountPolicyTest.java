package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class PctDiscountPolicyTest {

	PctDiscountPolicy discountPolicy = new PctDiscountPolicy();

	@Test
	@DisplayName("VIPs must be discounted by 10 percent")
	public void vip_o() throws Exception {
	    // given
		Member member = new Member(1L, "MemberVIP", Grade.VIP);

		// when
		int price = 10000;
		int discount = discountPolicy.discount(member, price);

		// then
		assertThat(discount).isEqualTo(1000);
	}

	@Test
	@DisplayName("No discount is applicable if it isn't VIP")
	public void vip_x() throws Exception {
	    // given
		Member member = new Member(1L, "MemberVIP", Grade.BASIC);

		// when
		int price = 10000;
		int discount = discountPolicy.discount(member, price);

		// then
		assertThat(discount).isEqualTo(0);
	}

}