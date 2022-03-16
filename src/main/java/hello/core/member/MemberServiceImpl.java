package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 구현체가 1개일 경우에는 관례상 impl 이라고 명칭한다.
//@Component("memberService2")  // 이름 부여 가능
@Component
public class MemberServiceImpl implements MemberService{
	
	private final MemberRepository memberRepository;

	@Autowired // ac.getBean(MemberRepository.class)
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}


	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.find(memberId);
	}

	//테스트용
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
