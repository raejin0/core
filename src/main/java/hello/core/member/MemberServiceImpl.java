package hello.core.member;

// 구현체가 1개일 경우에는 관례상 impl 이라고 명칭한다.
public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository = new MemoryMemberRepository();

	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.find(memberId);
	}
}
