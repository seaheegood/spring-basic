package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }

    /**
     * Is there any problem with Member domain settings?
     * - if you gonna change the repository to another, is it fine with OCP principle?
     * - good with DIP?
     *
     * - dependency relationship relies on not only the interface but also the implementation
     */
}
