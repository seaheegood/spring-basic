package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // rely on abstraction(추상화) and embodiment(구체화)
    private final MemberRepository memberRepository;

    // 현재 이 멤버 서비스 인플에 MemoryMemberRepository 에 관한 코드가 전혀 없다
    // 오로지 MemberRepository 라는 인터페이스만이 존재한다
    // -> 추상화에만 의존하는 것 DIP 를 지키는 것

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
