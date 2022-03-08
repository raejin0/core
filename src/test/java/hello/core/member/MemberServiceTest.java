package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

	private final MemberService memberService = new MemberServiceImpl();

	@Test
	void join() {
		//given
		Member member = new Member(1L, "member1", Grade.VIP);

		//when
		memberService.join(member);
		Member findMember = memberService.findMember(1L);
//		Member findMember = memberService.findMember(2L);

		//then
		Assertions.assertThat(member).isEqualTo(findMember);
	}
}
