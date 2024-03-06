package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig { // AppConfig 는 애플리케이션의 실제 동장엑 필요한 구현 객체를 생성한다.
    // AppConfig 는 생성항 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해 주입(연결) 해준다.

    public MemberService memberService() {
        // 생성자 주입
        // 클라이언트인 MemberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입하는 것 같다하여
        // DI(Dependency Injection) = 의존관계 주입 또는 의존성 주입
        return new MemberServiceImpl(memberRepository());
    }

    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
