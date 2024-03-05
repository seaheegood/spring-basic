package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // rely on abstraction(추상화) and embodiment(구체화)
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
