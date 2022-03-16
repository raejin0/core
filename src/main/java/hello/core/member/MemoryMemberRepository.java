package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoryMemberRepository implements MemberRepository{

	// 실무에서는 동시성 이슈가 있을 수 있기 때문에 ConcurrentHashMap을 사용
//	private static Map<Long, Member> store = new HashMap<>();
	private static Map<Long, Member> store = new ConcurrentHashMap<>();

	@Override
	public void save(Member member) {
		store.put(member.getId(), member);
	}

	@Override
	public Member find(Long memberId) {
		return store.get(memberId);
	}
}
